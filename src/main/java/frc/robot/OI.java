/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Compressor;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  public Joystick buttonMonkey = new Joystick(2);
  public Joystick leftJoy = new Joystick(0);
  public Joystick rightJoy = new Joystick(1);
  public JoystickButton hatchInButton = new JoystickButton(buttonMonkey, 8);
  public JoystickButton grabHatch = new JoystickButton(buttonMonkey, 9);
  public JoystickButton cargoRHigh = new JoystickButton(buttonMonkey, 4);
  public JoystickButton cargoShip = new JoystickButton(buttonMonkey, 3);
  public JoystickButton cargoRLow = new JoystickButton(buttonMonkey, 1);
  public JoystickButton armNeutral = new JoystickButton(buttonMonkey, 2);
  public JoystickButton armNeutral1 = new JoystickButton(buttonMonkey, 6);
  public JoystickButton resetEncoders = new JoystickButton(buttonMonkey, 7);
  public JoystickButton outHigh = new JoystickButton(rightJoy, 1);
  public JoystickButton outLow = new JoystickButton(leftJoy, 1);
  public JoystickButton unGrabHatch = new JoystickButton(buttonMonkey, 10);
  public JoystickButton climbButton = new JoystickButton(leftJoy, 5);
  public JoystickButton armLowerHatch = new JoystickButton(buttonMonkey, 5);
  public JoystickButton deClimbButton = new JoystickButton(rightJoy, 5);
  public JoystickButton ClimbDriveButton = new JoystickButton(leftJoy, 2);
  


  public double getLeftY(){
    return leftJoy.getY();
  }

  public double getRightY(){
    return rightJoy.getY();
  }
  
  resetEncoders.whenPressed(new ArmZero());
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

  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
}
