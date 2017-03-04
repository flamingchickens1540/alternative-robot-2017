
package org.team1540.robot2017.commands;

import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SpinupFlywheel extends Command {

    public SpinupFlywheel() {
        requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.shooter.setSpeed(Robot.tuning.getShooterFlywheelSpeed());
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return Robot.shooter.upToSpeed();
    }

}
