package org.team1540.robot2017.commands.auto;

import org.team1540.robot2017.commands.FireShooter;
import org.team1540.robot2017.commands.ResetGearMechanism;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoPlaceGearShootCenterBlue extends CommandGroup {
    public AutoPlaceGearShootCenterBlue() {
        addParallel(new ResetGearMechanism());
        addParallel(new GearAndShootDuringMotionProfile(2.2, 6));
        addSequential(new RunMotionProfile("gear_blue_center_shoot"));
        addSequential(new FireShooter("Auto Belt Target Speed", 2400));
    }
}
