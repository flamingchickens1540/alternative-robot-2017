package org.team1540.robot2017.commands.auto.unused;

import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveDistance extends Command {
    
    private double distance;
    private double position;
    
    public DriveDistance(double distance) {
        requires(Robot.driveTrain);
        this.distance = distance;
    }
    
    protected void initialize() {
        Robot.driveTrain.setPIDLeft(Robot.tuning.getAutoDrivingLeftP(), Robot.tuning.getAutoDrivingLeftI(), 
                Robot.tuning.getAutoDrivingLeftD(), Robot.tuning.getAutoDrivingLeftF());
        Robot.driveTrain.setPIDRight(Robot.tuning.getAutoDrivingRightP(), Robot.tuning.getAutoDrivingRightI(), 
                Robot.tuning.getAutoDrivingRightD(), Robot.tuning.getAutoDrivingRightF());
        double cWheel = 4 * Math.PI;
        double ticksPerRev = 1024;
        position = distance * (1/cWheel) * ticksPerRev;
        Robot.driveTrain.zeroEncoders();
        Robot.driveTrain.setSpeed(Math.signum(position)*200, Math.signum(position)*200);
    }
    
    protected void execute() {
        boolean r = Math.abs(Robot.driveTrain.getRightEncoderPosition() - position) 
                < Robot.tuning.getAutoDrivingMarginOfError();
        boolean l = Math.abs(Robot.driveTrain.getLeftEncoderPosition() - position) 
                < Robot.tuning.getAutoDrivingMarginOfError();
        if (r) {
            Robot.driveTrain.setRightSpeed(0);
        }
        
        if (l) {
            Robot.driveTrain.setLeftSpeed(0);
        }
    }
    
    protected void end() {
        Robot.driveTrain.set(0);
    }
    
    @Override
    protected boolean isFinished() {
        boolean r = Math.abs(Robot.driveTrain.getRightEncoderPosition() - position) 
                < Robot.tuning.getAutoDrivingMarginOfError();
        boolean l = Math.abs(Robot.driveTrain.getLeftEncoderPosition() - position) 
                < Robot.tuning.getAutoDrivingMarginOfError();
        return r && l;
    }
    
}
