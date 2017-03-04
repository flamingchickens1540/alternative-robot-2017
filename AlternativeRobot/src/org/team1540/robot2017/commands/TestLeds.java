package org.team1540.robot2017.commands;

import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TestLeds extends Command {
    public TestLeds() {
        requires(Robot.ledBar);
        setRunWhenDisabled(true);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.ledBar.setBrightness(1.0);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
//        Robot.ledBar.setBrightness(OI.getLedJoystick());
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
