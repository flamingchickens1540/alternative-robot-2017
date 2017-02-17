package org.team1540.robot2017;

import com.ctre.CANTalon;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	public static final int climberTalonTop = 12;
	public static final int climberTalonBottom = 11;
	
	public static final int gearSliderTalon = 3;
//	public static final int gearServoRightDeploy;
//	public static final int gearServoLeftDeploy;
	
	public static final int intakeTalon = 16;
	
	public static final int feederTalonTop = 15;
	public static final int feederTalonRight = 4;
	public static final int feederTalonLeft = 5;
	
	public static final int beltTalon = 2;
	
	public static final int shooterTalonRightFlywheel = 10;
	public static final int shooterTalonLeftFlywheel = 13;
	
	public static final int driveTalonRightA = 6;
	public static final int driveTalonRightB = 9;
	public static final int driveTalonRightC = 14;
	public static final int driveTalonLeftA = 1;
	public static final int driveTalonLeftB = 7;
	public static final int driveTalonLeftC = 8;
	
}