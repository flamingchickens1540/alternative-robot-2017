package org.team1540.robot2017.commands;

import org.team1540.robot2017.OI;
import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class FireShooter extends Command {
    
    private double beltSpeed;
    
    public FireShooter(double beltSpeed) {
        requires(Robot.feeder);
        requires(Robot.belt);
        requires(Robot.intake);
        this.beltSpeed = beltSpeed;
    }

    @Override
    protected void initialize() {
        Robot.feeder.setTop(Robot.tuning.getFeederTopOutput());
        Robot.belt.setSpeed(-beltSpeed);
        Robot.intake.set(Robot.tuning.getIntakeShootingOutput());
    }

    @Override
    protected void execute() {
        long time = System.currentTimeMillis();
        int feederSide = (int) ((time / Robot.tuning.getFeederSideSwitchPeriod()) % 3);
        Robot.feeder.setLeft(feederSide == 1 ? 0 : Robot.tuning.getFeederSideOutput());
        Robot.feeder.setRight(feederSide == 2 ? 0 : Robot.tuning.getFeederSideOutput());
    }
    
    @Override
    protected boolean isFinished() {
        return OI.buttonSpindown.get();
    }

}
