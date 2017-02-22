package org.team1540.robot2017.subsystems;

import org.team1540.robot2017.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Feeder extends Subsystem {

    private final CANTalon topFeederTalon = new CANTalon(RobotMap.feederFunnelingRollerTop);
    private final CANTalon leftFeederTalon = new CANTalon(RobotMap.feederFunnelingRollerLeft);
    private final CANTalon rightFeederTalon = new CANTalon(RobotMap.feederFunnelingRollerRight);

    public Feeder() {
        topFeederTalon.changeControlMode(TalonControlMode.PercentVbus);
        leftFeederTalon.changeControlMode(TalonControlMode.PercentVbus);
        rightFeederTalon.changeControlMode(TalonControlMode.PercentVbus);
        rightFeederTalon.reverseOutput(false);
        leftFeederTalon.reverseOutput(true);
    }

    public void setTop(double output) {
        topFeederTalon.changeControlMode(TalonControlMode.PercentVbus);
        topFeederTalon.set(output);
    }

    public void setLeft(double output) {
        leftFeederTalon.changeControlMode(TalonControlMode.PercentVbus);
        leftFeederTalon.set(output);
    }

    public void setRight(double output) {
        rightFeederTalon.changeControlMode(TalonControlMode.PercentVbus);
        rightFeederTalon.set(-output);
    }

    public double getTopCurrent() {
        return topFeederTalon.getOutputCurrent();
    }

    public double getRightCurrent() {
        return rightFeederTalon.getOutputCurrent();
    }

    public double getLeftCurrent() {
        return rightFeederTalon.getOutputCurrent();
    }

    @Override
    protected void initDefaultCommand() {

    }
}
