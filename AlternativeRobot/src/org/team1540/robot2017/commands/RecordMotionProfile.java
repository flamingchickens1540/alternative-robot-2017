package org.team1540.robot2017.commands;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.team1540.robot2017.OI;
import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class RecordMotionProfile extends Command {

    private List<double[]> pointsLeft = new ArrayList<>();
    private List<double[]> pointsRight = new ArrayList<>();
    private long prevTime = 0;
    
    public RecordMotionProfile() {
        requires(Robot.driveTrain);
    }
    
    @Override
    protected void initialize() {
        Robot.driveTrain.zeroEncoders();
        prevTime = System.nanoTime() / 1000000;
        pointsLeft.clear();
        pointsRight.clear();        
        
        System.out.println("started profiling");
    }

    @Override
    protected void execute() {
        int timeDiff = (int) ((System.nanoTime() / 1000000) - prevTime);
        double leftPos = Robot.driveTrain.getLeftEncoderPosition();
        double rightPos = Robot.driveTrain.getRightEncoderPosition();
        double leftVel = Robot.driveTrain.getLeftSpeed();
        double rightVel = Robot.driveTrain.getRightSpeed();
        prevTime += timeDiff;
        
        pointsLeft.add(new double[] { leftPos, leftVel, timeDiff });
        pointsRight.add(new double[] { rightPos, rightVel, timeDiff });      
    }

    @Override
    protected boolean isFinished() {
        return !OI.buttonRecord.get();
    }

    @Override
    protected void end() {
        System.out.println("finished profiling");
        
        StringBuilder leftCsv = new StringBuilder();
        for (int i=0; i<pointsLeft.size(); ++i) {
            if (i > 0) {
                double[] p = pointsLeft.get(i);
                leftCsv.append(p[0] + "," + p[1] + "," + p[2] + "\n");
            }
        }
        
        StringBuilder rightCsv = new StringBuilder();
        for (int i=0; i<pointsRight.size(); ++i) {
            if (i > 0) {
                double[] p = pointsRight.get(i);
                rightCsv.append(p[0] + "," + p[1] + "," + p[2] + "\n");
            }
        }
        
        try {
            Files.write(Paths.get("/home/lvuser/left.csv"), leftCsv.toString().getBytes());
            Files.write(Paths.get("/home/lvuser/right.csv"), rightCsv.toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void interrupted() {
    }
}