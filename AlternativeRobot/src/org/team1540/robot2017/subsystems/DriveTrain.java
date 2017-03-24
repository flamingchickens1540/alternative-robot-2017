package org.team1540.robot2017.subsystems;

import java.io.IOException;

import org.team1540.robot2017.Robot;
import org.team1540.robot2017.RobotMap;
import org.team1540.robot2017.RobotUtil;
import org.team1540.robot2017.commands.JoystickDrive;
import org.team1540.robot2017.motion.CSVMotionProfile;
import org.team1540.robot2017.motion.MotionProfile;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {
    private final CANTalon driveRightTalon = new CANTalon(RobotMap.driveTalonRightA);
    private final CANTalon driveRightBTalon = new CANTalon(RobotMap.driveTalonRightB);
    private final CANTalon driveRightCTalon = new CANTalon(RobotMap.driveTalonRightC);
    private final CANTalon driveLeftTalon = new CANTalon(RobotMap.driveTalonLeftA);
    private final CANTalon driveLeftBTalon = new CANTalon(RobotMap.driveTalonLeftB);
    private final CANTalon driveLeftCTalon = new CANTalon(RobotMap.driveTalonLeftC);
    private final CANTalon[] talons = { driveRightTalon, driveRightBTalon, driveRightCTalon, driveLeftTalon,
            driveLeftBTalon, driveLeftCTalon };
    
    private MotionProfile leftProfile;
    private MotionProfile rightProfile;

    class PeriodicRunnable implements java.lang.Runnable {
        public void run() {  
            driveLeftTalon.processMotionProfileBuffer();
            driveRightTalon.processMotionProfileBuffer();
        }
    }
    Notifier _notifer = new Notifier(new PeriodicRunnable());
    
    public DriveTrain() {
        _notifer.startPeriodic(0.005);
        
        try {
            CSVMotionProfile leftProfilePoints = (new CSVMotionProfile("/home/lvuser/profiles/left.csv"));
            CSVMotionProfile rightProfilePoints = (new CSVMotionProfile("/home/lvuser/profiles/right.csv"));
            leftProfile = new MotionProfile(driveLeftTalon, leftProfilePoints.points, leftProfilePoints.kNumPoints);
            rightProfile = new MotionProfile(driveRightTalon, rightProfilePoints.points, rightProfilePoints.kNumPoints);
        } catch (IOException e) {
            System.err.println("Could not find defualt profile");
        }
        
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

    public void setRightProfile(CSVMotionProfile profile) {
        rightProfile = new MotionProfile(driveRightTalon, profile.points, profile.kNumPoints);
    }
    
    public void setLeftProfile(CSVMotionProfile profile) {
        leftProfile = new MotionProfile(driveLeftTalon, profile.points, profile.kNumPoints);
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
        driveRightTalon.setPosition(0);
        driveRightTalon.changeControlMode(TalonControlMode.Position);
        driveRightBTalon.changeControlMode(TalonControlMode.Follower);
        driveRightCTalon.changeControlMode(TalonControlMode.Follower);
        driveRightBTalon.set(driveRightTalon.getDeviceID());
        driveRightCTalon.set(driveRightTalon.getDeviceID());
        driveRightTalon.setSetpoint(position);
    }
    
    public void setRelativePositionLeft(double position) {
        driveLeftTalon.setPosition(0);
        driveLeftTalon.changeControlMode(TalonControlMode.Position);
        driveLeftBTalon.changeControlMode(TalonControlMode.Follower);
        driveLeftCTalon.changeControlMode(TalonControlMode.Follower);
        driveLeftBTalon.set(driveLeftTalon.getDeviceID());
        driveLeftCTalon.set(driveLeftTalon.getDeviceID());
        driveLeftTalon.setSetpoint(position);
    }
    
    public void setSpeed(double left, double right) {
        driveLeftTalon.changeControlMode(TalonControlMode.Speed);
        driveLeftBTalon.changeControlMode(TalonControlMode.Follower);
        driveLeftCTalon.changeControlMode(TalonControlMode.Follower);
        driveLeftBTalon.set(driveLeftTalon.getDeviceID());
        driveLeftCTalon.set(driveLeftTalon.getDeviceID());
        driveLeftTalon.setSetpoint(left);
        
        driveRightTalon.changeControlMode(TalonControlMode.Speed);
        driveRightBTalon.changeControlMode(TalonControlMode.Follower);
        driveRightCTalon.changeControlMode(TalonControlMode.Follower);
        driveRightBTalon.set(driveRightTalon.getDeviceID());
        driveRightCTalon.set(driveRightTalon.getDeviceID());
        driveRightTalon.setSetpoint(right);
    }
    
    public void setRightSpeed(double right) {
        driveRightTalon.changeControlMode(TalonControlMode.Speed);
        driveRightBTalon.changeControlMode(TalonControlMode.Follower);
        driveRightCTalon.changeControlMode(TalonControlMode.Follower);
        driveRightBTalon.set(driveRightTalon.getDeviceID());
        driveRightCTalon.set(driveRightTalon.getDeviceID());
        driveRightTalon.setSetpoint(right);
    }
    
    public void setLeftSpeed(double left) {
        driveLeftTalon.changeControlMode(TalonControlMode.Speed);
        driveLeftBTalon.changeControlMode(TalonControlMode.Follower);
        driveLeftCTalon.changeControlMode(TalonControlMode.Follower);
        driveLeftBTalon.set(driveLeftTalon.getDeviceID());
        driveLeftCTalon.set(driveLeftTalon.getDeviceID());
        driveLeftTalon.setSetpoint(left);
    }
    
    public void zeroEncoders() {
        driveLeftTalon.setPosition(0);
        driveRightTalon.setPosition(0);
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
        return driveRightTalon.getPosition();
    }

    public double getLeftEncoderPosition() {
        return driveLeftTalon.getPosition();
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
    
    public double getLeftSpeed() {
        return driveLeftTalon.getSpeed();
    }
    
    public double getRightSpeed() {
        return driveRightTalon.getSpeed();
    }
    
    public void controlMotionProfile() {
        leftProfile.control();
        driveLeftTalon.changeControlMode(TalonControlMode.MotionProfile);
        CANTalon.SetValueMotionProfile setOutputLeft = leftProfile.getSetValue();
        driveLeftTalon.set(setOutputLeft.value);
        
        rightProfile.control();
        driveRightTalon.changeControlMode(TalonControlMode.MotionProfile);
        CANTalon.SetValueMotionProfile setOutputRight = rightProfile.getSetValue();
        driveRightTalon.set(setOutputRight.value);
    }
    
    public void startMotionProfile() {
        leftProfile.startMotionProfile();
        rightProfile.startMotionProfile();
    }
    
    public void stopMotionProfile() {
        leftProfile.reset();
        driveLeftTalon.changeControlMode(TalonControlMode.PercentVbus);
        driveLeftTalon.set(0);
        rightProfile.reset();
        driveRightTalon.changeControlMode(TalonControlMode.PercentVbus);
        driveRightTalon.set(0);
    }
}
