package org.team1540.robot2017.commands.auto;

import java.io.IOException;

import org.team1540.robot2017.Robot;
import org.team1540.robot2017.motion.CSVMotionProfile;

import edu.wpi.first.wpilibj.command.Command;

public class RunMotionProfile extends Command {
    private String profile;
    private boolean isEnd = false;
    
    public RunMotionProfile(String profile) {
        requires(Robot.driveTrain);
        this.profile = profile;
    }
    
    @Override
    protected void initialize() {
        Robot.driveTrain.stopMotionProfile();
        
        try {
            Robot.driveTrain.setLeftProfile(new CSVMotionProfile("/home/lvuser/profiles/"+profile+"_left.csv"));
            Robot.driveTrain.setRightProfile(new CSVMotionProfile("/home/lvuser/profiles/"+profile+"_right.csv"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        Robot.driveTrain.setPIDLeft(Robot.tuning.getMotionProfilingP(), 
                Robot.tuning.getMotionProfilingI(), 
                Robot.tuning.getMotionProfilingD(), 
                Robot.tuning.getMotionProfilingF());
        Robot.driveTrain.setPIDRight(Robot.tuning.getMotionProfilingP(), 
                Robot.tuning.getMotionProfilingI(), 
                Robot.tuning.getMotionProfilingD(), 
                Robot.tuning.getMotionProfilingF());
        Robot.driveTrain.startMotionProfile();
    }
    
    @Override
    protected void execute() {
        isEnd = Robot.driveTrain.controlMotionProfile();
    }

    @Override
    protected void end() {
        Robot.driveTrain.stopMotionProfile();
    }
    
    @Override
    public boolean isFinished() {
        return isEnd;
    }
}
