package org.team1540.robot2017.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SpinupFlywheelTeleop extends CommandGroup {
    
    public SpinupFlywheelTeleop() {
        addSequential(new SpinupFlywheel("Shooter Flywheel Target Speed", 9000));
        addSequential(new KeepFlywheelSpunUp());
    }
    
}
