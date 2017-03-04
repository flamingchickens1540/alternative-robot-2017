package org.team1540.robot2017.commands;

import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TurnDegrees extends Command {
    
    private double degrees; // positive = right, negative = left
    private double position;
    
    public TurnDegrees(double degreesR) {
        requires(Robot.driveTrain);
        degrees = degreesR;
    }
    
    protected void initialize() {
        Robot.driveTrain.zeroEncoders();
        Robot.driveTrain.setPIDLeft(Robot.tuning.getAutoTurningP(), Robot.tuning.getAutoTurningI(), 
                Robot.tuning.getAutoTurningD(), Robot.tuning.getAutoTuningF());
        Robot.driveTrain.setPIDRight(Robot.tuning.getAutoTurningP(), Robot.tuning.getAutoTurningI(), 
                Robot.tuning.getAutoTurningD(), Robot.tuning.getAutoTuningF());
        double cTurning = 24 * Math.PI;
        double cWheel = 4 * Math.PI;
        double ticksPerRev = 1024;
        position = degrees * (1.0/360.0) * cTurning * (1.0/cWheel) * ticksPerRev;
        Robot.driveTrain.setRelativePositionRight(position);
        Robot.driveTrain.setRelativePositionLeft(-position);
    }
    
    @Override
    protected boolean isFinished() {
        boolean r = Math.abs(Robot.driveTrain.getRightEncoderPosition() - position) 
                < Robot.tuning.getAutoTurningMarginOfError();
        boolean l = Math.abs(Robot.driveTrain.getLeftEncoderPosition() - (-position)) 
                < Robot.tuning.getAutoTurningMarginOfError();
        return r && l;
    }

}
