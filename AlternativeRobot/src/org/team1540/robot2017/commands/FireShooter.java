package org.team1540.robot2017.commands;

import org.team1540.robot2017.OI;
import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class FireShooter extends Command {
    
    private String networkTableName;
    private double defaultValue;
    
    public FireShooter(String networkTableName, double defaultValue) {
        requires(Robot.feeder);
        requires(Robot.belt);
        requires(Robot.intake);
        this.networkTableName = networkTableName;
        this.defaultValue = defaultValue;
    }

    @Override
    protected void initialize() {
        Robot.belt.setPID(Robot.tuning.getBeltP(), Robot.tuning.getBeltI(), Robot.tuning.getBeltD(), Robot.tuning.getBeltF());
        Robot.feeder.setTop(Robot.tuning.getFeederTopOutput());
        Robot.belt.setSpeed(Robot.tuning.getDouble(networkTableName, defaultValue));
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
