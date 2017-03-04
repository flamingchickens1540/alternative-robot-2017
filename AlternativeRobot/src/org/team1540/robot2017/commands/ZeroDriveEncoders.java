package org.team1540.robot2017.commands;

import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ZeroDriveEncoders extends Command {
    
    public ZeroDriveEncoders() {
        requires(Robot.driveTrain);
    }
    
    protected void initialize() {
        Robot.driveTrain.zeroEncoders();
    }
    
    @Override
    protected boolean isFinished() {
        return true;
    }

}
