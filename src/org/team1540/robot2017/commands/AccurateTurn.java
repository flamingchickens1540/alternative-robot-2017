package org.team1540.robot2017.commands;

import org.team1540.robot2017.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class AccurateTurn extends Command {

	//TODO make these tunable
	public double calibration = 1;
	public double p = 0.10;
	public double i = 0.00;
	public double d = 0.00;
	
	private Double lastValue = Double.NaN;
	
	private boolean isFinished = false;
	
	private NetworkTable table;
	private String valueKey;
	
	private CANTalon driveLeftTalon = Robot.driveTrain.driveLeftTalon;
	private CANTalon driveRightTalon = Robot.driveTrain.driveRightTalon;
	
	public AccurateTurn(String tableKey, String valueKey) {
		table = NetworkTable.getTable(tableKey);
		this.valueKey = valueKey;
		driveLeftTalon.reverseOutput(true);
		driveRightTalon.reverseOutput(true);
	}
	
	public AccurateTurn(String tableKey, String valueKey, String address) {
		table = NetworkTable.getTable(tableKey);
		this.valueKey = valueKey;
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.driveTrain.setControlModeAsFollower();
		
		driveLeftTalon.setP(p);
		driveLeftTalon.setI(i);
		driveLeftTalon.setD(d);
		driveRightTalon.setP(p);
		driveRightTalon.setI(i);
		driveRightTalon.setD(d);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		table.putNumber("setpointL", driveLeftTalon.getSetpoint());
		table.putNumber("setpointR", driveRightTalon.getSetpoint());
		table.putNumber("positionL", driveLeftTalon.getEncPosition());
		table.putNumber("positionR", driveRightTalon.getEncPosition());
		table.putNumber("errorL", driveLeftTalon.getError());
		table.putNumber("errorR", driveRightTalon.getError());
		
		//Note that this code assumes that the value of the degrees to turn will
		//change every time the camera updates it. While this should be fine in
		//practice, this may cause unintended side effects if used improperly
		if (lastValue == Double.NaN || lastValue!=table.getNumber(valueKey,0)) {
			lastValue = table.getNumber(valueKey,0);
			driveLeftTalon.set(
					(table.getNumber(valueKey,0)-driveLeftTalon.getPosition())
					*calibration);
			driveRightTalon.set(
					(table.getNumber(valueKey,0)-driveRightTalon.getPosition())
					*calibration);
		}
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
