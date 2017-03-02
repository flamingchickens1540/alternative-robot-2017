package org.team1540.robot2017.commands;

import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoShootAndCrossLine extends CommandGroup {
    
    public AutoShootAndCrossLine() {
        addSequential(new ShootForTime(Robot.tuning.getAutoShootingSeconds()));
        addSequential(new DriveForTime(Robot.tuning.getAutoLineDriveBackwardSecs(), 
                Robot.tuning.getAutoLineDrivePower()));
        addSequential(new TurnDegrees(Robot.tuning.getAutoLineDegreesToTurn()));
        addSequential(new DriveForTime(Robot.tuning.getAutoLineDriveForwardSecs(), 
                Robot.tuning.getAutoLineDrivePower()));
    }
    
}
