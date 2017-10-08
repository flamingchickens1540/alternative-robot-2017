package org.team1540.robot2017.commands.auto;

import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCrossLineRed extends CommandGroup {
    
    public AutoCrossLineRed() {
        addSequential(new DriveForTime(3, Robot.tuning.getAutoLineDrive1Set()));
    }
}
