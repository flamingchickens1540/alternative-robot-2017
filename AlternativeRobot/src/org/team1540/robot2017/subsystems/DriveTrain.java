package org.team1540.robot2017.subsystems;

import org.team1540.robot2017.Robot;
import org.team1540.robot2017.RobotMap;
import org.team1540.robot2017.RobotUtil;
import org.team1540.robot2017.commands.JoystickDrive;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain extends Subsystem {
    private final CANTalon driveRightTalon = new CANTalon(RobotMap.driveTalonRightA);
    private final CANTalon driveRightBTalon = new CANTalon(RobotMap.driveTalonRightB);
    private final CANTalon driveRightCTalon = new CANTalon(RobotMap.driveTalonRightC);
    private final CANTalon driveLeftTalon = new CANTalon(RobotMap.driveTalonLeftA);
    private final CANTalon driveLeftBTalon = new CANTalon(RobotMap.driveTalonLeftB);
    private final CANTalon driveLeftCTalon = new CANTalon(RobotMap.driveTalonLeftC);
    private final CANTalon[] talons = { driveRightTalon, driveRightBTalon, driveRightCTalon, driveLeftTalon,
            driveLeftBTalon, driveLeftCTalon };

    public DriveTrain() {
        for (CANTalon talon : talons) {
            talon.setVoltageRampRate(Robot.tuning.getDriveRampRate());
            talon.enableBrakeMode(true);
        }
        driveRightTalon.setProfile(0);
        driveLeftTalon.setProfile(0);
        driveRightTalon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
        driveLeftTalon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
        driveRightTalon.configEncoderCodesPerRev(1024);
        driveLeftTalon.configEncoderCodesPerRev(1024);
        driveRightTalon.changeControlMode(TalonControlMode.PercentVbus);
        driveRightBTalon.changeControlMode(TalonControlMode.Follower);
        driveRightCTalon.changeControlMode(TalonControlMode.Follower);
        driveLeftTalon.changeControlMode(TalonControlMode.PercentVbus);
        driveLeftBTalon.changeControlMode(TalonControlMode.Follower);
        driveLeftCTalon.changeControlMode(TalonControlMode.Follower);
        driveRightTalon.reverseOutput(false);
        driveLeftTalon.reverseOutput(false);
        driveRightTalon.reverseSensor(true);
        driveLeftTalon.reverseSensor(true);
        driveRightBTalon.set(driveRightTalon.getDeviceID());
        driveRightCTalon.set(driveRightTalon.getDeviceID());
        driveLeftBTalon.set(driveLeftTalon.getDeviceID());
        driveLeftCTalon.set(driveLeftTalon.getDeviceID());
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new JoystickDrive());
    }

    public void tankDrive(double left, double right, double triggerL, double triggerR) {
        driveRightTalon.changeControlMode(TalonControlMode.PercentVbus);
        driveRightBTalon.changeControlMode(TalonControlMode.Follower);
        driveRightCTalon.changeControlMode(TalonControlMode.Follower);
        driveLeftTalon.changeControlMode(TalonControlMode.PercentVbus);
        driveLeftBTalon.changeControlMode(TalonControlMode.Follower);
        driveLeftCTalon.changeControlMode(TalonControlMode.Follower);
        driveRightBTalon.set(driveRightTalon.getDeviceID());
        driveRightCTalon.set(driveRightTalon.getDeviceID());
        driveLeftBTalon.set(driveLeftTalon.getDeviceID());
        driveLeftCTalon.set(driveLeftTalon.getDeviceID());
        double deadzone = 0.15;
        double exponent = 2.0;
        driveRightTalon.set(RobotUtil.betterDeadzone(right + triggerR - triggerL, deadzone, exponent)*Robot.tuning.getRightDriveMultiplier());
        driveLeftTalon.set(RobotUtil.betterDeadzone(left - triggerR + triggerL, deadzone, exponent)*Robot.tuning.getLeftDriveMultiplier());
    }
    
    public void set(double value) {
        driveRightTalon.changeControlMode(TalonControlMode.PercentVbus);
        driveRightBTalon.changeControlMode(TalonControlMode.Follower);
        driveRightCTalon.changeControlMode(TalonControlMode.Follower);
        driveLeftTalon.changeControlMode(TalonControlMode.PercentVbus);
        driveLeftBTalon.changeControlMode(TalonControlMode.Follower);
        driveLeftCTalon.changeControlMode(TalonControlMode.Follower);
        driveRightBTalon.set(driveRightTalon.getDeviceID());
        driveRightCTalon.set(driveRightTalon.getDeviceID());
        driveLeftBTalon.set(driveLeftTalon.getDeviceID());
        driveLeftCTalon.set(driveLeftTalon.getDeviceID());
        driveRightTalon.set(value);
        driveLeftTalon.set(-value);
    }
    
    public void set(double left, double right) {
        driveRightTalon.changeControlMode(TalonControlMode.PercentVbus);
        driveRightBTalon.changeControlMode(TalonControlMode.Follower);
        driveRightCTalon.changeControlMode(TalonControlMode.Follower);
        driveLeftTalon.changeControlMode(TalonControlMode.PercentVbus);
        driveLeftBTalon.changeControlMode(TalonControlMode.Follower);
        driveLeftCTalon.changeControlMode(TalonControlMode.Follower);
        driveRightBTalon.set(driveRightTalon.getDeviceID());
        driveRightCTalon.set(driveRightTalon.getDeviceID());
        driveLeftBTalon.set(driveLeftTalon.getDeviceID());
        driveLeftCTalon.set(driveLeftTalon.getDeviceID());
        driveRightTalon.set(right);
        driveLeftTalon.set(-left);
    }
    
    public void stop() {
        driveRightTalon.changeControlMode(TalonControlMode.PercentVbus);
        driveRightBTalon.changeControlMode(TalonControlMode.Follower);
        driveRightCTalon.changeControlMode(TalonControlMode.Follower);
        driveLeftTalon.changeControlMode(TalonControlMode.PercentVbus);
        driveLeftBTalon.changeControlMode(TalonControlMode.Follower);
        driveLeftCTalon.changeControlMode(TalonControlMode.Follower);
        driveRightBTalon.set(driveRightTalon.getDeviceID());
        driveRightCTalon.set(driveRightTalon.getDeviceID());
        driveLeftBTalon.set(driveLeftTalon.getDeviceID());
        driveLeftCTalon.set(driveLeftTalon.getDeviceID());
        driveRightTalon.set(0);
        driveLeftTalon.set(0);
    }

    public void setRightFront(double value) {
        driveRightTalon.changeControlMode(TalonControlMode.PercentVbus);
        driveRightBTalon.changeControlMode(TalonControlMode.PercentVbus);
        driveRightCTalon.changeControlMode(TalonControlMode.PercentVbus);
        driveLeftTalon.changeControlMode(TalonControlMode.PercentVbus);
        driveLeftBTalon.changeControlMode(TalonControlMode.PercentVbus);
        driveLeftCTalon.changeControlMode(TalonControlMode.PercentVbus);
        driveRightTalon.set(value);
    }

    public void setRightMiddle(double value) {
        driveRightTalon.changeControlMode(TalonControlMode.PercentVbus);
        driveRightBTalon.changeControlMode(TalonControlMode.PercentVbus);
        driveRightCTalon.changeControlMode(TalonControlMode.PercentVbus);
        driveLeftTalon.changeControlMode(TalonControlMode.PercentVbus);
        driveLeftBTalon.changeControlMode(TalonControlMode.PercentVbus);
        driveLeftCTalon.changeControlMode(TalonControlMode.PercentVbus);
        driveRightBTalon.set(value);
    }

    public void setRightBack(double value) {
        driveRightTalon.changeControlMode(TalonControlMode.PercentVbus);
        driveRightBTalon.changeControlMode(TalonControlMode.PercentVbus);
        driveRightCTalon.changeControlMode(TalonControlMode.PercentVbus);
        driveLeftTalon.changeControlMode(TalonControlMode.PercentVbus);
        driveLeftBTalon.changeControlMode(TalonControlMode.PercentVbus);
        driveLeftCTalon.changeControlMode(TalonControlMode.PercentVbus);
        driveRightCTalon.set(value);
    }

    public void setLeftFront(double value) {
        driveRightTalon.changeControlMode(TalonControlMode.PercentVbus);
        driveRightBTalon.changeControlMode(TalonControlMode.PercentVbus);
        driveRightCTalon.changeControlMode(TalonControlMode.PercentVbus);
        driveLeftTalon.changeControlMode(TalonControlMode.PercentVbus);
        driveLeftBTalon.changeControlMode(TalonControlMode.PercentVbus);
        driveLeftCTalon.changeControlMode(TalonControlMode.PercentVbus);
        driveLeftTalon.set(value);
    }

    public void setLeftMiddle(double value) {
        driveRightTalon.changeControlMode(TalonControlMode.PercentVbus);
        driveRightBTalon.changeControlMode(TalonControlMode.PercentVbus);
        driveRightCTalon.changeControlMode(TalonControlMode.PercentVbus);
        driveLeftTalon.changeControlMode(TalonControlMode.PercentVbus);
        driveLeftBTalon.changeControlMode(TalonControlMode.PercentVbus);
        driveLeftCTalon.changeControlMode(TalonControlMode.PercentVbus);
        driveLeftBTalon.set(value);
    }

    public void setLeftBack(double value) {
        driveRightTalon.changeControlMode(TalonControlMode.PercentVbus);
        driveRightBTalon.changeControlMode(TalonControlMode.PercentVbus);
        driveRightCTalon.changeControlMode(TalonControlMode.PercentVbus);
        driveLeftTalon.changeControlMode(TalonControlMode.PercentVbus);
        driveLeftBTalon.changeControlMode(TalonControlMode.PercentVbus);
        driveLeftCTalon.changeControlMode(TalonControlMode.PercentVbus);
        driveLeftCTalon.set(value);
    }
    
    public void setRelativePositionRight(double position) {
        driveRightTalon.changeControlMode(TalonControlMode.Position);
        driveRightBTalon.changeControlMode(TalonControlMode.Follower);
        driveRightCTalon.changeControlMode(TalonControlMode.Follower);
        driveRightBTalon.set(driveRightTalon.getDeviceID());
        driveRightCTalon.set(driveRightTalon.getDeviceID());
        SmartDashboard.putNumber("Drive Right Relative Position", 
                driveRightTalon.getEncPosition());
        driveRightTalon.setSetpoint((driveRightTalon.getEncPosition()));
//        driveRightTalon.setSetpoint(100);
    }
    
    public void setRelativePositionLeft(double position) {
        driveLeftTalon.changeControlMode(TalonControlMode.Position);
        driveLeftBTalon.changeControlMode(TalonControlMode.Follower);
        driveLeftCTalon.changeControlMode(TalonControlMode.Follower);
        driveLeftBTalon.set(driveLeftTalon.getDeviceID());
        driveLeftCTalon.set(driveLeftTalon.getDeviceID());
        SmartDashboard.putNumber("Drive Left Relative Position",
                driveLeftTalon.getEncPosition());
        driveLeftTalon.setSetpoint((driveLeftTalon.getEncPosition()));
//        driveLeftTalon.setSetpoint(100);
    }
    
    public void setPIDLeft(double p, double i, double d, double f) {
        driveLeftTalon.setPID(p, i, d);
        driveLeftTalon.setF(f);
    }
    
    public void setPIDRight(double p, double i, double d, double f) {
        driveRightTalon.setPID(p, i, d);
        driveRightTalon.setF(f);
    }

    public double getRightEncoderPosition() {
        return driveRightTalon.getEncPosition();
    }

    public double getLeftEncoderPosition() {
        return driveLeftTalon.getEncPosition();
    }
    
    public double getRightError() {
        return driveRightTalon.getError();
    }
    
    public double getLeftError() {
        return driveLeftTalon.getError();
    }
    
    public void reverseOutputLeft() {
        driveLeftTalon.reverseOutput(true);
    }
    
    public void reverseOutputRight() {
        driveRightTalon.reverseOutput(true);
    }
    
    public void unreverseOutput() {
        driveLeftTalon.reverseOutput(false);
        driveLeftBTalon.reverseOutput(false);
        driveLeftCTalon.reverseOutput(false);
        driveRightTalon.reverseOutput(false);
        driveRightBTalon.reverseOutput(false);
        driveRightCTalon.reverseOutput(false);
    }
    
    public void configurePeakOutputVoltage(double value) {
        driveLeftTalon.configPeakOutputVoltage(value, -value);
        driveLeftBTalon.configPeakOutputVoltage(value, -value);
        driveLeftCTalon.configPeakOutputVoltage(value, -value);
        driveRightTalon.configPeakOutputVoltage(value, -value);
        driveRightBTalon.configPeakOutputVoltage(value, -value);
        driveRightCTalon.configPeakOutputVoltage(value, -value);
    }
    
    public double getRightMotorOutput() {
        return driveRightTalon.getOutputVoltage() / driveRightTalon.getBusVoltage();
    }
    
    public double getLeftMotorOutput() {
        return driveLeftTalon.getOutputVoltage() / driveRightTalon.getBusVoltage();
    }
    
    public double getRightSetpoint() {
        return driveRightTalon.getSetpoint();
    }
    
    public double getLeftSetpoint() {
        return driveLeftTalon.getSetpoint();
    }

    public double getRightFrontCurrent() {
        return driveRightTalon.getOutputCurrent();
    }

    public double getRightMiddleCurrent() {
        return driveRightBTalon.getOutputCurrent();
    }

    public double getRightBackCurrent() {
        return driveRightCTalon.getOutputCurrent();
    }

    public double getLeftFrontCurrent() {
        return driveLeftTalon.getOutputCurrent();
    }

    public double getLeftMiddleCurrent() {
        return driveLeftBTalon.getOutputCurrent();
    }

    public double getLeftBackCurrent() {
        return driveLeftCTalon.getOutputCurrent();
    }
}
