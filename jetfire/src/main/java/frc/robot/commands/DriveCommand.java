/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveCommand extends Command {
  public DriveCommand() {
    requires(Robot.m_chassis);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.m_chassis.Drive(Robot.m_oi.getManejator());
  }

  @Override
  protected boolean isFinished() {
    return false;
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