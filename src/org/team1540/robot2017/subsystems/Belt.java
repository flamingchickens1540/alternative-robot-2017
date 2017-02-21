package org.team1540.robot2017.subsystems;

import org.team1540.robot2017.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Belt extends Subsystem {

    private final CANTalon beltTalon = new CANTalon(RobotMap.beltTalon);

    public Belt() {
        beltTalon.changeControlMode(TalonControlMode.PercentVbus);
    }

    public void set(double output) {
        beltTalon.set(output);
    }

    public double getOutput() {
        return beltTalon.getOutputVoltage() / beltTalon.getBusVoltage();
    }

    public double getBeltEncoder() {
        return beltTalon.getEncPosition();
    }

    public double getBeltCurrent() {
        return beltTalon.getOutputCurrent();
    }

    @Override
    protected void initDefaultCommand() {

    }
}
