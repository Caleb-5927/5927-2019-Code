/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.CargoIntake;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.HatchGrabber;
import frc.robot.subsystems.Tilt;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.cameraserver.CameraServer;
import frc.robot.subsystems.Climber;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static OI m_oi;
  public static Arm m_arm = new Arm();
  public static Climber m_climber = new Climber();
  public static CargoIntake m_cargoIntake = new CargoIntake();
  public static DriveTrain m_driveTrain = new DriveTrain();
  public static HatchGrabber m_hatchGrabber = new HatchGrabber();
  public static Tilt m_tilt = new Tilt();
  public Joystick buttonMonkey = new Joystick(2);
  public Joystick leftJoy = new Joystick(0);
  public Joystick rightJoy = new Joystick(1);
  public JoystickButton hatchInButton = new JoystickButton(buttonMonkey, 7);
  public JoystickButton grabHatch = new JoystickButton(buttonMonkey, 9);
  public JoystickButton cargoRHigh = new JoystickButton(buttonMonkey, 4);
  public JoystickButton cargoShip = new JoystickButton(buttonMonkey, 3);
  public JoystickButton cargoRLow = new JoystickButton(buttonMonkey, 1);
  public JoystickButton armNeutral = new JoystickButton(buttonMonkey, 2);
  public JoystickButton armNeutral1 = new JoystickButton(buttonMonkey, 6);
  public JoystickButton resetEncoders = new JoystickButton(buttonMonkey, 8);
  public JoystickButton outHigh = new JoystickButton(rightJoy, 1);
  public JoystickButton outLow = new JoystickButton(leftJoy, 1);
  public DigitalInput limit = new DigitalInput(2);
  public Compressor c = new Compressor(0);
  public JoystickButton unGrabHatch = new JoystickButton(buttonMonkey, 10);
  public JoystickButton climbButton = new JoystickButton(leftJoy, 5);
  public JoystickButton armLowerHatch = new JoystickButton(buttonMonkey, 5);
  public JoystickButton deClimbButton = new JoystickButton(rightJoy, 5);
  public JoystickButton ClimbDriveButton = new JoystickButton(leftJoy, 2);

  Command Drive;
  Command ClimbDrive;
  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_oi = new OI();
    // chooser.addOption("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", m_chooser);
    //new ArmConfig();
    c.setClosedLoopControl(true);
    CameraServer.getInstance().startAutomaticCapture();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    c.setClosedLoopControl(true);
    SmartDashboard.putData(m_cargoIntake);
    resetEncoders.whenPressed(new ArmZero());
    SmartDashboard.putNumber("Encoder: ", m_arm.getEncoder());
    SmartDashboard.putBoolean("Eye: ", m_cargoIntake.getEye());
    SmartDashboard.putData(m_driveTrain);
    SmartDashboard.putBoolean("Limit: ", limit.get());
    SmartDashboard.putData(m_arm);
    SmartDashboard.putNumber("SetPoint: ", m_arm.getSetPoint());
    SmartDashboard.putNumber("Error: ", m_arm.getError());
    SmartDashboard.putNumber("Intake Speed: ", m_cargoIntake.getOutput());
    SmartDashboard.putData(m_climber);
    unGrabHatch.whenPressed(new HatchRelease());
    hatchInButton.whileHeld(new HatchIn());
    
    if(buttonMonkey.getPOV() == -1){
      SmartDashboard.putString("Mode", "Drive");
      grabHatch.whenPressed(new HatchGrab());
      unGrabHatch.whenPressed(new HatchRelease());
      outLow.whileHeld(new CargoOutLow());
      outHigh.whileHeld(new CargoOutHigh());
      cargoRHigh.whenPressed(new ArmCargoRocketHigh());
      cargoRLow.whenPressed(new ArmCargoRocketLow());
      cargoShip.whenPressed(new ArmCargoShip());
      armNeutral.whenPressed(new ArmNeutral());
      armLowerHatch.whenPressed(new ArmHatchLow());
      armNeutral1.whenPressed(new ArmNeutral());
    } if(buttonMonkey.getPOV() != -1){
      SmartDashboard.putString("Mode", "Climb");
      ClimbDriveButton.whileHeld(new ClimbDrive());
      c.setClosedLoopControl(false);
      climbButton.whenPressed(new Climb());
      deClimbButton.whenPressed(new DeClimb());
    }
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
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
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
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
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
