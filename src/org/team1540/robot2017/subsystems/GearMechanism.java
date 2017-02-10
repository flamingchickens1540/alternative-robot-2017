package org.team1540.robot2017.subsystems;

import org.team1540.robot2017.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

public class GearMechanism extends Subsystem {

	private final CANTalon gearSliderTalon = new CANTalon(RobotMap.gearSliderTalon);
//	private final Servo gearRightDeployServo = new Servo(RobotMap.gearServoRightDeploy);
//	private final Servo gearLeftDeployServo = new Servo(RobotMap.gearServoLeftDeploy);
	
	@Override
	protected void initDefaultCommand() {
		
	}
}
