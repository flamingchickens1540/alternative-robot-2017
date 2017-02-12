package org.team1540.robot2017.subsystems;

import org.team1540.robot2017.RobotMap;
import org.team1540.robot2017.RobotUtil;
import org.team1540.robot2017.commands.Climb;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {
	private final CANTalon climberTopTalon = new CANTalon(RobotMap.climberTop);
	private final CANTalon climberBottomTalon = new CANTalon(RobotMap.climberBottom);

	public Climber() {
		climberTopTalon.changeControlMode(TalonControlMode.PercentVbus);
		climberBottomTalon.changeControlMode(TalonControlMode.PercentVbus);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Climb());
	}

	public void joyClimb(double rawAxis) {
		climberTopTalon.set(RobotUtil.deadzone(rawAxis, 0.2));
		climberBottomTalon.set(RobotUtil.deadzone(-rawAxis, 0.2));
	}
}
