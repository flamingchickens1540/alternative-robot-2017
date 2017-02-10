package org.team1540.robot2017.subsystems;

import org.team1540.robot2017.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {

	private final CANTalon shooterRightFlywheelTalon = new CANTalon(RobotMap.shooterTalonRightFlywheel);
	private final CANTalon shooterLeftFlywheelTalon = new CANTalon(RobotMap.shooterTalonLeftFlywheel);
	private final CANTalon shooterBeltTalon = new CANTalon(RobotMap.shooterTalonBelt);
	
	@Override
	protected void initDefaultCommand() {
		
	}
}

