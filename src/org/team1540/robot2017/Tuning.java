package org.team1540.robot2017;

import edu.wpi.first.wpilibj.Preferences;

public class Tuning {
	
	private Preferences tuning;
	
	public Tuning() {
		tuning = Preferences.getInstance();
	}
	
	public double getShooterFlywheelSpeed() {
		return tuning.getDouble("Shooter Flywheel Target Speed", 20000); // constant constant that you could change
	}
	
//	public double getBeltPeriod() {
//		return tuning.getDouble("Shooter Belt Period", 250);
//	}
	
	public double getFeederTopPeriod() {
		return tuning.getDouble("Feeder Top Period", 250);
	}
	
	public double getFeederSideSwitchPeriod() {
		return tuning.getDouble("Feeder Side Switch Period", 250);
	}
	
	
}
