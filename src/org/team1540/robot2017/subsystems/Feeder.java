package org.team1540.robot2017.subsystems;

import org.team1540.robot2017.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Feeder extends Subsystem {

	private CANTalon topFeederTalon = new CANTalon(RobotMap.feederTalonTop);
	private CANTalon leftFeederTalon = new CANTalon(RobotMap.feederTalonLeft);
	private CANTalon rightFeederTalon = new CANTalon(RobotMap.feederTalonRight);
	
	@Override
	protected void initDefaultCommand() {
		
	}
}
