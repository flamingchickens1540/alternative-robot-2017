package org.team1540.robot2017.commands;

import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ResetGearMechanism extends Command {
    
    public ResetGearMechanism() {
        requires(Robot.gearMechanism);
    }
    
    protected void ititialize() {
        Robot.gearMechanism.stop();
        Robot.gearMechanism.setWrist(-Robot.tuning.getGearWristOutput());
    }
    
    protected void end() {
        Robot.gearMechanism.stop();
    }
    
    @Override
    protected boolean isFinished() {
        return Robot.gearMechanism.wristCurrentTooHigh();
    }
    
}
