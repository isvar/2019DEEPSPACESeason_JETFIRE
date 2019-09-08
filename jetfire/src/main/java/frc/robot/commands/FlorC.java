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

public class FlorC extends Command {

Timer trimi = new Timer();

  public FlorC() {
    requires(Robot.m_n);
  }

  @Override
  protected void initialize() {
    trimi.reset();
    trimi.start();
  }

  @Override
  protected void execute() {
    Robot.m_n.FlorC();

  }

  @Override
  protected boolean isFinished() {
    if (trimi.get() > 2) {
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
  }
}