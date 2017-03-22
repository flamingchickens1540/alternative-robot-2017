package org.team1540.robot2017.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class ShootForTime extends CommandGroup {
    
    public ShootForTime(double seconds) {
        addSequential(new SpinupFlywheel("Auto Flywheel Target Speed", 9000));
        addSequential(new WaitCommand(0.5));
        addSequential(new FireShooter("Auto Belt Target Speed", 2400), seconds);
        addSequential(new TurnEverythingOff());
    }
    
}
