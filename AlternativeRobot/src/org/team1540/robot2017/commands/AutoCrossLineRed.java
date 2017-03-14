package org.team1540.robot2017.commands;

import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCrossLineRed extends CommandGroup {
    
    public AutoCrossLineRed() {
        addSequential(new DriveForTime(Robot.tuning.getAutoLineDrive1Secs(), Robot.tuning.getAutoLineDrive1Set()));
        addSequential(new TurnForTime(Robot.tuning.getAutoLineTurnSecs(), -Robot.tuning.getAutoLineTurnSet()));
        addSequential(new DriveForTime(Robot.tuning.getAutoLineDrive2Secs(), Robot.tuning.getAutoLineDrive2Set()));
    }
}
