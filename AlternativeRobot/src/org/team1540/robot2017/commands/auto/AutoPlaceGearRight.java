package org.team1540.robot2017.commands.auto;

import org.team1540.robot2017.Robot;
import org.team1540.robot2017.commands.ResetGearMechanism;
import org.team1540.robot2017.commands.motion.RunMotionProfile;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoPlaceGearRight extends CommandGroup {
    public AutoPlaceGearRight() {
        addSequential(new ResetGearMechanism());
        addSequential(new RunMotionProfile("gear_right"));
        addSequential(new PlaceGear2(), Robot.tuning.getAutoGearPlacementSecs());
        addSequential(new DriveForTime(Robot.tuning.getAutoDrivingGearBackoffTime(), 
                Robot.tuning.getAutoDrivingGearBackoffSet()));
    }
}
