/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class Misc extends Subsystem {

  @Override
  public void initDefaultCommand() {
  }
    // Camera Controls
    public void ServoSet(double angle) {
      RobotMap.CameraTurret.setAngle(angle);
    }
    public void ServoStop() {
      RobotMap.CameraTurret.set(0);
    }

    // NavX Yaw Reset
    public void ResetYaw() {
      Robot.m_chassis.NavX.zeroYaw();
      Robot.m_chassis.getPIDController().disable();
  }
  }
