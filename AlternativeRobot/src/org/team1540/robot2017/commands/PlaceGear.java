package org.team1540.robot2017.commands;

import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.TimedCommand;

public class PlaceGear extends CommandGroup {

    public PlaceGear() {
        requires(Robot.gearWrist);
        requires(Robot.gearRollers);
        addParallel(new Command() {
            @Override
            protected void initialize() {
                Robot.gearRollers.setRollers(Robot.tuning.getGearRollerOutput());
            }
            @Override
            protected void end() {
                Robot.gearRollers.stop();
            }
            @Override
            protected boolean isFinished() {
                return false;
            }
        });
        addParallel(new Command() {
            @Override
            protected void initialize() {
                Robot.gearWrist.setWrist(Robot.tuning.getGearWristOutput());
            }
            protected void end() {
                Robot.gearWrist.stop();
            }
            @Override
            protected boolean isFinished() {
                return false;
            }
        });
    }

}
