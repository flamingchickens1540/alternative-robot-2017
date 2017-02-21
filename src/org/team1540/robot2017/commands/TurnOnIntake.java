package org.team1540.robot2017.commands;

import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TurnOnIntake extends Command {
	
	public TurnOnIntake() {
		requires(Robot.intake);
	}
	
	protected void initialize() {
		Robot.intake.set(Robot.tuning.getIntakeRegularOutput());
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}
	
}
