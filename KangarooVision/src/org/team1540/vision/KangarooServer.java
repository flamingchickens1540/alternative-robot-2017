package org.team1540.vision;

import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.nio.ByteBuffer;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.ds.ipcam.IpCamDeviceRegistry;
import com.github.sarxos.webcam.ds.ipcam.IpCamMode;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.IRemote;
import edu.wpi.first.wpilibj.tables.IRemoteConnectionListener;

public class KangarooServer {
    private static NetworkTable table;
    private static boolean connected;
    private static long nanoStartTime = System.nanoTime();

    private static boolean shouldStop = false;
    private static Mat currentFrame = null;
    private static Object videoLock = new Object();
    private static long currentFrameTime = 0;
    private static boolean needsNewImage = true;

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        
        NetworkTable.setClientMode();
        NetworkTable.setIPAddress("RoboRIO-1540-FRC.local");
        table = NetworkTable.getTable("kangaroo");

        table.addConnectionListener(new IRemoteConnectionListener() {
            @Override
            public void disconnected(IRemote remote) {
                connected = false;
            }

            @Override
            public void connected(IRemote remote) {
                connected = true;
                syncTime();
            }
        }, true);

        Thread videoThread = new Thread(KangarooServer::runVideoUpdater);
        videoThread.setDaemon(true);
        videoThread.start();

        while (!shouldStop) {
            if (currentFrame != null) {
                Mat imageOutputMat;
                Mat currentFrameCopy;
                long cFrameTime;
                float frameWidth;

                needsNewImage = true;

                while (needsNewImage) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                    }
                }

                synchronized (videoLock) {
                    imageOutputMat = Vision.grayscaleOutputMat(currentFrame);
                    cFrameTime = currentFrameTime;
                    frameWidth = currentFrame.width();
                    currentFrameCopy = currentFrame.clone();
                }

                Vision.thresholdImage(currentFrameCopy, imageOutputMat,
                        new Scalar(0.19444445 * 180, 0.7 * 256, 0.12999999 * 256), new Scalar(0.5 * 180, 256, 256));

                List<GoalBox> boxes = Vision.isolateThresholdedGoals(imageOutputMat, 50);
                boxes.sort((a, b) -> -Double.compare(a.area(), b.area()));

                if (!boxes.isEmpty()) {
                    GoalBox goal = boxes.get(0);
                    Point center = new Point(imageOutputMat.width() / 2, imageOutputMat.height() / 2);
                    float yawOffset = (float) Vision.horizontalAngleFromCenter(goal.center(), center,
                            68.5f / frameWidth);
                    transmitCameraUpdate(cFrameTime, yawOffset, true);
                    System.out.println("hue " + table.getNumber("hue_lower", -1));
                } else {
                    transmitCameraUpdate(cFrameTime, 0, false);
                }
            }

            try {
                Thread.sleep(1000 / 30);
            } catch (Exception e) {
            }
        }
    }

    private static Webcam openNetworkCam(String url) throws MalformedURLException {
        IpCamDeviceRegistry.register("Webcam", "http://127.0.0.1/?action=stream", IpCamMode.PUSH);
        Webcam cam = Webcam.getDefault();
        cam.open();
        return cam;
    }

    private static void syncTime() {
        ByteBuffer buffer = ByteBuffer.allocate(8).putLong(getNanoTime());
        buffer.rewind();
        table.putRaw("timestamp", buffer, 8);
    }

    private static long getNanoTime() {
        return System.nanoTime() - nanoStartTime;
    }

    private static void transmitCameraUpdate(long timestamp, double rotationalOffset, boolean canSee) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES + Double.BYTES + 1);
        buffer.putLong(timestamp);
        buffer.putDouble(rotationalOffset);
        buffer.put((byte) (canSee ? 1 : 0));
        buffer.rewind();
        table.putRaw("camera_update", buffer, Long.BYTES + Double.BYTES + 1);
    }

    private static void runVideoUpdater() {
        Webcam vc;
        try {
            vc = openNetworkCam("localhost");

            while (true) {
                for (int i = 0; i < 1; ++i) {
                    BufferedImage imageCandidate = vc.getImage();
                    if (imageCandidate != null && needsNewImage) {
                        synchronized (videoLock) {
                            currentFrame = Vision.imageToMat(imageCandidate);
                            currentFrameTime = getNanoTime();
                            needsNewImage = false;
                        }
                    }
                }

                try {
                    Thread.sleep(1000 / 30);
                } catch (Exception e) {
                }
            }
        } catch (MalformedURLException e1) {
        }
    }

    public static NetworkTable getTable() {
        return table;
    }

    public static boolean isConnected() {
        return connected;
    }
}
