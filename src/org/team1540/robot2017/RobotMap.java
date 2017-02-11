package org.team1540.robot2017;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	// Climber
	public static final int climberTop = 5;
	public static final int climberBottom = 2;
	
	// Feeder
	public static final int feederBelt = 12;
	public static final int feederConveyor = 3;
	public static final int feederFunnelingRollerRight = 1;
	public static final int feederFunnelingRollerLeft = 7;
	
	// GearMechanism
	public static final int gearSliderTalon = 8;
//	public static final int gearServoRightDeploy;
//	public static final int gearServoLeftDeploy;
	
	// Intake
	public static final int intakeRollers = 6;
	
	// Shooter
	public static final int shooterRightFlywheel = 10;
	public static final int shooterLeftFlywheel = 9;
	
	// DriveTrain
	public static final int driveTalonRightA = 13;
	public static final int driveTalonRightB = 14;
	public static final int driveTalonRightC = 4;
	public static final int driveTalonLeftA = 11;
	public static final int driveTalonLeftB = 15;
	public static final int driveTalonLeftC = 16;
}
