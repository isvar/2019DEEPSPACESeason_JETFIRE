/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Cargo extends Subsystem {

  @Override
  public void initDefaultCommand() {
    // setDefaultCommand(new MySpecialCommand());
  }

  // Grab Cargo
  public void RollIn() {
    RobotMap.RollerLeft.set(-0.3);
    RobotMap.RollerRight.set(-0.3);
  }

  // Eject Cargo
  public void RollEject() {
    RobotMap.RollerLeft.set(0.65);
    RobotMap.RollerRight.set(0.65);
  }

  // Release Cargo Right
  public void RollRight() {
    RobotMap.RollerRight.set(0.35);
    RobotMap.RollerLeft.set(-0.6);
  }

    // Release Cargo Left
    public void RollLeft() {
      RobotMap.RollerRight.set(-0.6);
      RobotMap.RollerLeft.set(0.35);
    }

    // Cargo Stop
    public void RollStop() {
      RobotMap.RollerRight.set(0);
      RobotMap.RollerLeft.set(0);
    }

    public void ServoSet(double angle) {
      RobotMap.CameraTurret.setAngle(angle);
    }

    public void ServoStop() {
      RobotMap.CameraTurret.set(0);
    }
  }