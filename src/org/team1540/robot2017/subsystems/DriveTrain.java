package org.team1540.robot2017.subsystems;

import org.team1540.robot2017.RobotMap;
import org.team1540.robot2017.commands.IdleDrive;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {
	
	private final CANTalon driveRightTalon = new CANTalon(RobotMap.driveTalonRightA);
	private final CANTalon driveRightBTalon = new CANTalon(RobotMap.driveTalonRightB);
	private final CANTalon driveRightCTalon = new CANTalon(RobotMap.driveTalonRightC);
	private final CANTalon driveLeftTalon = new CANTalon(RobotMap.driveTalonLeftA);
	private final CANTalon driveLeftBTalon = new CANTalon(RobotMap.driveTalonLeftB);
	private final CANTalon driveLeftCTalon = new CANTalon(RobotMap.driveTalonLeftC);
	
	public DriveTrain() {
		driveRightTalon.changeControlMode(TalonControlMode.PercentVbus);
		driveRightBTalon.changeControlMode(TalonControlMode.Follower);
		driveRightCTalon.changeControlMode(TalonControlMode.Follower);
		driveLeftTalon.changeControlMode(TalonControlMode.PercentVbus);
		driveLeftBTalon.changeControlMode(TalonControlMode.Follower);
		driveLeftCTalon.changeControlMode(TalonControlMode.Follower);
		driveRightBTalon.set(driveRightTalon.getDeviceID());
		driveRightCTalon.set(driveRightTalon.getDeviceID());
		driveLeftBTalon.set(driveLeftTalon.getDeviceID());
		driveLeftCTalon.set(driveLeftTalon.getDeviceID());
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new IdleDrive());
	}
	
	public void tankDrive(double left, double right) {
		driveRightTalon.set(right);
		driveLeftTalon.set(left);
	}
}
