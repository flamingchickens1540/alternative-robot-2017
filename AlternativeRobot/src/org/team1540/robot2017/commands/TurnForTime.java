package org.team1540.robot2017.commands;

import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

public class TurnForTime extends TimedCommand {
    
    private double set;
    
    public TurnForTime(double seconds, double set) {
        super(seconds);
        requires(Robot.driveTrain);
        this.set = set;
    }
    
    protected void initialize() {
        Robot.driveTrain.tankDrive(set, -set);
    }
    
    protected void end() {
        Robot.driveTrain.stop();
    }
    
    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }
    
}
