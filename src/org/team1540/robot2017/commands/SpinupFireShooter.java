package org.team1540.robot2017.commands;

import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SpinupFireShooter extends Command {
	
//	public SpinupFireShooter() {
//		requires(Robot.shooter);
//		requires(Robot.feeder);
//		requires(Robot.belt);
//		requires(Robot.intake);
//	}
//	
//	@Override
//	protected void initialize() {
//		if (Robot.shooter.upToSpeed()) {
//			new FireShooter();
//		}
//		else {
//			new SpinupFlywheel();
//		}
//	}
//	
//	protected void execute() {
//		if (Robot.shooter.upToSpeed()) {
//			// fire
////			Robot.feeder.set(0.75, 0.65, 0.86);
//			Robot.belt.set(0.4);
//			Robot.intake.set(1);
//		}
//		else {
//			// spinup
////			Robot.shooter.setSpeed(SmartDashboard.getNumber("Flywheel Setpoint", 1000));
//			Robot.shooter.setSpeed(Robot.tuning.getShooterFlywheelSpeed());
//		}
////		Robot.shooter.set(1);
//	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}

}
