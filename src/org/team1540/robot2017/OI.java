package org.team1540.robot2017;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static final Joystick driver = new Joystick(0);
	public static final Joystick copilot = new Joystick(1);
	
	public static final int driverRightAxis = 5;
	public static final int driverLeftAxis = 1;
	
	public static final JoystickButton buttonSpinup = new JoystickButton(driver, 1);
	public static final JoystickButton buttonFire = new JoystickButton(driver, 6);
	public static final JoystickButton buttonSpinupFire = new JoystickButton(driver, 4);
	public static final JoystickButton buttonSpindown = new JoystickButton(driver, 2);
	
	public static final JoystickButton buttonIntakeOn = new JoystickButton(driver, 3);
//	public static final JoystickButton buttonIntakeOff = new JoystickButton(driver, 4);
	
//	public static final JoystickButton buttonTest = new JoystickButton(driver, 6);
	
}
