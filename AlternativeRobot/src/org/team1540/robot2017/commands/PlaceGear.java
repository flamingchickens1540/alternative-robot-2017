package org.team1540.robot2017.commands;

import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class PlaceGear extends Command {

    public PlaceGear() {
        requires(Robot.gearMechanism);
    }

    @Override
    protected void initialize() {
        Robot.gearMechanism.setRollers(Robot.tuning.getGearRollerOutput());
    }
    
    @Override
    protected void end() {
        Robot.gearMechanism.stop();
    }
    
    @Override
    protected boolean isFinished() {
        return false;
    }

}
