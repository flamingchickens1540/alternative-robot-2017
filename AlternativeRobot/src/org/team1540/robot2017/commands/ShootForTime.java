package org.team1540.robot2017.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class ShootForTime extends CommandGroup {
    
    public ShootForTime(double seconds) {
        addSequential(new SpinupFlywheel());
        addSequential(new FireShooter(), seconds);
        addSequential(new TurnEverythingOff());
    }
    
}
