package org.team1540.robot2017;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    
    // Joysticks
    public static final Joystick driver = new Joystick(0);
    public static final Joystick copilot = new Joystick(1);
    
    // Axes
    private static final int rightAxisY = 5;
    private static final int leftAxisX = 0;
    private static final int leftAxisY = 1;

    private static final int rightTrigger = 3;
    private static final int leftTrigger = 2;
    
    // Buttons
    public static final JoystickButton buttonSpinup = new JoystickButton(copilot, 1);
    public static final JoystickButton buttonFire = new JoystickButton(copilot, 6);
    public static final JoystickButton buttonSpindown = new JoystickButton(copilot, 2);
    public static final JoystickButton buttonUnJam = new JoystickButton(copilot, 7);

    public static final JoystickButton buttonIntakeOn = new JoystickButton(copilot, 3);
    
    public static final JoystickButton buttonToggleGearServos = new JoystickButton(copilot, 4);
    
    public static final JoystickButton buttonTest = new JoystickButton(copilot, 5);
    
    public static final JoystickButton buttonSelfTest = new JoystickButton(driver, 7);

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
    
    public static double getDriveRightTrigger() {
        return -driver.getRawAxis(rightTrigger);
    }
    
    public static double getDriveLeftTrigger() {
        return driver.getRawAxis(leftTrigger);
    }

    // GearMechanism
    public static double getGearSliderJoystick() {
        return copilot.getRawAxis(leftAxisX);
    }

}
