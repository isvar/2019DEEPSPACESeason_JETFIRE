/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Neumatica extends Subsystem {

  // Boolean variables for piston movement
  public boolean shift = false;
  public boolean flor = false;
  public boolean pist = false;

  @Override
  public void initDefaultCommand() {
    // setDefaultCommand(new MySpecialCommand());
  }

  // Speed Change
  public void DockShift() {
    if (!shift) {
      RobotMap.DockShift.set(Value.kForward);
      shift = true;
    } else if (shift) {
      RobotMap.DockShift.set(Value.kReverse);
      shift = false;
    } else {
      RobotMap.DockShift.set(Value.kOff);
      shift = false;
    }
  }

  // Hatch Panel Mechanism
  public void Flor() {
    if (!flor) {
      RobotMap.Flor.set(Value.kForward);
      flor = true;
    } else if (flor) {
      RobotMap.Flor.set(Value.kReverse);
      flor = false;
    } else {
      RobotMap.Flor.set(Value.kOff);
      flor = false;
    }
  }

  public void FlorO() {
    RobotMap.Flor.set(Value.kForward);
  }

  public void FlorC() {
    RobotMap.Flor.set(Value.kReverse);
  }

  // Hatch Panel Mechanism retrieval and extension
  public void Pist() {
    if (!pist) {
      RobotMap.Pist.set(Value.kForward);
      pist = true;
    } else if (pist) {
      RobotMap.Pist.set(Value.kReverse);
      pist = false;
    } else {
      RobotMap.Pist.set(Value.kOff);
      pist = false;
    }
  }

}