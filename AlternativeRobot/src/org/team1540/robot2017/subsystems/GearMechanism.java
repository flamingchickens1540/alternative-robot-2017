package org.team1540.robot2017.subsystems;

import org.team1540.robot2017.RobotMap;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearMechanism extends Subsystem {
    private final Servo gearRightDeployServo = new Servo(RobotMap.gearServoRight);
    private final Servo gearLeftDeployServo = new Servo(RobotMap.gearServoLeft);
    private boolean gearLock;

    public GearMechanism() {
    }

    public boolean isRightSwitchSet() {
        return false;
    }

    public boolean isLeftSwitchSet() {
        return false;
    }

    @Override
    protected void initDefaultCommand() {
    }


    public void toggleServos() {
        if (gearLock) {
            gearRightDeployServo.set(1.0);
            gearLeftDeployServo.set(0.0);
        } else {
            gearRightDeployServo.set(0.0);
            gearLeftDeployServo.set(1.0);
        }
//        if (gearLock) {
//            gearRightDeployServo.set(0.0);
//            gearLeftDeployServo.set(1.0);
//        } else {
//            gearRightDeployServo.set(1.0);
//            gearLeftDeployServo.set(0.0);
//        }
        gearLock = !gearLock;
    }

    public void closeServos() {
        gearLock = true;
        gearRightDeployServo.set(0.0);
        gearLeftDeployServo.set(1.0);
//        gearRightDeployServo.set(1.0);
//        gearLeftDeployServo.set(0.0);
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
