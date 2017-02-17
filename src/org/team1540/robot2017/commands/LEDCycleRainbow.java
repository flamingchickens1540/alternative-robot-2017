package org.team1540.robot2017.commands;

import org.team1540.robot2017.OI;
import org.team1540.robot2017.Robot;
import org.team1540.robot2017.subsystems.LEDs;

import edu.wpi.first.wpilibj.command.Command;

public class LEDCycleRainbow extends Command {
	public LEDCycleRainbow() {
		requires(Robot.leds);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		int greenValue = Robot.leds.getGreen()+1;
		if (greenValue>=255)
			greenValue = 0;
		Robot.leds.setRGB(254, greenValue, 254);
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