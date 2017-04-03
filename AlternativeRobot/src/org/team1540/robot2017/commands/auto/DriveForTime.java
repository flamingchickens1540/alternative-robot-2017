package org.team1540.robot2017.commands.auto;

import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

public class DriveForTime extends TimedCommand {
    
    private double set;
    
    public DriveForTime(double seconds, double set) {
        super(seconds);
        requires(Robot.driveTrain);
        this.set = set;
    }
    
    protected void initialize() {
        Robot.driveTrain.set(set, set);
    }
    
    protected void end() {
        Robot.driveTrain.stop();
    }
    
    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }
    
}
