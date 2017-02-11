package org.team1540.robot2017;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static final Joystick primary = new Joystick(0);
	public static final Joystick secondary = new Joystick(1);
	
	public static final int primaryRightAxis = 5;
	public static final int primaryLeftAxis = 1;
	
	public static final int primaryRightTrigger = 3;
	public static final int primaryLeftTrigger = 2;
}
