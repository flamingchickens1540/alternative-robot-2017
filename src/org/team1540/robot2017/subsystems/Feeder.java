package org.team1540.robot2017.subsystems;

import org.team1540.robot2017.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Feeder extends Subsystem {

	private final CANTalon feederConveyor = new CANTalon(RobotMap.feederConveyor);
	private final CANTalon leftFeederTalon = new CANTalon(RobotMap.feederFunnelingRollerLeft);
	private final CANTalon rightFeederTalon = new CANTalon(RobotMap.feederFunnelingRollerLeft);
	
	public Feeder() {
		feederConveyor.changeControlMode(TalonControlMode.PercentVbus);
		leftFeederTalon.changeControlMode(TalonControlMode.PercentVbus);
		rightFeederTalon.changeControlMode(TalonControlMode.PercentVbus);
		rightFeederTalon.reverseOutput(true);
		leftFeederTalon.reverseSensor(true);
	}
	
//	public void set(double topFeederOutput, double leftFeederOutput, double rightFeederOutput) {
//		topFeederTalon.set(topFeederOutput);
//		leftFeederTalon.set(leftFeederOutput);
//		rightFeederTalon.set(rightFeederOutput);
//	}
	
	public void setTop(double output) {
		feederConveyor.set(output);
	}
	
	public void setLeft(double output) {
		leftFeederTalon.set(output);
	}
	
	public void setRight(double output) {
		rightFeederTalon.set(output);
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}
}
