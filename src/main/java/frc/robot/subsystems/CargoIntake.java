/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.RobotMap;
import frc.robot.commands.CargoNeutral;

/**
 * Add your docs here.
 */
public class CargoIntake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public Spark intake = new Spark(RobotMap.INTAKE_MOTOR);
  public DigitalInput eye = new DigitalInput(RobotMap.PHOTO_EYE);
  private boolean eyeValueReversed;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new CargoNeutral());
  }

  public void inLow() {
    intake.set(-0.7);
  }

  public void inHigh() {
    intake.set(0.7);
  }

  public void outHigh() {
    intake.set(-0.7);
  }
  
  public void outLow() {
    intake.set(0.7);
  }

  public boolean getEye() {
    eyeValueReversed = !eye.get();
    return eyeValueReversed;
  }

  public void neutral(){
    intake.set(0);
  }

  public double getOutput() {
    return intake.getSpeed();
  }
}
