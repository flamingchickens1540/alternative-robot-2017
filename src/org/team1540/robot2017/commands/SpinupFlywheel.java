package org.team1540.robot2017.commands;

import org.team1540.robot2017.OI;
import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SpinupFlywheel extends Command {
    private double setpoint = 0;
    private double currentMotorOutput = 0;
    private double previousError = 0;
    private double takeBackHalfVariable = 0;

    public SpinupFlywheel() {
        requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
//    	System.out.println("initializing");
        previousError = 0;
        setpoint = Robot.tuning.getShooterFlywheelSpeed();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
//    	System.out.println("executing");
    	double error = setpoint - Robot.shooter.getSpeed();
        currentMotorOutput += Robot.tuning.getTBHParameter() * error;
        if (currentMotorOutput > 1) {
            currentMotorOutput = 1;
        } else if (currentMotorOutput < 0) {
            currentMotorOutput = 0;
        }

        if (Math.signum(error) != Math.signum(previousError)) {
            currentMotorOutput = takeBackHalfVariable = 0.5 * (currentMotorOutput + takeBackHalfVariable);
        }

        previousError = error;

        Robot.shooter.setSpeed(currentMotorOutput);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return Robot.oi.buttonSpindown.get();
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    	Robot.shooter.setSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
