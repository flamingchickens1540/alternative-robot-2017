package org.team1540.robot2017.commands;

import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TurnEverythingOff extends Command {
	public TurnEverythingOff() {
		requires(Robot.shooter);
		requires(Robot.feeder);
		requires(Robot.belt);
		requires(Robot.intake);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.shooter.stop();
		Robot.feeder.setTop(0);
		Robot.feeder.setLeft(0);
		Robot.feeder.setRight(0);
		Robot.belt.stop();
		Robot.intake.set(0);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return true;
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
