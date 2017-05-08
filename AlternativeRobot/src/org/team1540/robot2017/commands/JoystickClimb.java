package org.team1540.robot2017.commands;

import org.team1540.robot2017.OI;
import org.team1540.robot2017.Robot;
import org.team1540.robot2017.RobotUtil;

import edu.wpi.first.wpilibj.command.Command;

public class JoystickClimb extends Command {
    
    public JoystickClimb() {
        requires(Robot.climber);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        Robot.climber.set(RobotUtil.deadzone(Math.abs(OI.getClimberJoystick()), 0.2));
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }
    
}
