package org.team1540.robot2017;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	// Climber
	public static final int climberTop = 12;
	public static final int climberBottom = 11;

	// Feeder
	public static final int feederBelt = 2;
	public static final int feederConveyor = 15;
	public static final int feederFunnelingRollerRight = 4;
	public static final int feederFunnelingRollerLeft = 5;

	// GearMechanism
	public static final int gearSliderTalon = 3;
	public static final int gearServoRight = 7;
	public static final int gearServoLeft = 6;
	public static final int gearLeftLimitSwitch = 1;
	public static final int gearRightLimitSwitch = 0;

	// Intake
	public static final int intakeRollers = 16;

	// Shooter
	public static final int shooterRightFlywheel = 10;
	public static final int shooterLeftFlywheel = 13;

	// DriveTrain
	public static final int driveTalonRightA = 6;
	public static final int driveTalonRightB = 9;
	public static final int driveTalonRightC = 14;
	public static final int driveTalonLeftA = 7;
	public static final int driveTalonLeftB = 1;
	public static final int driveTalonLeftC = 8;

	// RGB LEDs
	public static int redPWM = 2;
	public static int greenPWM = 3;
	public static int bluePWM = 1;

	public static final int climberTalonTop = 12;
	public static final int climberTalonBottom = 11;

	public static final int intakeTalon = 16;

	public static final int feederTalonTop = 15;
	public static final int feederTalonRight = 4;
	public static final int feederTalonLeft = 5;

	public static final int beltTalon = 2;

}
