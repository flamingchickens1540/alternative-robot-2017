package org.team1540.robot2017.commands;

import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SetGearSliderReferencePositionOnTrip extends Command {
    private boolean prevTrip = false;

    public SetGearSliderReferencePositionOnTrip() {
        requires(Robot.gearMechanism);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }
}
