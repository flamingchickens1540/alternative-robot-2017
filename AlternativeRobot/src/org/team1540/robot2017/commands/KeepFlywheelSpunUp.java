package org.team1540.robot2017.commands;

import org.team1540.robot2017.OI;
import org.team1540.robot2017.Robot;
import org.team1540.robot2017.RobotUtil;

import edu.wpi.first.wpilibj.command.Command;

public class KeepFlywheelSpunUp extends Command {
    
    public KeepFlywheelSpunUp() {
        requires(Robot.shooter);
    }
    
    protected void execute() {
        Robot.shooter.setSpeed(Robot.tuning.getShooterFlywheelSpeed() 
                + RobotUtil.betterDeadzone(OI.getFlywheelSpeedJoystick(), 0.15, 2.0) 
                * Robot.tuning.getFlywheelSpeedChangeCoefficient());
    }
    
    @Override
    protected boolean isFinished() {
        return OI.buttonSpindown.get(); // ok because this command is only run in teleop
    }

}
