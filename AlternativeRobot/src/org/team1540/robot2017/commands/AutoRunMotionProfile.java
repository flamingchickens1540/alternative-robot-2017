package org.team1540.robot2017.commands;

import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoRunMotionProfile extends CommandGroup {

    public AutoRunMotionProfile() {
        addSequential(new RunMotionProfile("gen"));
    }
    
}
