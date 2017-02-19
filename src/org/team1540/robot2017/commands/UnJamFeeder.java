package org.team1540.robot2017.commands;

import org.team1540.robot2017.OI;
import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class UnJamFeeder extends Command {	
	public UnJamFeeder() {
		requires(Robot.shooter);
		requires(Robot.feeder);
		requires(Robot.belt);
		requires(Robot.intake);
	}
	
	protected void initialize() {
		Robot.shooter.stop();
		Robot.feeder.setTop(0);
		Robot.feeder.setLeft(0);
		Robot.feeder.setRight(0);
		Robot.belt.set(0);
		Robot.intake.set(0);
		Robot.feeder.setTop(-Robot.tuning.getFeederTopOutput());
		Robot.feeder.setLeft(-Robot.tuning.getFeederSideOutput());
		Robot.feeder.setRight(-Robot.tuning.getFeederSideOutput());
	}
	
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return !OI.buttonUnJam.get();
	}
	
	protected void end() {
		Robot.feeder.setTop(0.0);
		Robot.feeder.setLeft(0.0);
		Robot.feeder.setRight(0.0);
	}
}
