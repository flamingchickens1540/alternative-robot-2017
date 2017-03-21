package org.team1540.robot2017.commands;

import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurnDegrees extends Command {
    
    private double degrees; // positive = right, negative = left
    private double position;
    
    public TurnDegrees(double degreesR) {
        requires(Robot.driveTrain);
        degrees = degreesR;
    }
    
    protected void initialize() {
        Robot.driveTrain.configurePeakOutputVoltage(10d);
//        Robot.driveTrain.reverseOutputLeft();
//        Robot.driveTrain.setPIDLeft(Robot.tuning.getAutoTurningP(), Robot.tuning.getAutoTurningI(), 
//                Robot.tuning.getAutoTurningD(), Robot.tuning.getAutoTuningF());
//        Robot.driveTrain.setPIDRight(Robot.tuning.getAutoTurningP(), Robot.tuning.getAutoTurningI(), 
//                Robot.tuning.getAutoTurningD(), Robot.tuning.getAutoTuningF());
        Robot.driveTrain.setPIDLeft(0.01, 0, 0, 0);
        Robot.driveTrain.setPIDRight(0.01, 0, 0, 0);
        double cTurning = 24 * Math.PI;
        double cWheel = 4 * Math.PI;
        double ticksPerRev = 1024;
        position = degrees * (1.0/360.0) * cTurning * (1.0/cWheel) * ticksPerRev;
        SmartDashboard.putNumber("Target Position", position);
        Robot.driveTrain.setRelativePositionRight(-position);
        Robot.driveTrain.setRelativePositionLeft(position);
    }
    
    @Override
    protected boolean isFinished() {
//        boolean r = Math.abs(Robot.driveTrain.getRightEncoderPosition() - position) 
//                < Robot.tuning.getAutoTurningMarginOfError();
//        boolean l = Math.abs(Robot.driveTrain.getLeftEncoderPosition() - (-position)) 
//                < Robot.tuning.getAutoTurningMarginOfError();
//        return r && l;
        return false;
    }
    
    @Override
    protected void end() {
        Robot.driveTrain.unreverseOutput();
        Robot.driveTrain.configurePeakOutputVoltage(12d);
    }

}
