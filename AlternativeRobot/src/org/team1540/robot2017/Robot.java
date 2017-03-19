package org.team1540.robot2017;

import org.team1540.robot2017.commands.AutoCrossLineBlue;
import org.team1540.robot2017.commands.AutoCrossLineRed;
import org.team1540.robot2017.commands.AutoDoNothing;
import org.team1540.robot2017.commands.AutoShoot;
import org.team1540.robot2017.commands.AutoShootAndCrossLineBlue;
import org.team1540.robot2017.commands.AutoShootAndCrossLineRed;
import org.team1540.robot2017.commands.FireShooter;
import org.team1540.robot2017.commands.PickUpGear;
import org.team1540.robot2017.commands.PlaceGear;
import org.team1540.robot2017.commands.ResetGearMechanism;
import org.team1540.robot2017.commands.SelfTest;
import org.team1540.robot2017.commands.SpinupFlywheelTeleop;
import org.team1540.robot2017.commands.TurnEverythingOff;
import org.team1540.robot2017.commands.TurnHopperOff;
import org.team1540.robot2017.commands.TurnOnIntake;
import org.team1540.robot2017.commands.UnJamFeeder;
import org.team1540.robot2017.subsystems.Belt;
import org.team1540.robot2017.subsystems.Climber;
import org.team1540.robot2017.subsystems.DriveTrain;
import org.team1540.robot2017.subsystems.Feeder;
import org.team1540.robot2017.subsystems.GearMechanism;
import org.team1540.robot2017.subsystems.Intake;
import org.team1540.robot2017.subsystems.LedBar;
import org.team1540.robot2017.subsystems.Shooter;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    public static DriveTrain driveTrain;
    public static Climber climber;
    public static GearMechanism gearMechanism;
    public static Feeder feeder;
    public static Belt belt;
    public static Intake intake;
    public static Shooter shooter;
    public static LedBar ledBar;
    public static Tuning tuning;
    public static NetworkTable kangarooTable;

    Command autonomousCommand;
    Command stopEverything;
    SendableChooser<Command> chooser;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        UsbCamera camera0 = new UsbCamera("Front", 0);
        UsbCamera camera1 = new UsbCamera("Back", 1);
        MjpegServer mjpegServer0 = new MjpegServer("Front Server", 1181);
        MjpegServer mjpegServer1 = new MjpegServer("Back Server", 1182);
        mjpegServer0.setSource(camera0);
        mjpegServer1.setSource(camera1);
        
        tuning = new Tuning();
        driveTrain = new DriveTrain();
        climber = new Climber();
        gearMechanism = new GearMechanism();
        feeder = new Feeder();
        belt = new Belt();
        intake = new Intake();
        shooter = new Shooter();
        ledBar = new LedBar();
        kangarooTable = NetworkTable.getTable("kangaroo");
        
        chooser = new SendableChooser<Command>();
        chooser.addDefault("Do Nothing", new AutoDoNothing());
        chooser.addObject("Shoot", new AutoShoot());
        chooser.addObject("Cross Line RED", new AutoCrossLineRed());
        chooser.addObject("Cross Line BLUE", new AutoCrossLineBlue());
        chooser.addObject("Shoot and Cross Line RED", new AutoShootAndCrossLineRed());
        chooser.addObject("Shoot and Cross Line BLUE", new AutoShootAndCrossLineBlue());
        SmartDashboard.putData("Autonomous Mode Chooser", chooser);
        
        stopEverything = new TurnEverythingOff();
        stopEverything.setRunWhenDisabled(true);

        OI.buttonSpinup.whenPressed(new SpinupFlywheelTeleop());
        OI.buttonFire.whenPressed(new FireShooter("Belt Target Speed", 2400));
        OI.buttonSpindown.whenPressed(new TurnHopperOff());
        OI.buttonIntakeOn.whenPressed(new TurnOnIntake());
        OI.buttonUnJam.whenPressed(new UnJamFeeder());
        OI.buttonPickUpGear.whenPressed(new PickUpGear());
        OI.buttonPlaceGear.whileHeld(new PlaceGear());
        OI.buttonResetGearMech.whenPressed(new ResetGearMechanism());
        OI.buttonSelfTest.whenPressed(new SelfTest());
    }
    
    @Override
    public void robotPeriodic() {
        SmartDashboard.putNumber("Flywheel Speed", Robot.shooter.getSpeed());
        SmartDashboard.putNumber("Flywheel Setpoint", Robot.shooter.getSetpoint());
        SmartDashboard.putNumber("Flywheel Error", Robot.shooter.getError());
        SmartDashboard.putNumber("Flywheel Output", Robot.shooter.getMotorOutput());
        SmartDashboard.putNumber("Flywheel Current Left", Robot.shooter.getFlywheelCurrent());
        SmartDashboard.putNumber("Flywheel Voltage Left", Robot.shooter.getFlywheelVoltage());
        SmartDashboard.putNumber("Climber Top Current Draw", Robot.climber.getTopClimberCurrent());
        SmartDashboard.putNumber("Climber Bottom Current Draw", Robot.climber.getBottomClimberCurrent());
        SmartDashboard.putNumber("Belt PID", Robot.belt.getPIDOutput());
        SmartDashboard.putNumber("Belt Current Draw", Robot.belt.getCurrent());
        SmartDashboard.putNumber("Belt Speed", Robot.belt.getSpeed());
        SmartDashboard.putNumber("Belt Setpoint", Robot.belt.getSetpoint());
        SmartDashboard.putNumber("Belt Encoder", Robot.belt.getBeltEncoder());
        SmartDashboard.putNumber("Belt Error", Robot.belt.getError());
        SmartDashboard.putNumber("Belt Output", Robot.belt.getOutput());
        SmartDashboard.putNumber("Drive Left Output", Robot.driveTrain.getLeftMotorOutput());
        SmartDashboard.putNumber("Drive Right Output", Robot.driveTrain.getRightMotorOutput());
        SmartDashboard.putNumber("Gear Wrist Current", Robot.gearMechanism.getWristCurrent());
        SmartDashboard.putNumber("Gear Roller Current", Robot.gearMechanism.getRollerCurrent());
    }

    /**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
     * the robot is disabled.
     */
    @Override
    public void disabledInit() {
        stopEverything.start();
        OI.copilot.setRumble(RumbleType.kLeftRumble, 0.0);
        OI.driver.setRumble(RumbleType.kLeftRumble, 0.0);
        OI.copilot.setRumble(RumbleType.kRightRumble, 0.0);
        OI.driver.setRumble(RumbleType.kRightRumble, 0.0);
    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This autonomous (along with the chooser code above) shows how to select
     * between different autonomous modes using the dashboard. The sendable
     * chooser code works with the Java SmartDashboard. If you prefer the
     * LabVIEW Dashboard, remove all of the chooser code and uncomment the
     * getString code to get the auto name from the text box below the Gyro
     *
     * You can add additional auto modes by adding additional commands to the
     * chooser code above (like the commented example) or additional comparisons
     * to the switch structure below with additional strings & commands.
     */
    @Override
    public void autonomousInit() {
        autonomousCommand = chooser.getSelected();

        /*
         * String autoSelected = SmartDashboard.getString("Auto Selector",
         * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
         * = new MyAutoCommand(); break; case "Default Auto": default:
         * autonomousCommand = new ExampleCommand(); break; }
         */

        // schedule the autonomous command (example)
        if (autonomousCommand != null)
            autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null)
            autonomousCommand.cancel();

        shooter.setPID(tuning.getFlywheelP(), tuning.getFlywheelI(), tuning.getFlywheelD(), tuning.getFlywheelF());
        belt.setPID(tuning.getBeltP(), tuning.getBeltI(), tuning.getBeltD(), tuning.getBeltF());
        
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        
        OI.copilot.setRumble(RumbleType.kRightRumble, intake.isIntaking() ? 0.3 : 0.0);
        OI.driver.setRumble(RumbleType.kRightRumble, intake.isIntaking() ? 0.3 : 0.0);

        OI.copilot.setRumble(RumbleType.kRightRumble, shooter.upToSpeed(tuning.getShooterFlywheelSpeed() 
                + RobotUtil.betterDeadzone(OI.getFlywheelSpeedJoystick(), 0.15, 2.0) 
                * Robot.tuning.getFlywheelSpeedChangeCoefficient()) ? 0.7 : 0.0);
        OI.driver.setRumble(RumbleType.kRightRumble, shooter.upToSpeed(tuning.getShooterFlywheelSpeed() 
                + RobotUtil.betterDeadzone(OI.getFlywheelSpeedJoystick(), 0.15, 2.0) 
                * Robot.tuning.getFlywheelSpeedChangeCoefficient()) ? 0.7 : 0.0);
    }

    /**
     * This function is called periodically during test mode
     */
    @Override
    public void testPeriodic() {
        LiveWindow.run();
    }
}
