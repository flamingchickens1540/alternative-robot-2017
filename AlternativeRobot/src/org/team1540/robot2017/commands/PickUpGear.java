package org.team1540.robot2017.commands;

import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class PickUpGear extends CommandGroup {
    
    public PickUpGear() {
        addSequential(new Command() {
            protected void initialize() {
                Robot.gearMechanism.setWrist(Robot.tuning.getGearWristOutput());
                Robot.gearMechanism.setRollers(-Robot.tuning.getGearRollerOutput());
            }
            protected void end() {
                Robot.gearMechanism.setWrist(0);
            }
            @Override
            protected boolean isFinished() {
                return Robot.gearMechanism.wristCurrentTooHigh();
            }
        });
        addSequential(new Command() {
            protected void end() {
                Robot.gearMechanism.stop();
            }
            @Override
            protected boolean isFinished() {
                return Robot.gearMechanism.rollerCurrentTooHigh();
            }
        });
        addSequential(new Command() {
            protected void initialize() {
                Robot.gearMechanism.setWrist(-Robot.tuning.getGearWristOutput());
            }
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
