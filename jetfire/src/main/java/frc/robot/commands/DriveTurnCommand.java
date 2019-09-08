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

public class DriveTurnCommand extends Command {

  double turnAngle;
  Timer timir = new Timer();

  public DriveTurnCommand(double angle) {
    requires(Robot.m_chassis);
    this.turnAngle = angle;
  }

  @Override
  protected void initialize() {
    timir.reset();
    timir.start();
  }

  @Override
  protected void execute() {
    Robot.m_chassis.Turn(turnAngle);
  }

  @Override
  protected boolean isFinished() {
    if (timir.get() > 2) {
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