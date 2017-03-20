package org.team1540.robot2017.commands;

import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class PickUpGear extends CommandGroup {
    
    public PickUpGear() {
        requires(Robot.gearMechanism);
        addSequential(new Command() {
            @Override
            protected void initialize() {
                Robot.gearMechanism.setWrist(Robot.tuning.getGearWristOutput());
                Robot.gearMechanism.setRollers(-Robot.tuning.getGearRollerOutput());
            }
            @Override
            protected void end() {
                Robot.gearMechanism.setWrist(0);
            }
            @Override
            protected boolean isFinished() {
                return Robot.gearMechanism.wristCurrentTooHigh();
            }
        });
        addSequential(new Command() {
            @Override
            protected void end() {
                Robot.gearMechanism.stop();
            }
            @Override
            protected boolean isFinished() {
                return Robot.gearMechanism.rollerCurrentTooHigh();
            }
        });
        addSequential(new Command() {
            @Override
            protected void initialize() {
                Robot.gearMechanism.setWrist(-Robot.tuning.getGearWristOutput());
            }
            @Override
            protected void end() {
                Robot.gearMechanism.stop();
            }
            @Override
            protected boolean isFinished() {
                return Robot.gearMechanism.wristCurrentTooHigh();
            }
        });
    }
    
}
