package org.team1540.robot2017.commands;

import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCrossLine extends CommandGroup {
    
    public AutoCrossLine() {
//        addSequential(new ShootForTime(Robot.tuning.getAutoShootingSeconds()));
//        addSequential(new DriveForTime(Robot.tuning.getAutoLineDriveBackwardSecs(), 
//                Robot.tuning.getAutoLineDrivePower()));
//        addSequential(new DriveDistance(Robot.tuning.getAutoLineDriveBackwardDist()));
//        addSequential(new TurnDegrees(Robot.tuning.getAutoLineDegreesToTurn()));
//        addSequential(new DriveForTime(Robot.tuning.getAutoLineDriveForwardSecs(), 
//                Robot.tuning.getAutoLineDrivePower()));
//        addSequential(new DriveDistance(Robot.tuning.getAutoLineDriveForwardDist()));
        addSequential(new DriveForTime(Robot.tuning.getAutoLineDrive1Secs(), Robot.tuning.getAutoLineDrive1Set()));
        addSequential(new TurnForTime(Robot.tuning.getAutoLineTurnSecs(), Robot.tuning.getAutoLineTurnSet()));
        addSequential(new DriveForTime(Robot.tuning.getAutoLineDrive2Secs(), Robot.tuning.getAutoLineDrive2Set()));
    }
    
}
