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
        Robot.driveTrain.setPID(Robot.tuning.getAutoTurningP(), Robot.tuning.getAutoTurningI(), 
                Robot.tuning.getAutoTurningD());
        double cTurning = 24 * Math.PI;
        double cWheel = 4 * Math.PI;
        double ticksPerRev = 125 * 15;
        position = degrees * (1/360) * cTurning * (1/cWheel) * ticksPerRev;
        Robot.driveTrain.setPositionRight(position);
        Robot.driveTrain.setPositionLeft(-position);
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
