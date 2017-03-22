package org.team1540.robot2017.subsystems;

import org.team1540.robot2017.Robot;
import org.team1540.robot2017.RobotMap;
import org.team1540.robot2017.commands.ManualGearControl;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class GearWrist extends Subsystem {
    
    private final CANTalon gearWristTalon = new CANTalon(RobotMap.gearWrist);
    
    public GearWrist() {
        gearWristTalon.reverseOutput(true);
        gearWristTalon.changeControlMode(TalonControlMode.PercentVbus);
    }
    
    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new ManualGearControl());
    }
    
    public void setWrist(double value) {
        gearWristTalon.set(value);
    }
    
    public void stop() {
        gearWristTalon.set(0);
    }
    
    public double getWristCurrent() {
        return gearWristTalon.getOutputCurrent();
    }
    
    public boolean wristCurrentTooHigh() {
        return getWristCurrent() > Robot.tuning.getGearWristCurrentThreshold();
    }
    
}
