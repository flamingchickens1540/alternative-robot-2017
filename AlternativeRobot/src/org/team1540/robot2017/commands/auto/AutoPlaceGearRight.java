package org.team1540.robot2017.commands.auto;

import org.team1540.robot2017.Robot;
import org.team1540.robot2017.commands.DriveForTime;
import org.team1540.robot2017.commands.RunMotionProfile;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoPlaceGearRight extends CommandGroup {
    public AutoPlaceGearRight() {
        addSequential(new RunMotionProfile("gear_right"));
//        addSequential(new PlaceGear());
        addSequential(new DriveForTime(Robot.tuning.getAutoDrivingGearBackoffTime(), 
                Robot.tuning.getAutoDrivingGearBackoffSet()));
    }
}
