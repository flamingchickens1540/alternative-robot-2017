package org.team1540.robot2017.subsystems;

import org.team1540.robot2017.Robot;
import org.team1540.robot2017.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {

    
    private final CANTalon shooterFlywheelTalon = new CANTalon(RobotMap.shooterLeftFlywheel);
    private final CANTalon shooterRightFlywheelTalon = new CANTalon(RobotMap.shooterRightFlywheel);
    
    public Shooter() {
        shooterRightFlywheelTalon.changeControlMode(TalonControlMode.Follower);
        shooterRightFlywheelTalon.set(shooterFlywheelTalon.getDeviceID());
        shooterRightFlywheelTalon.reverseOutput(true);

        shooterFlywheelTalon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
        shooterFlywheelTalon.reverseSensor(true);
        shooterFlywheelTalon.configNominalOutputVoltage(+0f, -0f);
        shooterFlywheelTalon.configPeakOutputVoltage(+12f, -12f);
        shooterFlywheelTalon.setAllowableClosedLoopErr(0);
        shooterFlywheelTalon.setProfile(0);
        shooterFlywheelTalon.ClearIaccum();
        shooterFlywheelTalon.enable();
        shooterFlywheelTalon.setF(Robot.tuning.getFlywheelF());
        shooterFlywheelTalon.setP(Robot.tuning.getFlywheelP());
        shooterFlywheelTalon.setI(Robot.tuning.getFlywheelI());
        shooterFlywheelTalon.setD(Robot.tuning.getFlywheelD());
    }

    public void setPID(double p, double i, double d, double f) {
        shooterFlywheelTalon.setPID(p, i, d);
        shooterFlywheelTalon.setF(f);
    }

    public double getP() {
        return shooterFlywheelTalon.getP();
    }

    public double getI() {
        return shooterFlywheelTalon.getI();
    }

    public double getD() {
        return shooterFlywheelTalon.getD();
    }

    public double getF() {
        return shooterFlywheelTalon.getF();
    }

    public double getPIDOutput() {
        return shooterFlywheelTalon.pidGet();
    }

    public void setSpeed(double rpm) {
        shooterRightFlywheelTalon.changeControlMode(TalonControlMode.Follower);
        shooterRightFlywheelTalon.set(shooterFlywheelTalon.getDeviceID());
        shooterFlywheelTalon.changeControlMode(TalonControlMode.Speed);
        shooterFlywheelTalon.setSetpoint(rpm);
    }

    public void stop() {
        shooterFlywheelTalon.changeControlMode(TalonControlMode.PercentVbus);
        shooterFlywheelTalon.set(0);
    }

    public double getSpeed() {
        return shooterFlywheelTalon.getSpeed();
    }

    public double getSetpoint() {
        return shooterFlywheelTalon.getSetpoint();
    }

    public double getClosedLoopError() {
        return shooterFlywheelTalon.getClosedLoopError();
    }

    public double getMotorOutput() {
        return shooterFlywheelTalon.getOutputVoltage() / shooterFlywheelTalon.getBusVoltage();
    }

    public boolean upToSpeed() {
        return Math.abs(getSpeed() - Robot.tuning.getShooterFlywheelSpeed()) < Robot.tuning.getFlywheelSpeedMarginOfError();
    }

    public double getFlywheelCurrentL() {
        return shooterFlywheelTalon.getOutputCurrent();
    }

    public double getFlywheelCurrentR() {
        return shooterRightFlywheelTalon.getOutputCurrent();
    }

    public double getFlywheelEncoder() {
        return shooterFlywheelTalon.getEncPosition();
    }

    public void setRight(double value) {
        shooterRightFlywheelTalon.changeControlMode(TalonControlMode.PercentVbus);
        shooterFlywheelTalon.changeControlMode(TalonControlMode.PercentVbus);
        shooterRightFlywheelTalon.set(value);
    }

    public void setLeft(double value) {
        shooterRightFlywheelTalon.changeControlMode(TalonControlMode.PercentVbus);
        shooterFlywheelTalon.changeControlMode(TalonControlMode.PercentVbus);
        shooterFlywheelTalon.set(value);
    }

    public double getRightCurrent() {
        return shooterRightFlywheelTalon.getOutputCurrent();
    }

    public double getLeftCurrent() {
        return shooterFlywheelTalon.getOutputCurrent();
    }
    
    @Override
    protected void initDefaultCommand() {

    }
}
