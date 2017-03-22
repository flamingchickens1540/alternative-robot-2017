package org.team1540.robot2017.commands;

import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ResetGearMechanism extends Command {
    
    public ResetGearMechanism() {
        requires(Robot.gearWrist);
        requires(Robot.gearRollers);
    }
    
    @Override
    protected void initialize() {
        Robot.gearRollers.stop();
        Robot.gearWrist.setWrist(-Robot.tuning.getGearWristOutput());
    }
    
    @Override
    protected void end() {
        Robot.gearWrist.stop();
    }
    
    @Override
    protected boolean isFinished() {
        return Robot.gearWrist.wristCurrentTooHigh();
    }
    
}
