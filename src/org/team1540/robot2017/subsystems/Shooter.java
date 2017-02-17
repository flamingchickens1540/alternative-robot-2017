package org.team1540.robot2017.subsystems;

import org.team1540.robot2017.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {

    private final CANTalon shooterFlywheelTalon = new CANTalon(RobotMap.shooterTalonLeftFlywheel);
    private final CANTalon shooterRightFlywheelTalon = new CANTalon(RobotMap.shooterTalonRightFlywheel);

    public Shooter() {
        shooterRightFlywheelTalon.reset();
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
    }

    public double getSpeed() {
        return shooterFlywheelTalon.getSpeed();
    }

    public void setSpeed(double speed) {
        shooterFlywheelTalon.changeControlMode(TalonControlMode.PercentVbus);
        shooterFlywheelTalon.set(speed);
    }

    public double getClosedLoopError() {
        return shooterFlywheelTalon.getClosedLoopError();
    }

    public double getMotorOutput() {
        return shooterFlywheelTalon.getOutputVoltage() / shooterFlywheelTalon.getBusVoltage();
    }

    public double getFlywheelCurrentL() {
        return shooterFlywheelTalon.getOutputCurrent();
    }

    public double getFlywheelCurrentR() {
        return shooterRightFlywheelTalon.getOutputCurrent();
    }

    @Override
    protected void initDefaultCommand() {

    }
}
