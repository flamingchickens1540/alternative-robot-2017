package org.team1540.robot2017.subsystems;

import org.team1540.robot2017.Robot;
import org.team1540.robot2017.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {

	private final CANTalon shooterFlywheelTalon = new CANTalon(RobotMap.shooterLeftFlywheel);
	private final CANTalon shooterRightFlywheelTalon = new CANTalon(RobotMap.shooterRightFlywheel);

	public Shooter() {
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
        shooterFlywheelTalon.setF(Robot.tuning.getFlywheelF());
        shooterFlywheelTalon.setP(Robot.tuning.getFlywheelP());
        shooterFlywheelTalon.setI(Robot.tuning.getFlywheelI());
        shooterFlywheelTalon.setD(Robot.tuning.getFlywheelD());
//        shooterFlywheelTalon.setP(0.14);
//        shooterFlywheelTalon.setI(0.0001);
//        shooterFlywheelTalon.setD(0.05);

	}

	public void setPID(double p, double i, double d) {
		shooterFlywheelTalon.setPID(p, i, d);
	}

	public void setF(double f) {
		shooterFlywheelTalon.setF(f);
	}

//	public double getP() {
//		return shooterFlywheelTalon.getP();
//	}
//
//	public double getI() {
//		return shooterFlywheelTalon.getI();
//	}
//
//	public double getD() {
//		return shooterFlywheelTalon.getD();
//	}
//
//	public double getF() {
//		return shooterFlywheelTalon.getF();
//	}

	public double getPIDOutput() {
		return shooterFlywheelTalon.pidGet();
	}

	public void setSpeed(double rpm) {
		shooterFlywheelTalon.changeControlMode(TalonControlMode.Speed);
		shooterFlywheelTalon.setSetpoint(rpm);
	}
	
	public void stop() {
		shooterFlywheelTalon.changeControlMode(TalonControlMode.PercentVbus);
		shooterFlywheelTalon.set(0);
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
		return Math.abs(shooterFlywheelTalon.getSpeed() / Robot.tuning.getShooterFlywheelSpeed() - 1) < 0.1;
	}

	public double getFlywheelCurrentL() {
		return shooterFlywheelTalon.getOutputCurrent();
	}

	public double getFlywheelCurrentR() {
		return shooterRightFlywheelTalon.getOutputCurrent();
	}
	
	@Override
	protected void initDefaultCommand() {

	}
}
