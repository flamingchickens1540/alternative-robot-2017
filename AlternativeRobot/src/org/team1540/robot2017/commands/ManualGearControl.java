package org.team1540.robot2017.commands;

import org.team1540.robot2017.OI;
import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ManualGearControl extends Command {
    
    public ManualGearControl() {
        requires(Robot.gearWrist);
        requires(Robot.gearRollers);
    }
    
    @Override
    protected void execute() {
//        int g = OI.getGearPOV();
//        // rollers
//        if (g >= 45 && g <= 135) { // right = rollers out
//            Robot.gearRollers.setRollers(Robot.tuning.getGearRollerOutput());
//        } else if (g >= 225 && g <= 315) { // left = rollers in
//            Robot.gearRollers.setRollers(-Robot.tuning.getGearRollerOutput());
//        } else {
            Robot.gearRollers.setRollers((OI.getCopilotLeftTrigger() + OI.getCopilotRightTrigger()) / 2);
//        }
//        // wrist
//        if (g >= 135 && g <= 225) { // down = wrist down
//            Robot.gearWrist.setWrist(Robot.tuning.getGearWristOutput());
//        } else if ((g >= 315 && g <= 360) || (g >= 0 && g <= 45)) { // up = wrist up
//            Robot.gearWrist.setWrist(-Robot.tuning.getGearWristOutput());
//        } else {
//            Robot.gearWrist.setWrist(-Robot.tuning.getGearWristIdle());
//        }
    }
    
    @Override
    protected boolean isFinished() {
        return false;
    }
    
}
