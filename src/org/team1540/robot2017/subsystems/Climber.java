package org.team1540.robot2017.subsystems;

import org.team1540.robot2017.RobotMap;
import org.team1540.robot2017.commands.Climb;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {
	private final CANTalon climberTopTalon = new CANTalon(RobotMap.climberTalonTop);
	private final CANTalon climberBottomTalon = new CANTalon(RobotMap.climberTalonBottom);

	public Climber() {
		climberTopTalon.changeControlMode(TalonControlMode.PercentVbus);
		climberBottomTalon.changeControlMode(TalonControlMode.PercentVbus);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Climb());
	}

	public void joyClimb(double rawAxis) {
		climberTopTalon.set(rawAxis);
		climberBottomTalon.set(-rawAxis);
	}
}
