package org.team1540.robot2017.commands;

import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ResetGearSliderPosition extends Command {

    private boolean trippedLimitSwitch = false;
    private boolean calibrated = false;

    public ResetGearSliderPosition() {
        requires(Robot.gearMechanism);
    }

    @Override
    protected void initialize() {
        trippedLimitSwitch = false;
        calibrated = false;
    }

    @Override
    protected void execute() {
        // if (Robot.gearMechanism.getRightLimitSwitch()) {
        // trippedLimitSwitch = true;
        // }
        //
        // if (!Robot.gearMechanism.getRightLimitSwitch() && !trippedLimitSwitch
        // && !calibrated) {
        // Robot.gearMechanism.slider(-0.7);
        // } else if (Robot.gearMechanism.getRightLimitSwitch() && !calibrated)
        // {
        // Robot.gearMechanism.slider(+0.2);
        // } else if (!Robot.gearMechanism.getRightLimitSwitch() &&
        // trippedLimitSwitch && !calibrated) {
        // Robot.gearMechanism.slider(0);
        // Robot.gearMechanism.initializeSliderEncoder();
        // calibrated = true;
        // }
    }

    @Override
    protected boolean isFinished() {
        // return calibrated;
        return true;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }
}