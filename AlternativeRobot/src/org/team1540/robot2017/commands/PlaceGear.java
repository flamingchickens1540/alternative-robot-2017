package org.team1540.robot2017.commands;

import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class PlaceGear extends Command {

    /*
     * turn gear rollers backward
     * move wrist down (but not very far?) make it really smart and use a trigger or no?
     * turn gear rollers off
     * move wrist back up
     */

    public PlaceGear() {
        requires(Robot.gearMechanism);
    }

    protected void initialize() {
        Robot.gearMechanism.setRollers(Robot.tuning.getGearRollerOutput());
//        Robot.gearMechanism.setWrist(-Robot.tuning.getGearWristOutput());
    }
    protected void end() {
        Robot.gearMechanism.stop();
    }
    @Override
    protected boolean isFinished() {
        return false;
    }

}
