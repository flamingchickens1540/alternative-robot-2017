package org.team1540.robot2017.subsystems;

import org.team1540.robot2017.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Feeder extends Subsystem {

	private final CANTalon feederConveyor = new CANTalon(RobotMap.feederConveyor);
	private final CANTalon leftFeederTalon = new CANTalon(RobotMap.feederFunnelingRollerLeft);
	private final CANTalon rightFeederTalon = new CANTalon(RobotMap.feederFunnelingRollerLeft);
	
	@Override
	protected void initDefaultCommand() {
		
	}
}
