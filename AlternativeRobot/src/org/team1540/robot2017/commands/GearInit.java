package org.team1540.robot2017.commands;

import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

public class GearInit extends TimedCommand {
    
    public GearInit() {
        super(Robot.tuning.getGearInitRollerSpinSecs());
        requires(Robot.gearRollers);
    }
    
    @Override
    protected void initialize() {
        Robot.gearRollers.setRollers(-Robot.tuning.getGearRollerOutput());
    }
    
    @Override
    protected void end() {
        Robot.gearRollers.stop();
    }
    
}
