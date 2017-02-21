package org.team1540.robot2017.commands;

import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TurnOnIntake extends Command {
	
	public TurnOnIntake() {
		requires(Robot.intake);
	}
	
	@Override
	protected void execute() {
		Robot.intake.set(1.0);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
}
