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
    
    private double bangBangTarget = 0.0;
    private final Notifier bangBangNotifier = new Notifier(() -> {
        setBangBang(bangBangTarget);
    });

    public Shooter() {
//        shooterRightFlywheelTalon.changeControlMode(TalonControlMode.Follower);
//        shooterRightFlywheelTalon.set(shooterFlywheelTalon.getDeviceID());
//        shooterRightFlywheelTalon.reverseOutput(true);

        shooterFlywheelTalon.setFeedbackDevice(FeedbackDevice.EncRising);
        shooterFlywheelTalon.reverseSensor(false);
        shooterFlywheelTalon.reverseOutput(false);
        shooterFlywheelTalon.configNominalOutputVoltage(+0f, -0f);
        shooterFlywheelTalon.configPeakOutputVoltage(+12f, -12f);
        shooterFlywheelTalon.configEncoderCodesPerRev(1024);
        shooterFlywheelTalon.setAllowableClosedLoopErr(0);
        shooterFlywheelTalon.setProfile(0);
        shooterFlywheelTalon.ClearIaccum();
        shooterFlywheelTalon.enable();
        shooterFlywheelTalon.setF(Robot.tuning.getFlywheelF());
        shooterFlywheelTalon.setP(Robot.tuning.getFlywheelP());
        shooterFlywheelTalon.setI(Robot.tuning.getFlywheelI());
        shooterFlywheelTalon.setD(Robot.tuning.getFlywheelD());
//        shooterFlywheelTalon.setF(0);
//        shooterFlywheelTalon.setP(0);
//        shooterFlywheelTalon.setI(0);
//        shooterFlywheelTalon.setD(0);
//        /* set acceleration and vcruise velocity - see documentation */
//        shooterFlywheelTalon.setMotionMagicCruiseVelocity(0);
//        shooterFlywheelTalon.setMotionMagicAcceleration(0);
        
//        shooterFlywheelTalon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
//        shooterFlywheelTalon.reverseSensor(true);
//        shooterFlywheelTalon.configNominalOutputVoltage(+0.0f, -0.0f);
//        shooterFlywheelTalon.configPeakOutputVoltage(+12.0f, -12.0f);
//        shooterFlywheelTalon.setProfile(0);
//        shooterFlywheelTalon.setF(1);
//        shooterFlywheelTalon.setP(0);
//        shooterFlywheelTalon.setI(0);
//        shooterFlywheelTalon.setD(0);
//        shooterFlywheelTalon.setMotionMagicCruiseVelocity(0);
//        shooterFlywheelTalon.setMotionMagicAcceleration(0);
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
        shooterFlywheelTalon.changeControlMode(TalonControlMode.Speed);
        shooterFlywheelTalon.setSetpoint(rpm);
    }
    
    public void setSpeedCruise(double rpm) {
        shooterFlywheelTalon.changeControlMode(TalonControlMode.MotionMagic);
        shooterFlywheelTalon.set(rpm);
    }

    public void stop() {
        disableBangBang();
        shooterFlywheelTalon.changeControlMode(TalonControlMode.PercentVbus);
        shooterFlywheelTalon.set(0);
    }

    public double getSpeed() {
        return shooterFlywheelTalon.getSpeed();
//        return shooterFlywheelTalon.getPosition();
//        return shooterFlywheelTalon.getPulseWidthVelocity();
    }

    public double getSetpoint() {
        return shooterFlywheelTalon.getSetpoint();
    }
    
    public double getError() {
        return shooterFlywheelTalon.getError();
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
    
    public void setBangBang(double target) {
        shooterRightFlywheelTalon.changeControlMode(TalonControlMode.Follower);
        shooterRightFlywheelTalon.set(shooterFlywheelTalon.getDeviceID());
        shooterFlywheelTalon.changeControlMode(TalonControlMode.PercentVbus);
        
        double output;
        if (target > 0) {
            output = shooterFlywheelTalon.getEncVelocity() < target ? -1.0 : 0.0;
        } else if (target < 0) {
            output = shooterFlywheelTalon.getEncVelocity() > target ? 1.0 : 0.0;
        } else {
            output = 0;
        }
        
        shooterFlywheelTalon.set(output);
    }
    
    public void enableBangBang(double target) {
        bangBangTarget = -target;
        bangBangNotifier.startPeriodic(0.001);
    }
    
    public void disableBangBang() {
        bangBangTarget = 0.0;
        bangBangNotifier.stop();
        shooterFlywheelTalon.changeControlMode(TalonControlMode.PercentVbus);
        shooterFlywheelTalon.set(0);
    }

    @Override
    protected void initDefaultCommand() {

    }
}
