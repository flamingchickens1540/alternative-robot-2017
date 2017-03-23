package org.team1540.robot2017.commands.auto;

import org.team1540.robot2017.Robot;
import org.team1540.robot2017.commands.DriveForTime;
import org.team1540.robot2017.commands.PlaceGear2;
import org.team1540.robot2017.commands.ResetGearMechanism;
import org.team1540.robot2017.commands.RunMotionProfile;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoPlaceGearLeft extends CommandGroup {
    public AutoPlaceGearLeft() {
        addSequential(new ResetGearMechanism());
        addSequential(new RunMotionProfile("gear_left"));
        addSequential(new PlaceGear2(), Robot.tuning.getAutoGearPlacementSecs());
        addSequential(new DriveForTime(Robot.tuning.getAutoDrivingGearBackoffTime(), 
                Robot.tuning.getAutoDrivingGearBackoffSet()));
    }
}
