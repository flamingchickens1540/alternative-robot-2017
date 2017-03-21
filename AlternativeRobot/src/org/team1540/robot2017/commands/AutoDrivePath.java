package org.team1540.robot2017.commands;

import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoDrivePath extends CommandGroup {
    
    public AutoDrivePath() {
        addSequential(new TurnDegrees(Robot.tuning.getAutoDegreesToTurn()));
    }

}
