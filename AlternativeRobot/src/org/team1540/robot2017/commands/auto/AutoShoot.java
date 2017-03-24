package org.team1540.robot2017.commands.auto;

import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoShoot extends CommandGroup {
    
    public AutoShoot() {
        addSequential(new ShootForTime(Robot.tuning.getAutoShootingSeconds()));
    }
    
}
