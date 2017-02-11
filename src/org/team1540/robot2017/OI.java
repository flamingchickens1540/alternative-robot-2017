package org.team1540.robot2017;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	// Joysticks
	private static final Joystick primary = new Joystick(0);
	private static final Joystick secondary = new Joystick(1);
	
	private static final int rightAxisX = 5;
	private static final int rightAxisY = 5;
	private static final int leftAxisX = 1;
	private static final int leftAxisY = 1;
	
	private static final int rightTrigger = 3;
	private static final int leftTrigger = 2;
	
	// Climber
	public static double getClimberJoystick() {
		return secondary.getRawAxis(leftAxisY);
	}
	
	// DriveTrain
	public static double getDriveRightJoystick() {
		return primary.getRawAxis(rightAxisY);
	}
	public static double getDriveLeftJoystick() {
		return primary.getRawAxis(leftAxisY);
	}
	
}
