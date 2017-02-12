package org.team1540.robot2017.commands;
import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ResetGearSliderPosition extends Command {

    public ResetGearSliderPosition() {
    }

    protected void initialize() {
    	Robot.gearMechanism.initializeCounter();
    	Robot.gearMechanism.disableJoystickControl();
    	Robot.gearMechanism.sliderSlowCalib();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return Robot.gearMechanism.isSwitchSet();
    }

    protected void end() {
    	Robot.gearMechanism.sliderStop();
    	Robot.gearMechanism.initializeSliderEncoder();
    	Robot.gearMechanism.enableJoystickControl();
    }

    protected void interrupted() {
    	Robot.gearMechanism.sliderStop();
    	Robot.gearMechanism.enableJoystickControl();
        end();
    }
}