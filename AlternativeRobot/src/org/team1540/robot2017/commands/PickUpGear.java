package org.team1540.robot2017.commands;

import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class PickUpGear extends CommandGroup {
    
    public PickUpGear() {
        requires(Robot.gearWrist);
        requires(Robot.gearRollers);
        addSequential(new Command() {
            private long startTime;
            
            @Override
            protected void initialize() {
                Robot.gearWrist.setWrist(Robot.tuning.getGearWristOutput());
                Robot.gearRollers.setRollers(-Robot.tuning.getGearRollerOutput());
                startTime = System.currentTimeMillis();
            }
            @Override
            protected void end() {
                Robot.gearWrist.stop();
            }
            @Override
            protected boolean isFinished() {
                return (System.currentTimeMillis() - startTime) > ((long) (Robot.tuning.getGearRollerCurrentWait() * 1000)) 
                        && Robot.gearWrist.wristCurrentTooHigh();
            }
        });
        addSequential(new Command() {
            @Override
            protected void initialize() {
//                Robot.gearRollers.setRollers(-Robot.tuning.getGearRollerOutput());
            }
            
            @Override
            protected void end() {
                Robot.gearRollers.stop();
            }
            @Override
            protected boolean isFinished() {
                return Robot.gearRollers.rollerCurrentTooHigh();
            }
        });
        addSequential(new Command() {
            @Override
            protected void initialize() {
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
        });
    }
    
}
