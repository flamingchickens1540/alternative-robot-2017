package org.team1540.robot2017.subsystems;

import org.team1540.robot2017.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {

	private final CANTalon shooterFlywheelTalon = new CANTalon(RobotMap.shooterRightFlywheel);
	private final CANTalon shooterLeftFlywheelTalon = new CANTalon(RobotMap.shooterLeftFlywheel);
	private final CANTalon shooterBeltTalon = new CANTalon(RobotMap.feederBelt);
	
	public Shooter() {
		shooterLeftFlywheelTalon.changeControlMode(TalonControlMode.Follower);
		shooterLeftFlywheelTalon.set(shooterFlywheelTalon.getDeviceID());
		shooterLeftFlywheelTalon.reverseOutput(true);
		shooterFlywheelTalon.changeControlMode(TalonControlMode.Speed);
		shooterFlywheelTalon.configEncoderCodesPerRev(125 * 35);
	}
	
	public void setPID(double p, double i, double d) {
		shooterFlywheelTalon.setPID(p, i, d);
	}
	
	public double getP() {
		return shooterFlywheelTalon.getP();
	}

	public double getI() {
		return shooterFlywheelTalon.getP();
	}
	
	public double getD() {
		return shooterFlywheelTalon.getP();
	}
	
	public double getF() {
		return shooterFlywheelTalon.getP();
	}
	
	public void setSpeed(double revsPerSec) {
		shooterFlywheelTalon.set(revsPerSec);
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}
}

