package org.team1540.robot2017.subsystems;

import org.team1540.robot2017.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {
	private CANTalon climberTopTalon = new CANTalon(RobotMap.climberTalonTop);
	private CANTalon climberBottomTalon = new CANTalon(RobotMap.climberTalonBottom);

	@Override
	protected void initDefaultCommand() {
		
	} 
}
