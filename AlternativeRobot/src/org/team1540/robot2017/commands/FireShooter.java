package org.team1540.robot2017.commands;

import org.team1540.robot2017.OI;
import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class FireShooter extends Command {

    public FireShooter() {
        requires(Robot.feeder);
        requires(Robot.belt);
        requires(Robot.intake);
    }

    @Override
    protected void initialize() {
        Robot.feeder.setTop(Robot.tuning.getFeederTopOutput());
        Robot.belt.setSpeed(Robot.tuning.getBeltSpeed());
        Robot.intake.set(Robot.tuning.getIntakeShootingOutput());
        System.out.println("firing shooter");
    }

    @Override
    protected void execute() {
        long time = System.currentTimeMillis();
        int feederSide = (int) ((time / Robot.tuning.getFeederSideSwitchPeriod()) % 3);
        Robot.feeder.setLeft(feederSide == 1 ? 0 : Robot.tuning.getFeederSideOutput());
        Robot.feeder.setRight(feederSide == 2 ? 0 : Robot.tuning.getFeederSideOutput());
        System.out.println("fire shooter is executing");
    }
    
    protected void end() {
        System.out.println("fire shooter is ending");
    }

    @Override
    protected boolean isFinished() {
        return OI.buttonSpindown.get();
    }

}
