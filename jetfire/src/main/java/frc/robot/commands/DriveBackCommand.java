/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class DriveBackCommand extends Command {

  double bDistance;
  Timer tim = new Timer();

  public DriveBackCommand(double length) {
    requires(Robot.m_chassis);
    this.bDistance = length;
  }

  @Override
  protected void initialize() {
    RobotMap.ChassisLeft.reset();
    RobotMap.ChassisRight.reset();
    Robot.m_chassis.encoderAverage = 0;
    tim.reset();
    tim.start();
  }

  @Override
  protected void execute() {
    Robot.m_chassis.GoBack(bDistance);
  }

  @Override
  protected boolean isFinished() {
    if (-Robot.m_chassis.encoderAverage > bDistance) {
      return true;
    } else {
      return false;
    } 
  }

  @Override
  protected void end() {
    Robot.m_chassis.DriveStop();
  }

  @Override
  protected void interrupted() {
    end();
  }
}