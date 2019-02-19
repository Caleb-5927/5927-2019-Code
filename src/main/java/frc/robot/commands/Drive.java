/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class Drive extends Command {


  public Drive() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_driveTrain);
 }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_driveTrain.configDrive();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Robot.m_oi.getRightY() < -0.1 && Robot.m_oi.getLeftY() < -0.1) {
      Robot.m_driveTrain.RampOn();
    } else {
      Robot.m_driveTrain.RampOff();
    }

    Robot.m_driveTrain.Drive(Robot.m_oi.getRightY() * RobotMap.SPEED_MULTIPLIER, Robot.m_oi.getLeftY() * -RobotMap.SPEED_MULTIPLIER);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
