package org.team1540.robot2017.commands.auto;

import org.team1540.robot2017.commands.FireShooter;
import org.team1540.robot2017.commands.ResetGearMechanism;
import org.team1540.robot2017.commands.motion.RunMotionProfile;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoPlaceGearShootCenterRed extends CommandGroup {
    public AutoPlaceGearShootCenterRed() {
        addParallel(new ResetGearMechanism());
        addParallel(new GearAndShootDuringMotionProfile(2.2, 6));
        addSequential(new RunMotionProfile("gear_red_center_shoot"));
        addSequential(new FireShooter("Auto Belt Target Speed", 2400));
    }
}
