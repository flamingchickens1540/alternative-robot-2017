package org.team1540.robot2017.subsystems;

import org.team1540.robot2017.RobotMap;
import org.team1540.robot2017.commands.JoystickGearSlider;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearMechanism extends Subsystem {
    private final CANTalon gearSliderTalon = new CANTalon(RobotMap.gearSliderTalon);
    private final DigitalInput leftLimitSwitch = new DigitalInput(RobotMap.gearLeftLimitSwitch);
    private final DigitalInput rightLimitSwitch = new DigitalInput(RobotMap.gearRightLimitSwitch);
    private final Servo gearRightDeployServo = new Servo(RobotMap.gearServoRight);
    private final Servo gearLeftDeployServo = new Servo(RobotMap.gearServoLeft);
    private boolean gearLock;

    public GearMechanism() {
        gearSliderTalon.changeControlMode(TalonControlMode.PercentVbus);
        initializeSliderEncoder();
    }

    public boolean isRightSwitchSet() {
        return false;
    }

    public boolean isLeftSwitchSet() {
        return false;
    }

    public void initializeCounter() {
        // counterRight.reset();
        // counterLeft.reset();
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new JoystickGearSlider());
    }

    public void joySlider(double rawAxis) {
        double max = 1;
        double min = -1;

        if (getSliderEncoder() < -2100.0) {
            min = 0;
        }

        if (getSliderEncoder() > 2700.0) {
            max = 0;
        }

        // gearSliderTalon.set(RobotUtil.limit(rawAxis, max, min));
    }

    public void slider(double vel) {
        // gearSliderTalon.set(vel);
    }

    public boolean getRightLimitSwitch() {
        return !rightLimitSwitch.get();
    }

    public boolean getLeftLimitSwitch() {
        return leftLimitSwitch.get();
    }

    public double getSliderEncoder() {
        return gearSliderTalon.getEncPosition();
    }

    public void initializeSliderEncoder() {
        gearSliderTalon.setEncPosition(0);
    }

    public void toggleServos() {
        if (gearLock) {
            gearRightDeployServo.set(1.0);
            gearLeftDeployServo.set(0.0);
        } else {
            gearRightDeployServo.set(0.0);
            gearLeftDeployServo.set(1.0);
        }
        gearLock = !gearLock;
    }

    public void closeServos() {
        gearLock = true;
        gearRightDeployServo.set(0.0);
        gearLeftDeployServo.set(1.0);
    }

    public boolean getServoOpen() {
        return !gearLock;
    }

    public double getServoRightPosition() {
        return gearRightDeployServo.get();
    }

    public double getServoLeftPosition() {
        return gearRightDeployServo.get();
    }
}
