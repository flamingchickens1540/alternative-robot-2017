package org.team1540.robot2017.commands;

import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class RunMotionProfile extends Command {
    public RunMotionProfile() {
        requires(Robot.driveTrain);
    }
    
    @Override
    protected void initialize() {
        Robot.driveTrain.setPIDLeft(0.1, 1.0e-5, 0.1, 0.09);
        Robot.driveTrain.setPIDRight(0.1, 1.0e-5, 0.1, 0.09);
        Robot.driveTrain.startMotionProfile();
    }
    
    @Override
    protected void execute() {
        Robot.driveTrain.controlMotionProfile();
    }

    @Override
    protected void end() {
        Robot.driveTrain.stopMotionProfile();
    }
    
    @Override
    public boolean isFinished() {
        return false;
    }
}
