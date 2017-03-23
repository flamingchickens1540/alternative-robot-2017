package org.team1540.robot2017.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoShootAndCrossLineRed extends CommandGroup {
    
    public AutoShootAndCrossLineRed() {
        addSequential(new AutoShoot());
        addSequential(new AutoCrossLineRed());
    }
    
}
