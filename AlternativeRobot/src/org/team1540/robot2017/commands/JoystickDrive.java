package org.team1540.robot2017.commands;

import org.team1540.robot2017.OI;
import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class JoystickDrive extends Command {
    public JoystickDrive() {
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        double multiplier = (OI.l.get() || OI.r.get()) ? 0.4 : 1.0;
        Robot.driveTrain.setBraking(OI.l.get() || OI.r.get());
        Robot.driveTrain.tankDrive(OI.getDriveLeftJoystickX(), OI.getDriveLeftJoystick(), OI.getDriveRightJoystickX(), 
                OI.getDriveRightJoystick(), OI.getDriveLeftTrigger(), OI.getDriveRightTrigger(), multiplier);
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
