/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;
  public static int LEFT_MASTER_TALON = 1;
  public static int RIGHT_MASTER_TALON = 0;
  public static int LEFT_SLAVE_TALON = 2;
  public static int RIGHT_SLAVE_TALON = 3;
  public static int ARM_TALON = 12;
  public static int INTAKE_MOTOR = 0;
  public static int GRABBER_SOLENIOD = 0;
  public static int TILT_SOLENIOD = 1;
  public static int CLIMB = 2;
  public static int PHOTO_EYE = 0;
      //1290 = bottom Rocket Cargo
    //4920 = Top Rocket Cargo
    //3000 = Cargo Cargoship
  public static int ROCKET_BOTTOM_CARGO = 1290;
  public static int ROCKET_TOP_CARGO = 4920;
  public static int CARO_SHIP = 3500;
  public static int SPEED_MULTIPLIER = 1;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
}
