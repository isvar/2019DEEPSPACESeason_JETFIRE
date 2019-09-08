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

public class ElevatorPointCommand extends Command {
  
  double setHeight;
  Timer tim = new Timer();

  public ElevatorPointCommand(double height) {
    requires(Robot.m_elevator);
    this.setHeight = height;
  }

  @Override
  protected void initialize() {
    tim.reset();
    tim.start();
  }

  @Override
  protected void execute() {
    Robot.m_elevator.ELevatorPID(setHeight);
  }

  @Override
  protected boolean isFinished() {
    if  (tim.get() > 1) {
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