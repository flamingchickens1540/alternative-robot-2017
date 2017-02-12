package org.team1540.robot2017.subsystems;

import org.team1540.robot2017.RobotMap;
import org.team1540.robot2017.RobotUtil;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearMechanism extends Subsystem {

	private final CANTalon gearSliderTalon = new CANTalon(RobotMap.gearSliderTalon);
//	private final Servo gearRightDeployServo = new Servo(RobotMap.gearServoRightDeploy);
//	private final Servo gearLeftDeployServo = new Servo(RobotMap.gearServoLeftDeploy);
	
	DigitalInput limitSwitch = new DigitalInput(1);
	Counter counter = new Counter(limitSwitch);
	private double autoSpeed;
	private boolean useJoystick;
	private Encoder sliderEncoder;
	
	public GearMechanism() {
		gearSliderTalon.changeControlMode(TalonControlMode.PercentVbus);
		autoSpeed=0;
		sliderEncoder = new Encoder(0, 1, false, Encoder.EncodingType.k2X);
	}
	
	public boolean isSwitchSet() {
        return counter.get() > 0;
    }
	
	public void initializeCounter() {
        counter.reset();
    }
	
	@Override
	protected void initDefaultCommand() {
		
	}
	
	public void joySlider(double rawAxis) {
		if (useJoystick) {
			System.out.print(sliderEncoder.get()); // ??
			if (sliderEncoder.get()>1000) {
				gearSliderTalon.set(RobotUtil.limit(RobotUtil.deadzone(rawAxis, 0.2), 1, 0));
			} else {
				gearSliderTalon.set(RobotUtil.deadzone(rawAxis, 0.2));
			}
		} else {
			gearSliderTalon.set(autoSpeed);
		}
				
	}
	
	public void sliderStop() {
		autoSpeed = 0;
	}
	
	public void sliderSlowCalib() {
		autoSpeed = 0.3;
	}
	
	public void enableJoystickControl() {
		useJoystick = true;
	}
	
	public void disableJoystickControl() {
		useJoystick = false;
	}

	public void initializeSliderEncoder() {
		sliderEncoder.setMaxPeriod(.1);
		sliderEncoder.setMinRate(10);
		sliderEncoder.setDistancePerPulse(5);
		sliderEncoder.setReverseDirection(false);
		sliderEncoder.setSamplesToAverage(7);
		sliderEncoder.reset();
	}
}
