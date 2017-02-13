package org.team1540.robot2017.subsystems;

import org.team1540.robot2017.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {

	private final CANTalon shooterFlywheelTalon = new CANTalon(RobotMap.shooterTalonLeftFlywheel);
	private final CANTalon shooterRightFlywheelTalon = new CANTalon(RobotMap.shooterTalonRightFlywheel);
//	private final CANTalon shooterBeltTalon = new CANTalon(RobotMap.shooterTalonBelt);
	
	public Shooter() {
		shooterRightFlywheelTalon.changeControlMode(TalonControlMode.Follower);
		shooterRightFlywheelTalon.set(shooterFlywheelTalon.getDeviceID());
		shooterRightFlywheelTalon.reverseOutput(false);
		
		shooterFlywheelTalon.setEncPosition(0);
		shooterFlywheelTalon.configEncoderCodesPerRev(125 * 15);
		shooterFlywheelTalon.configNominalOutputVoltage(+0f, -0f);
		shooterFlywheelTalon.configPeakOutputVoltage(+12f, -12f);
		shooterFlywheelTalon.setAllowableClosedLoopErr(0);
		shooterFlywheelTalon.setProfile(0);
		shooterFlywheelTalon.setF(0.0125);
		shooterFlywheelTalon.setP(0.01);
		shooterFlywheelTalon.setI(0.01); 
		shooterFlywheelTalon.setD(0.01);
		shooterFlywheelTalon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		shooterFlywheelTalon.reverseSensor(false);
		shooterFlywheelTalon.reverseOutput(false);
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
		shooterFlywheelTalon.changeControlMode(TalonControlMode.Speed);
	}
	
	public double getSpeed() {
		return shooterFlywheelTalon.getSpeed();
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}
}
