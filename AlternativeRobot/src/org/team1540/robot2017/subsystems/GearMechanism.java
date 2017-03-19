package org.team1540.robot2017.subsystems;

import org.team1540.robot2017.Robot;
import org.team1540.robot2017.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class GearMechanism extends Subsystem {
    
    private final CANTalon gearWristTalon = new CANTalon(RobotMap.gearWrist);
    private final CANTalon gearRollersTalon = new CANTalon(RobotMap.gearRoller);
    
    public GearMechanism() {
        gearWristTalon.reverseOutput(true);
        gearWristTalon.changeControlMode(TalonControlMode.PercentVbus);
        gearRollersTalon.changeControlMode(TalonControlMode.PercentVbus);
    }
    
    public void setWrist(double value) {
        gearWristTalon.set(value);
    }
    
    public void setRollers(double value) {
        gearRollersTalon.set(value);
    }
    
    public void stop() {
        gearWristTalon.set(0);
        gearRollersTalon.set(0);
    }
    
    public double getWristCurrent() {
        return gearWristTalon.getOutputCurrent();
    }
    
    public double getRollerCurrent() {
        return gearRollersTalon.getOutputCurrent();
    }
    
    public boolean wristCurrentTooHigh() {
        return getWristCurrent() > Robot.tuning.getGearWristCurrentThreshold();
    }
    
    public boolean rollerCurrentTooHigh() {
        return getRollerCurrent() > Robot.tuning.getGearRollerCurrentThreshold();
    }
    
    @Override
    protected void initDefaultCommand() {
    }
    
}
