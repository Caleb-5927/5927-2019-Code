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
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import frc.robot.RobotMap;
import frc.robot.commands.ArmNeutral;
/**
 * Add your docs here.
 */
public class Arm extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public TalonSRX arm = new TalonSRX(RobotMap.ARM_TALON);
  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new ArmNeutral());
  }

  public void configTalon() {
    arm.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 20);
    arm.configClosedLoopPeakOutput(0, 0.3);
    arm.setSelectedSensorPosition(0, 0, 0);
    arm.selectProfileSlot(0, 0);
    arm.config_kP(0, 2);
    arm.config_kI(0, 0);
    arm.config_kD(0, 0);
  }

  public void armPos(int Pos) {
    arm.set(ControlMode.Position, Pos);
  }

  public void armNeutral(){
    arm.neutralOutput();
  }
  
  public void reset() {
    arm.setSelectedSensorPosition(0);
  }

  public double getEncoder() {
    return arm.getSelectedSensorPosition(0);
  }

  public double getError() {
    return arm.getClosedLoopError();
  }

  public double getSetPoint() {
    return arm.getClosedLoopTarget();
  }
  
}
