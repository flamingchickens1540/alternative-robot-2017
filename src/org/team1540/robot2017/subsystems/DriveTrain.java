package org.team1540.robot2017.subsystems;

import org.team1540.robot2017.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {
	
	private final CANTalon driveRightATalon = new CANTalon(RobotMap.driveTalonRightA);
	private final CANTalon driveRightBTalon = new CANTalon(RobotMap.driveTalonRightB);
	private final CANTalon driveRightCTalon = new CANTalon(RobotMap.driveTalonRightC);
	private final CANTalon driveLeftATalon = new CANTalon(RobotMap.driveTalonLeftA);
	private final CANTalon driveLeftBTalon = new CANTalon(RobotMap.driveTalonLeftB);
	private final CANTalon driveLeftCTalon = new CANTalon(RobotMap.driveTalonLeftC);
	
	@Override
	protected void initDefaultCommand() {
		
	}
}
