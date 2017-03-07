package org.team1540.robot2017.subsystems;

import org.team1540.robot2017.Robot;
import org.team1540.robot2017.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.ctre.CANTalon.VelocityMeasurementPeriod;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {
    private final CANTalon shooterFlywheelTalon = new CANTalon(RobotMap.shooterLeftFlywheel);

    public Shooter() {
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
        shooterFlywheelTalon.SetVelocityMeasurementPeriod(VelocityMeasurementPeriod.Period_1Ms);
        shooterFlywheelTalon.SetVelocityMeasurementWindow(2);
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
        shooterFlywheelTalon.changeControlMode(TalonControlMode.PercentVbus);
        shooterFlywheelTalon.set(0);
    }

    public double getSpeed() {
        return shooterFlywheelTalon.getSpeed();
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

    public boolean upToSpeed(double targetSpeed) {
        return Math.abs(getSpeed() - targetSpeed) < Robot.tuning.getFlywheelSpeedMarginOfError();
    }

    public double getFlywheelCurrent() {
        return shooterFlywheelTalon.getOutputCurrent();
    }

    public double getFlywheelVoltage() {
        return shooterFlywheelTalon.getOutputVoltage();
    }

    public double getFlywheelEncoder() {
        return shooterFlywheelTalon.getEncPosition();
    }

    public void setLeft(double value) {
        shooterFlywheelTalon.changeControlMode(TalonControlMode.PercentVbus);
        shooterFlywheelTalon.set(value);
    }

    public double getLeftCurrent() {
        return shooterFlywheelTalon.getOutputCurrent();
    }

    @Override
    protected void initDefaultCommand() {

    }
}
