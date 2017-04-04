package org.team1540.robot2017.commands.auto;

import org.team1540.robot2017.Robot;
import org.team1540.robot2017.commands.ResetGearMechanism;
import org.team1540.robot2017.commands.SpinupFlywheel;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class GearAndShootDuringMotionProfile extends CommandGroup {
    
    public GearAndShootDuringMotionProfile(double secsBeforeGear, double secsBeforeSpinup) {
        addParallel(new CommandGroup() {
            {
                addSequential(new WaitCommand(secsBeforeGear));
                addSequential(new PlaceGear2(), Robot.tuning.getAutoGearPlacementSecs());
                addSequential(new ResetGearMechanism());
            }
        });
        addParallel(new CommandGroup() {
            {
                addSequential(new WaitCommand(secsBeforeSpinup));
                addSequential(new SpinupFlywheel("Auto Flywheel Target Speed", 9000));
            }
        });
    }
    
}
