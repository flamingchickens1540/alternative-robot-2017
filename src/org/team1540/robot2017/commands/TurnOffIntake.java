package org.team1540.robot2017.commands;

import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TurnOffIntake extends Command {
	
	public TurnOffIntake() {
		requires(Robot.intake);
	}
	
	protected void execute() {
		Robot.intake.set(0);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}
