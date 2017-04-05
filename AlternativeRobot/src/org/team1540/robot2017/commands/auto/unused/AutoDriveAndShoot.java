package org.team1540.robot2017.commands.auto.unused;

import org.team1540.robot2017.commands.auto.DriveForTime;
import org.team1540.robot2017.commands.auto.ShootForTime;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutoDriveAndShoot extends CommandGroup {
    public AutoDriveAndShoot() {
        addSequential(new WaitCommand(4.0));
        addSequential(new DriveForTime(4.0, 0.67));
        addSequential(new ShootForTime(7.0));
    }
}
