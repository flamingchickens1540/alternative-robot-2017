//package org.team1540.robot2017.commands;
//
//import org.team1540.robot2017.Robot;
//
//import edu.wpi.first.wpilibj.command.CommandGroup;
//
//public class SpinupFire extends CommandGroup {
//    
//    public SpinupFire() {
//        if (Robot.shooter.upToSpeed()) {
//            addSequential(new FireShooter(Robot.tuning.getBeltSpeed()));
//        }
//        else {
//            addSequential(new SpinupFlywheel(Robot.tuning.getShooterFlywheelSpeed()));
//        }
//    }
//    
//}
// jake said if you use this he will fucking kill you