package org.team1540.robot2017.commands;

import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PickUpGear extends CommandGroup {
    
    public PickUpGear() {
        requires(Robot.gearWrist);
        requires(Robot.gearRollers);
        addSequential(new Command() {
            @Override
            protected void initialize() {
                Robot.gearWrist.setWrist(Robot.tuning.getGearWristOutput());
//                Robot.gearRollers.setRollers(-Robot.tuning.getGearRollerOutput());
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
        addSequential(new Command() {
            private long startTime;
            @Override
            protected void initialize() {
                Robot.gearRollers.setRollers(-Robot.tuning.getGearRollerOutput());
                startTime = System.currentTimeMillis();
            }
            @Override
            protected void execute() {
                SmartDashboard.putNumber("Gear Roller Current", Robot.gearRollers.getRollerCurrent());
            }
            @Override
            protected void end() {
                Robot.gearRollers.stop();
            }
            @Override
            protected boolean isFinished() {
                return Robot.gearRollers.rollerCurrentTooHigh() && 
                        System.currentTimeMillis() - startTime > Robot.tuning.getGearRollerTurnOnMillis();
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
