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

public class DriveStopCommand extends Command {

  Timer timi = new Timer();

  public DriveStopCommand() {
    requires(Robot.m_chassis);
  }

  @Override
  protected void initialize() {
    RobotMap.ChassisLeft.reset();
    RobotMap.ChassisRight.reset();
    Robot.m_chassis.encoderAverage = 0;
    timi.reset();
    timi.start();
  }

  @Override
  protected void execute() {
    Robot.m_chassis.DriveStop();
  }

  @Override
  protected boolean isFinished() {
    if (timi.get() > 2) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    end();
  }
}