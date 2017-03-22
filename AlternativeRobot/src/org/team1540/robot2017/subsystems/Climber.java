package org.team1540.robot2017.subsystems;

import org.team1540.robot2017.RobotMap;
import org.team1540.robot2017.commands.JoystickClimb;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {
    
    private final CANTalon climberTopTalon = new CANTalon(RobotMap.climberTop);
    private final CANTalon climberBottomTalon = new CANTalon(RobotMap.climberBottom);
    private final CANTalon climberNewTalon = new CANTalon(RobotMap.climberNew);

    public Climber() {
        climberTopTalon.changeControlMode(TalonControlMode.PercentVbus);
        climberBottomTalon.changeControlMode(TalonControlMode.PercentVbus);
        climberNewTalon.changeControlMode(TalonControlMode.PercentVbus);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new JoystickClimb());
    }

    public void set(double value) {
        climberTopTalon.changeControlMode(TalonControlMode.PercentVbus);
        climberBottomTalon.changeControlMode(TalonControlMode.PercentVbus);
        climberTopTalon.set(-value);
        climberBottomTalon.set(value);
        climberNewTalon.set(value);
    }

    public void setTop(double value) {
        climberTopTalon.changeControlMode(TalonControlMode.PercentVbus);
        climberTopTalon.set(value);
    }

    public void setBottom(double value) {
        climberTopTalon.changeControlMode(TalonControlMode.PercentVbus);
        climberTopTalon.set(value);
    }
    
    public void setNew(double value) {
        climberNewTalon.changeControlMode(TalonControlMode.PercentVbus);
        climberNewTalon.set(value);
    }

    public double getTopClimberCurrent() {
        return climberTopTalon.getOutputCurrent();
    }

    public double getBottomClimberCurrent() {
        return climberBottomTalon.getOutputCurrent();
    }
    
    public double getNewClimberCurrent() {
        return climberNewTalon.getOutputCurrent();
    }
    
}
