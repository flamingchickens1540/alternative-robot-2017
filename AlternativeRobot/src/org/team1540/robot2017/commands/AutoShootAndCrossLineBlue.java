package org.team1540.robot2017.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoShootAndCrossLineBlue extends CommandGroup {
    
    public AutoShootAndCrossLineBlue() {
        addSequential(new AutoShoot());
        addSequential(new AutoCrossLineBlue());
    }
    
}
