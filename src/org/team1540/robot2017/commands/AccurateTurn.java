package org.team1540.robot2017.commands;

import org.team1540.robot2017.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class AccurateTurn extends Command {

	//TODO make these tunable
	public double calibration = 1;
	public double p = 0.0;
	public double i = 0.0;
	public double d = 0.0;
	
	private boolean isFinished = false;
	
	public String address = "";
	private NetworkTable table;
	private String valueKey;
	
	private CANTalon driveLeftTalon = Robot.driveTrain.driveLeftTalon;
	private CANTalon driveRightTalon = Robot.driveTrain.driveLeftTalon;
	
	public AccurateTurn(String tableKey, String valueKey) {
		NetworkTable.setIPAddress(address);
		NetworkTable.setClientMode();
		table = NetworkTable.getTable(tableKey);
		this.valueKey = valueKey;
	}
	
	public AccurateTurn(String tableKey, String valueKey, String address) {
		this.address = address;
		NetworkTable.setIPAddress(address);
		NetworkTable.setClientMode();
		table = NetworkTable.getTable(tableKey);
		this.valueKey = valueKey;
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.driveTrain.setControlModeAsFollower();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		driveLeftTalon.setP(p);
		driveLeftTalon.setI(i);
		driveLeftTalon.setD(d);
		driveRightTalon.setP(p);
		driveRightTalon.setI(i);
		driveRightTalon.setD(d);
		
		driveLeftTalon.setSetpoint(table.getNumber(valueKey,0)*calibration);
		driveLeftTalon.setSetpoint(-table.getNumber(valueKey,0)*calibration);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return isFinished;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.driveTrain.setControlModeAsCurrent();
		Robot.driveTrain.setAll(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
	
	/**
	 * Sets if the auto is finished.
	 * @param toSet If auto is finished
	 * @return Previous value
	 */
	public boolean setIsFinished(boolean toSet) {
		boolean wasFinished = isFinished;
		isFinished = toSet;
		return wasFinished;
	}
}
