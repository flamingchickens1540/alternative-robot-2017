//package org.team1540.robot2017;
//
//import edu.wpi.first.wpilibj.IterativeRobot;
//import edu.wpi.first.wpilibj.Servo;
//
//public class Robot2 extends IterativeRobot {
//
//    private Servo servo;
//    
//    /**
//     * This function is run when the robot is first started up and should be
//     * used for any initialization code.
//     */
//    @Override
//    public void robotInit() {
//        servo = new Servo(7);
//    }
//
//    @Override
//    public void robotPeriodic() {
//    }
//
//    /**
//     * This function is called once each time the robot enters Disabled mode.
//     * You can use it to reset any subsystem information you want to clear when
//     * the robot is disabled.
//     */
//    @Override
//    public void disabledInit() {
//    }
//
//    @Override
//    public void disabledPeriodic() {
//    }
//
//    /**
//     * This autonomous (along with the chooser code above) shows how to select
//     * between different autonomous modes using the dashboard. The sendable
//     * chooser code works with the Java SmartDashboard. If you prefer the
//     * LabVIEW Dashboard, remove all of the chooser code and uncomment the
//     * getString code to get the auto name from the text box below the Gyro
//     *
//     * You can add additional auto modes by adding additional commands to the
//     * chooser code above (like the commented example) or additional comparisons
//     * to the switch structure below with additional strings & commands.
//     */
//    @Override
//    public void autonomousInit() {
//    }
//
//    /**
//     * This function is called periodically during autonomous
//     */
//    @Override
//    public void autonomousPeriodic() {
//    }
//
//    @Override
//    public void teleopInit() {
//    }
//
//    /**
//     * This function is called periodically during operator control
//     */
//    @Override
//    public void teleopPeriodic() {
//        servo.set(0.5);
//    }
//
//    /**
//     * This function is called periodically during test mode
//     */
//    @Override
//    public void testPeriodic() {
//    }
//}
