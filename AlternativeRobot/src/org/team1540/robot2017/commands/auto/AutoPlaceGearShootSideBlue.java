package org.team1540.robot2017.commands.auto;

import org.team1540.robot2017.commands.FireShooter;
import org.team1540.robot2017.commands.ResetGearMechanism;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoPlaceGearShootSideBlue extends CommandGroup {
    public AutoPlaceGearShootSideBlue() {
        addSequential(new ResetGearMechanism());
        addParallel(new GearAndShootDuringMotionProfile(5.28, 6));
        addSequential(new RunMotionProfile("gear_blue_side_shoot"));
        addSequential(new FireShooter("Auto Belt Target Speed", 2400));
    }
}
