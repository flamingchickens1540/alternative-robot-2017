package org.team1540.robot2017.commands;

import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class ShootForTime extends CommandGroup {
    
    public ShootForTime() {
        addSequential(new SpinupFlywheel());
        addSequential(new FireShooter());
        addSequential(new WaitCommand(Robot.tuning.getAutoShootingSeconds()));
        addSequential(new TurnEverythingOff());
    }
    
}
