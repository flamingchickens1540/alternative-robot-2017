package org.team1540.robot2017.commands;

import java.util.function.DoubleSupplier;

import org.team1540.robot2017.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SelfTest extends CommandGroup {
	private static class SelfTestOutputEncoderCurrent extends Command {
		private String name;
		private SelfTest self;
		private Runnable turnOn;
		private Runnable turnOff;
		private DoubleSupplier getEncoder;
		private DoubleSupplier getCurrent;
		private double startEncoder;
		private boolean encoderWorks = false;
		private boolean currentWorks = false;
		
		public SelfTestOutputEncoderCurrent(SelfTest self, String name, Runnable turnOn, Runnable turnOff,
				DoubleSupplier getEncoder, DoubleSupplier getCurrent, Subsystem... listRequires) {
			this.turnOn = turnOn;
			this.turnOff = turnOff;
			this.getEncoder = getEncoder;
			this.getCurrent = getCurrent;
			this.self = self;
			this.name = name;
			
			for (Subsystem s : listRequires) {
				requires(s);
			}
		}
		
		@Override
		protected void initialize() {
			self.registerStatus(name, false);
			if (getEncoder != null) {
				startEncoder = getEncoder.getAsDouble();
			}
			turnOn.run();
		}
		
		@Override
		protected void execute() {
			if (getEncoder != null) {
				System.out.println(name + " encoder " + getEncoder.getAsDouble());
				
				// newEncoder != startEncoder
				double newEncoder = getEncoder.getAsDouble();
				if (Math.abs(newEncoder - startEncoder) > 1.0e-6) {
					encoderWorks = true;
				}
			} else {
				encoderWorks = true;
			}
			
			if (getCurrent != null) {
				System.out.println(name + " current " + getCurrent.getAsDouble());
				
				// current != 0.0
				double current = getCurrent.getAsDouble();
				if (Math.abs(current - 0.0) > 1.0e-6) {
					currentWorks = true;
				}
			} else {
				currentWorks = true;
			}
		}
		
		@Override
		protected void end() {
			turnOff.run();
			if (encoderWorks && currentWorks) {
				self.registerStatus(name, true);
			} else {
				self.registerStatus(name, false);
			}
		}
		
		@Override
		protected boolean isFinished() {
			return encoderWorks && currentWorks;
		}
	}
	
	private static class ResetSelfTest extends Command {
		private SelfTest self;
		
		public ResetSelfTest(SelfTest self) {
			this.self = self;
		}
		
		@Override
		protected void initialize() {
			self.registerStatus("Drive Right Front", false);
			self.registerStatus("Drive Right Middle", false);
			self.registerStatus("Drive Right Back", false);
			self.registerStatus("Drive Left Front", false);
			self.registerStatus("Drive Left Middle", false);
			self.registerStatus("Drive Left Back", false);
			self.registerStatus("Belt", false);
			self.registerStatus("Climber Top", false);
			self.registerStatus("Climber Bottom", false);
			self.registerStatus("Feeder Top", false);
			self.registerStatus("Feeder Right", false);
			self.registerStatus("Feeder Left", false);
			self.registerStatus("Gear Servo Left", false);
			self.registerStatus("Gear Servo Right", false);
			self.registerStatus("Intake", false);
			self.registerStatus("Shooter Right", false);
			self.registerStatus("Shooter Left", false);
		}
		
		@Override
		protected boolean isFinished() {
			return true;
		}
	}
	
	public void registerStatus(String name, boolean status) {
		SmartDashboard.putBoolean("Self Test " + name, status);
	}
	
	public SelfTest() {
		addSequential(new ResetSelfTest(this));
		addSequential(new SelfTestOutputEncoderCurrent(this,
				"Drive Right Front",
				() -> Robot.driveTrain.setRightFront(0.7),
				() -> Robot.driveTrain.setRightFront(0.0),
				() -> Robot.driveTrain.getRightEncoder(),
				() -> Robot.driveTrain.getRightFrontCurrent(),
				Robot.driveTrain), 0.5);

		addSequential(new SelfTestOutputEncoderCurrent(this,
				"Drive Right Middle",
				() -> Robot.driveTrain.setRightMiddle(0.7),
				() -> Robot.driveTrain.setRightMiddle(0.0),
				() -> Robot.driveTrain.getRightEncoder(),
				() -> Robot.driveTrain.getRightMiddleCurrent(),
				Robot.driveTrain), 0.5);

		addSequential(new SelfTestOutputEncoderCurrent(this,
				"Drive Right Back",
				() -> Robot.driveTrain.setRightBack(0.7),
				() -> Robot.driveTrain.setRightBack(0.0),
				() -> Robot.driveTrain.getRightEncoder(),
				() -> Robot.driveTrain.getRightBackCurrent(),
				Robot.driveTrain), 0.5);
		
		addSequential(new SelfTestOutputEncoderCurrent(this,
				"Drive Left Front",
				() -> Robot.driveTrain.setLeftFront(0.7),
				() -> Robot.driveTrain.setLeftFront(0.0),
				() -> Robot.driveTrain.getLeftEncoder(),
				() -> Robot.driveTrain.getLeftFrontCurrent(),
				Robot.driveTrain), 0.5);

		addSequential(new SelfTestOutputEncoderCurrent(this,
				"Drive Left Middle",
				() -> Robot.driveTrain.setLeftMiddle(0.7),
				() -> Robot.driveTrain.setLeftMiddle(0.0),
				() -> Robot.driveTrain.getLeftEncoder(),
				() -> Robot.driveTrain.getLeftMiddleCurrent(),
				Robot.driveTrain), 0.5);

		addSequential(new SelfTestOutputEncoderCurrent(this,
				"Drive Left Back",
				() -> Robot.driveTrain.setLeftBack(0.7),
				() -> Robot.driveTrain.setLeftBack(0.0),
				() -> Robot.driveTrain.getLeftEncoder(),
				() -> Robot.driveTrain.getLeftBackCurrent(),
				Robot.driveTrain), 0.5);
		
		addSequential(new SelfTestOutputEncoderCurrent(this,
				"Belt",
				() -> Robot.belt.set(0.7),
				() -> Robot.belt.set(0.0),
				() -> Robot.belt.getBeltEncoder(),
				() -> Robot.belt.getBeltCurrent(),
				Robot.belt), 0.5);
		
		addSequential(new SelfTestOutputEncoderCurrent(this,
				"Climber Top",
				() -> Robot.climber.setTop(0.7),
				() -> Robot.climber.setTop(0.0),
				null,
				() -> Robot.climber.getTopClimberCurrent(),
				Robot.climber), 0.5);
		
		addSequential(new SelfTestOutputEncoderCurrent(this,
				"Climber Bottom",
				() -> Robot.climber.setBottom(0.7),
				() -> Robot.climber.setBottom(0.0),
				null,
				() -> Robot.climber.getBottomClimberCurrent(),
				Robot.climber), 0.5);
		
		addSequential(new SelfTestOutputEncoderCurrent(this,
				"Feeder Top",
				() -> Robot.feeder.setTop(0.7),
				() -> Robot.feeder.setTop(0.0),
				null,
				() -> Robot.feeder.getTopCurrent(),
				Robot.feeder), 0.5);

		addSequential(new SelfTestOutputEncoderCurrent(this,
				"Feeder Left",
				() -> Robot.feeder.setLeft(0.7),
				() -> Robot.feeder.setLeft(0.0),
				null,
				() -> Robot.feeder.getLeftCurrent(),
				Robot.feeder), 0.5);
		
		addSequential(new SelfTestOutputEncoderCurrent(this,
				"Feeder Right",
				() -> Robot.feeder.setRight(0.7),
				() -> Robot.feeder.setRight(0.0),
				null,
				() -> Robot.feeder.getRightCurrent(),
				Robot.feeder), 0.5);
		
		addSequential(new ToggleGearServos());
		addSequential(new SelfTestOutputEncoderCurrent(this,
				"Gear Servo Left",
				() -> Robot.gearMechanism.toggleServos(),
				() -> {},
				() -> Robot.gearMechanism.getServoLeftPosition(),
				null,
				Robot.gearMechanism), 0.5);
		addSequential(new SelfTestOutputEncoderCurrent(this,
				"Gear Servo Right",
				() -> Robot.gearMechanism.toggleServos(),
				() -> {},
				() -> Robot.gearMechanism.getServoRightPosition(),
				null,
				Robot.gearMechanism), 0.5);
		
		addSequential(new SelfTestOutputEncoderCurrent(this,
				"Intake",
				() -> Robot.intake.set(0.7),
				() -> Robot.intake.set(0.0),
				null,
				() -> Robot.intake.getIntakeCurrent(),
				Robot.intake), 0.5);
		
		addSequential(new SelfTestOutputEncoderCurrent(this,
				"Shooter Right",
				() -> Robot.shooter.setRight(0.7),
				() -> Robot.shooter.setRight(0.0),
				() -> Robot.shooter.getFlywheelEncoder(),
				() -> Robot.shooter.getRightCurrent(),
				Robot.shooter), 0.5);
		
		addSequential(new SelfTestOutputEncoderCurrent(this,
				"Shooter Left",
				() -> Robot.shooter.setLeft(0.7),
				() -> Robot.shooter.setLeft(0.0),
				() -> Robot.shooter.getFlywheelEncoder(),
				() -> Robot.shooter.getLeftCurrent(),
				Robot.shooter), 0.5);
	}
}
