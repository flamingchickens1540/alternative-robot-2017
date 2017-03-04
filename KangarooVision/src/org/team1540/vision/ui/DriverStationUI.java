package org.team1540.vision.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.team1540.vision.GoalBox;
import org.team1540.vision.Vision;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.ds.ipcam.IpCamDeviceRegistry;
import com.github.sarxos.webcam.ds.ipcam.IpCamDriver;
import com.github.sarxos.webcam.ds.ipcam.IpCamMode;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class DriverStationUI {
    private static HSVThresholdPicker picker = new HSVThresholdPicker(360, 20, 100);
    private static WebcamPanel webcamPanel = new WebcamPanel();
    private static NetworkTable table;

    private static BufferedImage thresholdImage(BufferedImage image) {
        Mat imageMat = Vision.imageToMat(image);
        Mat imageOutputMat = Vision.grayscaleOutputMat(imageMat);

        Vision.thresholdImage(imageMat, imageOutputMat,
                new Scalar(picker.getHueLower() * 180, picker.getSaturationLower() * 256, picker.getValueLower() * 256),
                new Scalar(picker.getHueUpper() * 180, picker.getSaturationUpper() * 256,
                        picker.getValueUpper() * 256));
        BufferedImage imageOutput = Vision.matToImage(imageOutputMat);

        BufferedImage convertedImg = new BufferedImage(imageOutput.getWidth(), imageOutput.getHeight(),
                BufferedImage.TYPE_3BYTE_BGR);
        convertedImg.getGraphics().drawImage(imageOutput, 0, 0, null);

        List<GoalBox> goalBoxes = Vision.isolateThresholdedGoals(imageOutputMat, 30);
        goalBoxes.sort((a, b) -> a.area() < b.area() ? 1 : -1);

        int boxRadius = 3;
        Graphics2D g2d = (Graphics2D) convertedImg.getGraphics();
        for (GoalBox box : goalBoxes) {
            g2d.setColor(Color.YELLOW);
            g2d.drawLine((int) box.bottomLeft.x, (int) box.bottomLeft.y, (int) box.bottomRight.x,
                    (int) box.bottomRight.y);
            g2d.drawLine((int) box.bottomRight.x, (int) box.bottomRight.y, (int) box.topRight.x, (int) box.topRight.y);
            g2d.drawLine((int) box.topRight.x, (int) box.topRight.y, (int) box.topLeft.x, (int) box.topLeft.y);
            g2d.drawLine((int) box.topLeft.x, (int) box.topLeft.y, (int) box.bottomLeft.x, (int) box.bottomLeft.y);
            g2d.setColor(Color.MAGENTA);
            g2d.fillRect((int) box.bottomLeft.x - boxRadius, (int) box.bottomLeft.y - boxRadius, boxRadius * 2 + 1,
                    boxRadius * 2 + 1);
            g2d.setColor(Color.RED);
            g2d.fillRect((int) box.bottomRight.x - boxRadius, (int) box.bottomRight.y - boxRadius, boxRadius * 2 + 1,
                    boxRadius * 2 + 1);
            g2d.setColor(Color.GREEN);
            g2d.fillRect((int) box.topLeft.x - boxRadius, (int) box.topLeft.y - boxRadius, boxRadius * 2 + 1,
                    boxRadius * 2 + 1);
            g2d.setColor(Color.CYAN);
            g2d.fillRect((int) box.topRight.x - boxRadius, (int) box.topRight.y - boxRadius, boxRadius * 2 + 1,
                    boxRadius * 2 + 1);
        }

        if (!goalBoxes.isEmpty()) {
            GoalBox box = goalBoxes.get(0);
            double angle = Vision.horizontalAngleFromCenter(box.center(),
                    new Point(image.getWidth() / 2, image.getHeight() / 2), (68.5f / 1) / image.getWidth());
            System.out.println("Angle " + angle + " Width " + image.getWidth());
        }

        return convertedImg;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        JFrame frame = new JFrame("ChickenVision Tuner");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel listPane = new JPanel();
        frame.setContentPane(listPane);
        frame.setLayout(new BoxLayout(listPane, BoxLayout.X_AXIS));

        listPane.add(webcamPanel);
        listPane.add(Box.createRigidArea(new Dimension(0, 20)));
        listPane.add(picker);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(875, 463);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Webcam.setDriver(new IpCamDriver());
        IpCamDeviceRegistry.register("Webcam", "http://1540kangaroo.frc-robot.local:8080/?action=stream", IpCamMode.PUSH);
        Webcam webcam = Webcam.getDefault();
        webcam.open();
        
        NetworkTable.setClientMode();
        NetworkTable.setIPAddress("RoboRIO-1540-FRC.local");
        table = NetworkTable.getTable("kangaroo");

        Thread thing = new Thread(() -> {
            while (true) {
                System.out.println("Hue L " + picker.getHueLower() + " | " + "Hue U " + picker.getHueUpper() + " | "
                        + "Sat L " + picker.getSaturationLower() + " | " + "Sat U " + picker.getSaturationUpper()
                        + " | " + "Val L " + picker.getValueLower() + " | " + "Val L " + picker.getValueUpper());
                table.putNumber("hue_lower", picker.getHueLower());
                try {
                    Thread.sleep(5000);
                } catch (Exception e) {
                }
            }
        });
        thing.setDaemon(true);
        thing.start();

        while (webcam.isOpen()) {
            BufferedImage possibleImage = webcam.getImage();
            if (possibleImage != null) {
                BufferedImage smallerImage = thresholdImage(Vision.toBufferedImage(
                        possibleImage.getScaledInstance((int) (640.f / 1.5), (int) (480.f / 1.5), Image.SCALE_FAST)));
                webcamPanel.setImage(smallerImage);
            }
            Thread.sleep(100);
        }
    }
}
