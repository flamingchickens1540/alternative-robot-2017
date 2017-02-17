package org.team1540.robot2017;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	// Joysticks
	public static final Joystick driver = new Joystick(0);
	public static final Joystick copilot = new Joystick(1);
	
//	private static final int rightAxisX = ???; // ??
	private static final int rightAxisY = 5;
//	private static final int leftAxisX = ???; // ??
	private static final int leftAxisY = 1;
	
	private static final int rightTrigger = 3;
	private static final int leftTrigger = 2;
	
//	private static final int rightBumper = (Integer) null;
//	private static final int leftBumper = (Integer) null;
//	
//	private static final int buttonX = (Integer) null;
//	private static final int buttonA = (Integer) null;
//	private static final int buttonY = (Integer) null;
//	private static final int buttonB = (Integer) null;
	
//	private static final int leftJoyPush = (Integer) null;
//	private static final int rightJoyPush = (Integer) null;
	
//	private static final int buttonN = (Integer) null;
//	private static final int buttonNE = (Integer) null;
//	private static final int buttonE = (Integer) null;
//	private static final int buttonSE = (Integer) null;
//	private static final int buttonS = (Integer) null;
//	private static final int buttonSW = (Integer) null;
//	private static final int buttonW = (Integer) null;
//	private static final int buttonNW = (Integer) null;

//	private static final int rightBumper = ???;
//	private static final int leftBumper = ???;
//	
//	private static final int buttonX = ???;
//	private static final int buttonA = ???;
//	private static final int buttonY = ???;
//	private static final int buttonB = ???;
//	
//	private static final int leftJoyPush = ???;
//	private static final int rightJoyPush = ???;
//	
//	private static final int buttonN = ???;
//	private static final int buttonNE = ???;
//	private static final int buttonE = ???;
//	private static final int buttonSE = ???;
//	private static final int buttonS = ???;
//	private static final int buttonSW = ???;
//	private static final int buttonW = ???;
//	private static final int buttonNW = ???;
	
	// Climber
	public static double getClimberJoystick() {
		return copilot.getRawAxis(rightAxisY);
	}
	
	// DriveTrain
	public static double getDriveRightJoystick() {
		return -driver.getRawAxis(rightAxisY);
	}
	public static double getDriveLeftJoystick() {
		return driver.getRawAxis(leftAxisY);
	}

	// GearMechanism
	public static double getGearSliderJoystick() {
		return copilot.getRawAxis(rightAxisY);
	}
}
