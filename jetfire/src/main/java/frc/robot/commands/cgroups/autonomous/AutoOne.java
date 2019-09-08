/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.cgroups.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.*;
//import frc.robot.commands.cgroups.*;
import frc.robot.RobotMap;

public class AutoOne extends CommandGroup {
  public AutoOne() {
    addSequential(new DriveGoCommand(RobotMap.d1));
    addSequential(new DriveStopCommand());
    addSequential(new FlorC());
    addSequential(new DriveBackCommand(10));
    /*addSequential(new DriveTurnCommand(RobotMap.a1));
    addSequential(new DriveGoCommand(RobotMap.d2));
    addSequential(new DriveTurnCommand(RobotMap.a2));
    addSequential(new HPSetGroup());
    addSequential(new DriveTurnCommand(RobotMap.a3));
    addSequential(new DriveGoCommand(RobotMap.d4));
    addSequential(new DriveTurnCommand(RobotMap.a4));
    addSequential(new HPSetGroup());*/
  }
}