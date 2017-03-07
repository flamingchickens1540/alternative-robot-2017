package org.team1540.robot2017.commands;

import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SpinupFlywheel extends Command {
    
    private String networkTableName;
    private double defaultValue;
    
    public SpinupFlywheel(String networkTableName, double defaultValue) {
        requires(Robot.shooter);
        this.networkTableName = networkTableName;
        this.defaultValue = defaultValue;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.shooter.setSpeed(SmartDashboard.getNumber(networkTableName, defaultValue) 
                + Robot.tuning.getFlywheelSpeedChangeCoefficient());
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return Robot.shooter.upToSpeed(SmartDashboard.getNumber(networkTableName, defaultValue));
    }

}
