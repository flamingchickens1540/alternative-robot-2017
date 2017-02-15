package org.team1540.robot2017.commands;

import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class FireShooter extends Command {
	
	public FireShooter() {
		requires(Robot.feeder);
		requires(Robot.belt);
		requires(Robot.intake);
	}
	
	protected void initialize() {
//		Robot.feeder.set(1, 0.6, 1);
		Robot.feeder.setTop(1);
//		Robot.feeder.setLeft(0.6);
//		Robot.feeder.setRight(1);
		Robot.belt.set(1);
		Robot.intake.set(1);
	}
	
	protected void execute() {
		long time = System.currentTimeMillis();
//		boolean beltShouldBeOn = ((long) (time / Robot.tuning.getBeltPeriod()) % 2) == 1 ? true : false;
//		Robot.belt.set(beltShouldBeOn ? 1 : 0);
//		Robot.feeder.setTop(((long) (time / Robot.tuning.getFeederTopPeriod()) % 2) == 1 ? 1 : 0);
		boolean feederSide = ((long) (time / Robot.tuning.getFeederSideSwitchPeriod()) % 2) == 1 ? true : false;
		Robot.feeder.setLeft(feederSide == true ? 0.8 : 0);
		Robot.feeder.setRight(feederSide == true ? 0 : 0.8);
//		SmartDashboard.putNumber("Belt Output", Robot.belt.getOutput());
//		SmartDashboard.putNumber("Should Belt Be On", beltShouldBeOn ? 1 : 0);
		SmartDashboard.putNumber("Time", time);
//		SmartDashboard.putNumber("thing", (time / Robot.tuning.getBeltPeriod()) % 2);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
