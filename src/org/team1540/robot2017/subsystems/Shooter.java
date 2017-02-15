package org.team1540.robot2017.subsystems;

import org.team1540.robot2017.Robot;
import org.team1540.robot2017.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {

	private final CANTalon shooterFlywheelTalon = new CANTalon(RobotMap.shooterTalonLeftFlywheel);
	private final CANTalon shooterRightFlywheelTalon = new CANTalon(RobotMap.shooterTalonRightFlywheel);
	
	public Shooter() {
		shooterRightFlywheelTalon.reset();
        shooterRightFlywheelTalon.changeControlMode(TalonControlMode.Follower);
        shooterRightFlywheelTalon.set(shooterFlywheelTalon.getDeviceID());
        shooterRightFlywheelTalon.reverseOutput(true);

        shooterFlywheelTalon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
        shooterFlywheelTalon.reverseSensor(true);
        shooterFlywheelTalon.configNominalOutputVoltage(+0f, -0f);
        shooterFlywheelTalon.configPeakOutputVoltage(+12f, -12f);
        shooterFlywheelTalon.setAllowableClosedLoopErr(0);
        shooterFlywheelTalon.setProfile(0);
        shooterFlywheelTalon.ClearIaccum();
        shooterFlywheelTalon.enable();
        shooterFlywheelTalon.setF(1 / 26274);
        shooterFlywheelTalon.setP(0.07);
        shooterFlywheelTalon.setI(0.0001); 
        shooterFlywheelTalon.setD(0.05);
	}
	
	public void setPID(double p, double i, double d) {
		shooterFlywheelTalon.setPID(p, i, d);
	}
	
	public double getP() {
		return shooterFlywheelTalon.getP();
	}

	public double getI() {
		return shooterFlywheelTalon.getI();
	}
	
	public double getD() {
		return shooterFlywheelTalon.getD();
	}
	
	public double getF() {
		return shooterFlywheelTalon.getF();
	}
	
	public double getPIDOutput() {
		return shooterFlywheelTalon.pidGet();
	}
	
//	public void setFull() {
//		shooterFlywheelTalon.set(1);
//	}
	
	public void setSpeed(double rpm) {
		shooterFlywheelTalon.changeControlMode(TalonControlMode.Speed);
		shooterFlywheelTalon.setSetpoint(rpm);
	}
	
	public double getSpeed() {
		return shooterFlywheelTalon.getSpeed();
	}
	
	public double getSetpoint() {
		return shooterFlywheelTalon.getSetpoint();
	}
	
	public double getClosedLoopError() {
		return shooterFlywheelTalon.getClosedLoopError();
	}
	
	public double getMotorOutput() {
		return shooterFlywheelTalon.getOutputVoltage() / shooterFlywheelTalon.getBusVoltage();
	}
	
	public boolean upToSpeed() {
		return shooterFlywheelTalon.getSpeed() >= Robot.tuning.getShooterFlywheelSpeed(); ////////
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}
}
