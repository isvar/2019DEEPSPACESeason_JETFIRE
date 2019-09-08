/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.cgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.*;

public class HPSetGroup extends CommandGroup {
  public HPSetGroup() {
   // addSequential(new DriveTurnCommand(Robot.m_chassis.visionAngle));
    addSequential(new DriveGoCommand(10));
    addSequential(new FlorC());
    addSequential(new DriveGoCommand(-10));

  }
}