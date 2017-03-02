package org.team1540.robot2017.commands;

import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

public class DriveForTime extends TimedCommand {
    
    private double power;
    
    public DriveForTime(double seconds, double power) {
        super(seconds);
        requires(Robot.driveTrain);
        this.power = power;
    }
    
    protected void initialize() {
        Robot.driveTrain.set(power);
    }
    
    protected void end() {
        Robot.driveTrain.stop();
    }
    
    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }
    
}
