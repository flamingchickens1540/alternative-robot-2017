package org.team1540.robot2017.commands.auto;

import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCrossLineBlue extends CommandGroup {
    
    public AutoCrossLineBlue() {
        addSequential(new DriveForTime(3, Robot.tuning.getAutoLineDrive1Set()));
    }
}
