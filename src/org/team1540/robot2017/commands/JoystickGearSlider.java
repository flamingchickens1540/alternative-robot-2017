package org.team1540.robot2017.commands;

import org.team1540.robot2017.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class JoystickGearSlider extends Command {
	private boolean prevTrip = false;

	public JoystickGearSlider() {
		requires(Robot.gearMechanism);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
//		Robot.gearMechanism.joySlider(RobotUtil.deadzone(OI.getGearSliderJoystick(), 0.2) * 0.6);
//
//    	SmartDashboard.putNumber("gear position", Robot.gearMechanism.getSliderEncoder());
//    	
//    	if (prevTrip != Robot.gearMechanism.getRightLimitSwitch()) {
//    		Robot.gearMechanism.initializeSliderEncoder();
//    	}
//    	
//    	prevTrip = Robot.gearMechanism.getRightLimitSwitch();
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