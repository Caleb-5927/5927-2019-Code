/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import frc.robot.commands.Drive;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public TalonSRX rightMaster = new TalonSRX(0);
  public TalonSRX leftMaster = new TalonSRX(1);
  public TalonSRX rightSlave = new TalonSRX(3);
  public TalonSRX leftSlave = new TalonSRX(2);
  private Double rampRate = 1.0;


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new Drive());
  }

  public void Drive(double leftSpeed, double rightSpeed) {
    leftMaster.set(ControlMode.PercentOutput, leftSpeed);
    rightMaster.set(ControlMode.PercentOutput, rightSpeed);
  }

  public void configDrive() {
    leftSlave.follow(leftMaster);
    rightSlave.follow(rightMaster);
    leftSlave.setNeutralMode(NeutralMode.Brake);
    rightSlave.setNeutralMode(NeutralMode.Brake);
    leftMaster.setNeutralMode(NeutralMode.Brake);
    rightMaster.setNeutralMode(NeutralMode.Brake);
  }

  public void RampOn() {
    leftSlave.configOpenloopRamp(rampRate);
    leftMaster.configOpenloopRamp(rampRate);
    rightMaster.configOpenloopRamp(rampRate);
    rightSlave.configOpenloopRamp(rampRate);
  }

  public void RampOff() {
    leftSlave.configOpenloopRamp(0);
    leftMaster.configOpenloopRamp(0);
    rightMaster.configOpenloopRamp(0);
    rightSlave.configOpenloopRamp(0);
  }
}
