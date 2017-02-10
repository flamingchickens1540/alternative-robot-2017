package org.team1540.robot2017.subsystems;

import org.team1540.robot2017.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {
	
	private CANTalon driveRightATalon = new CANTalon(RobotMap.driveTalonRightA);
	private CANTalon driveRightBTalon = new CANTalon(RobotMap.driveTalonRightB);
	private CANTalon driveRightCTalon = new CANTalon(RobotMap.driveTalonRightC);
	private CANTalon driveLeftATalon = new CANTalon(RobotMap.driveTalonLeftA);
	private CANTalon driveLeftBTalon = new CANTalon(RobotMap.driveTalonLeftB);
	private CANTalon driveLeftCTalon = new CANTalon(RobotMap.driveTalonLeftC);
	
	@Override
	protected void initDefaultCommand() {
		
	}
}
