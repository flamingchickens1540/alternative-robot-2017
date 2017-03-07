package org.team1540.robot2017.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoGearLeft extends CommandGroup {
    public AutoGearLeft() {
        addSequential(new DriveDistance(0.3));
//        addSequential(new TurnDegrees(6));
    }
}
