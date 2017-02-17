package org.team1540.robot2017.subsystems;

import org.team1540.robot2017.RobotMap;
import org.team1540.robot2017.commands.IdleDrive;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {
	
	public final CANTalon driveRightTalon = new CANTalon(RobotMap.driveTalonRightA);
	public final CANTalon driveRightBTalon = new CANTalon(RobotMap.driveTalonRightB);
	public final CANTalon driveRightCTalon = new CANTalon(RobotMap.driveTalonRightC);
	public final CANTalon driveLeftTalon = new CANTalon(RobotMap.driveTalonLeftA);
	public final CANTalon driveLeftBTalon = new CANTalon(RobotMap.driveTalonLeftB);
	public final CANTalon driveLeftCTalon = new CANTalon(RobotMap.driveTalonLeftC);
	
	public DriveTrain() {
		driveRightTalon.changeControlMode(TalonControlMode.PercentVbus);
		driveRightBTalon.changeControlMode(TalonControlMode.Follower);
		driveRightCTalon.changeControlMode(TalonControlMode.Follower);
//		driveRightTalon.reverseOutput(true);
		driveLeftTalon.changeControlMode(TalonControlMode.PercentVbus);
		driveLeftBTalon.changeControlMode(TalonControlMode.Follower);
		driveLeftCTalon.changeControlMode(TalonControlMode.Follower);
		driveRightBTalon.set(driveRightTalon.getDeviceID());
		driveRightCTalon.set(driveRightTalon.getDeviceID());
		driveLeftBTalon.set(driveLeftTalon.getDeviceID());
		driveLeftCTalon.set(driveLeftTalon.getDeviceID());
		
        driveRightTalon.configNominalOutputVoltage(+0f, -0f);
        driveRightTalon.configPeakOutputVoltage(+12f, -12f);
        driveRightTalon.setAllowableClosedLoopErr(0);
        driveRightTalon.setProfile(0);
        driveRightTalon.ClearIaccum();
        
        driveLeftTalon.configNominalOutputVoltage(+0f, -0f);
        driveLeftTalon.configPeakOutputVoltage(+12f, -12f);
        driveLeftTalon.setAllowableClosedLoopErr(0);
        driveLeftTalon.setProfile(0);
        driveLeftTalon.ClearIaccum();
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new IdleDrive());
	}
	
	public void setAll(double output) {
		driveRightTalon.set(output);
		driveRightBTalon.set(output);
		driveRightCTalon.set(output);
		driveLeftTalon.set(output);
		driveLeftBTalon.set(output);
		driveLeftCTalon.set(output);
	}
	
	public void setRight(double output) {
		driveRightTalon.set(output);
		driveRightBTalon.set(output);
		driveRightCTalon.set(output);
	}
	
	public void setLeft(double output) {
		driveLeftTalon.set(output);
		driveLeftBTalon.set(output);
		driveLeftCTalon.set(output);
	}
	
	/**
	 * Sets all the motors on the left and the right side to be slaves to their respective
	 * encoder equipped motors.
	 * This is particularly useful when using PID on the encoder equipped motors.
	 */
	public void setControlModeAsFollower() {
		//hard coding is stupid but Amber told me to do it
		driveRightBTalon.changeControlMode(CANTalon.TalonControlMode.Follower);
		driveRightCTalon.changeControlMode(CANTalon.TalonControlMode.Follower);
		driveLeftBTalon.changeControlMode(CANTalon.TalonControlMode.Follower);
		driveLeftCTalon.changeControlMode(CANTalon.TalonControlMode.Follower);
		driveRightBTalon.set(driveRightTalon.getDeviceID());
		driveRightCTalon.set(driveRightTalon.getDeviceID());
		driveLeftBTalon.set(driveLeftTalon.getDeviceID());
		driveLeftCTalon.set(driveLeftTalon.getDeviceID());
	}
	
	/**
	 * Sets all the motors to use current as the control method.
	 */
	public void setControlModeAsCurrent() {
		//hard coding is stupid but Amber told me to do it
		driveRightTalon.changeControlMode(CANTalon.TalonControlMode.Current);
		driveRightBTalon.changeControlMode(CANTalon.TalonControlMode.Current);
		driveRightCTalon.changeControlMode(CANTalon.TalonControlMode.Current);
		driveLeftTalon.changeControlMode(CANTalon.TalonControlMode.Current);
		driveLeftBTalon.changeControlMode(CANTalon.TalonControlMode.Current);
		driveLeftCTalon.changeControlMode(CANTalon.TalonControlMode.Current);
	}
	
	public void tankDrive(double left, double right) {
		driveRightTalon.set(right);
		driveLeftTalon.set(left);
	}
}