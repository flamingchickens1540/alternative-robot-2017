package org.team1540.robot2017.commands;

import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class PickUpGear extends CommandGroup {
    
    public PickUpGear() {
        requires(Robot.gearWrist);
        requires(Robot.gearRollers);
        addSequential(new Command() {    // wrist down         
            @Override
            protected void initialize() {
                Robot.gearWrist.setWrist(Robot.tuning.getGearWristOutput());
                Robot.gearRollers.setRollers(-Robot.tuning.getGearRollerOutput());
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
        addSequential(new Command() { // actually pick up the gear
            private long startTime;
            @Override
            protected void initialize() {
                startTime = System.currentTimeMillis();
//                Robot.gearRollers.setRollers(-Robot.tuning.getGearRollerOutput());
                Robot.gearWrist.setWrist(Robot.tuning.getGearWristIdle());
            }
            @Override
            protected void end() {
                Robot.gearRollers.stop();
                Robot.gearWrist.stop();
            }
            @Override
            protected boolean isFinished() {
                return (System.currentTimeMillis() - startTime) > ((long) (Robot.tuning.getGearRollerCurrentWait() * 1000))
                        && Robot.gearRollers.rollerCurrentTooHigh();
            }
        });
        addSequential(new Command() { // wrist up
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
