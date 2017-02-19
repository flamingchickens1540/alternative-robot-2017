package org.team1540.robot2017.subsystems;

import org.team1540.robot2017.Robot;
import org.team1540.robot2017.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Belt extends Subsystem {
	
	private final CANTalon beltTalon = new CANTalon(RobotMap.beltTalon);
	
	public Belt() {
		beltTalon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		beltTalon.reverseSensor(true);
		beltTalon.configNominalOutputVoltage(+0f, -0f);
		beltTalon.configPeakOutputVoltage(+12f, -12f);
		beltTalon.configEncoderCodesPerRev(125 * 15);
		beltTalon.setAllowableClosedLoopErr(0);
		beltTalon.setProfile(0);
		beltTalon.ClearIaccum();
		beltTalon.enable();
		beltTalon.setP(Robot.tuning.getBeltP());
		beltTalon.setI(Robot.tuning.getBeltI());
		beltTalon.setD(Robot.tuning.getBeltD());
		beltTalon.setF(Robot.tuning.getBeltF());
	}
	
//	public void set(double output) {
//		beltTalon.set(output);
//	}
	
	public void setSpeed(double rpm) {
		beltTalon.changeControlMode(TalonControlMode.Speed);
		beltTalon.setSetpoint(rpm);
	}
	
	public void stop() {
		beltTalon.changeControlMode(TalonControlMode.PercentVbus);
		beltTalon.set(0);
	}
	
	public double getPIDOutput() {
		return beltTalon.pidGet();
	}
	
	public double getSpeed() {
		return beltTalon.getSpeed();
	}
	
	public double getClosedLoopError() {
		return beltTalon.getClosedLoopError();
	}
	
	public double getMotorOutput() {
		return beltTalon.getOutputVoltage() / beltTalon.getBusVoltage();
	}
	
	public double getCurrent() {
		return beltTalon.getOutputCurrent();
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}
	
}
