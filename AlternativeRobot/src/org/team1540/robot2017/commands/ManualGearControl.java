package org.team1540.robot2017.commands;

import org.team1540.robot2017.OI;
import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ManualGearControl extends Command {
    
    public ManualGearControl() {
        requires(Robot.gearMechanism);
    }
    
    @Override
    protected void execute() {
        int g = OI.getGearPOV();
        // rollers
        if (g >= 45 && g <= 135) { // right = rollers out
            Robot.gearMechanism.setRollers(Robot.tuning.getGearRollerOutput());
        } else if (g >= 225 && g <= 315) { // left = rollers in
            Robot.gearMechanism.setRollers(-Robot.tuning.getGearRollerOutput());
        } else {
            Robot.gearMechanism.setRollers(0);
        }
        // wrist
        if (g >= 135 && g <= 225) { // down = wrist down
            Robot.gearMechanism.setWrist(Robot.tuning.getGearWristOutput());
        } else if ((g >= 315 && g <= 360) || (g >= 0 && g <= 45)) { // up = wrist up
            Robot.gearMechanism.setWrist(-Robot.tuning.getGearWristOutput());
        } else {
            Robot.gearMechanism.setWrist(0);
        }
    }
    
    @Override
    protected boolean isFinished() {
        return false;
    }
    
}
