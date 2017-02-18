package org.team1540.robot2017.commands;

import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SetGearSliderReferencePositionOnTrip extends Command {
	private boolean prevTrip = false;
	
    public SetGearSliderReferencePositionOnTrip() {
    	requires(Robot.gearMechanism);
    }

    protected void initialize() {
    }

    protected void execute() {
    }

    protected boolean isFinished() {
    	return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
