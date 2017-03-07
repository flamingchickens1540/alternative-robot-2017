package org.team1540.robot2017.commands;

import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TurnHopperOff extends Command {
    public TurnHopperOff() {
        requires(Robot.shooter);
        requires(Robot.feeder);
        requires(Robot.belt);
        requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.shooter.stop();
        Robot.feeder.stop();
        Robot.belt.stop();
        Robot.intake.set(0);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return true;
    }
}
