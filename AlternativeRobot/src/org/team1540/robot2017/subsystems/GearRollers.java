package org.team1540.robot2017.subsystems;

import org.team1540.robot2017.Robot;
import org.team1540.robot2017.RobotMap;
import org.team1540.robot2017.commands.ManualGearControl;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class GearRollers extends Subsystem {
    
    private final CANTalon gearRollersTalon = new CANTalon(RobotMap.gearRoller);
    
    public GearRollers() {
        gearRollersTalon.changeControlMode(TalonControlMode.PercentVbus);
    }
    
    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new ManualGearControl());
    }
    
    public void setRollers(double value) {
        gearRollersTalon.set(value);
    }
    
    public void stop() {
        gearRollersTalon.set(0);
    }
    
    public double getRollerCurrent() {
        return gearRollersTalon.getOutputCurrent();
    }
    
    public boolean rollerCurrentTooHigh() {
        return getRollerCurrent() > Robot.tuning.getGearRollerCurrentThreshold();
    }
    
    public double getRollerMotorOutput() {
        return gearRollersTalon.getOutputVoltage() / gearRollersTalon.getBusVoltage();
    }
    
    public boolean rollersIntaking() {
        return getRollerMotorOutput() < 0;
    }
    
}
