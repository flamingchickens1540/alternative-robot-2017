//package org.team1540.robot2017.subsystems;
//
//import org.team1540.robot2017.Robot2;
//import org.team1540.robot2017.RobotMap;
//
//import com.ctre.CANTalon;
//import com.ctre.CANTalon.FeedbackDevice;
//import com.ctre.CANTalon.TalonControlMode;
//
//import edu.wpi.first.wpilibj.command.PIDSubsystem;
//
//public class ShooterPID extends PIDSubsystem {
//    
//    private final CANTalon talon = new CANTalon(RobotMap.shooterLeftFlywheel);
//    
//    public ShooterPID() {
//        super("Shooter", Robot2.tuning.getFlywheelP(), Robot2.tuning.getFlywheelI(), Robot2.tuning.getFlywheelD(), Robot2.tuning.getFlywheelF());
//        talon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
//        talon.configNominalOutputVoltage(+0f, -0f);
//        talon.configPeakOutputVoltage(+12f, -12f);
//        enable();
//    }
//    
//    public void setSpeed(double speed) {
//        talon.changeControlMode(TalonControlMode.Speed);
//        talon.setSetpoint(speed);
//    }
//    
//    public void stop() {
//        talon.changeControlMode(TalonControlMode.PercentVbus);
//        talon.set(0);
//    }
//    
//    @Override
//    protected double returnPIDInput() {
//        return talon.getSpeed();
//    }
//
//    @Override
//    protected void usePIDOutput(double output) {
//        talon.pidWrite(output);
//    }
//
//    @Override
//    protected void initDefaultCommand() {
//        
//    }
//    
//}
