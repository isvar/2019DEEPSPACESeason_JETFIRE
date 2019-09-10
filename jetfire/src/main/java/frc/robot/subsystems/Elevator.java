/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.RobotMap;

public class Elevator extends PIDSubsystem {

  double speed;

  @Override
  public void initDefaultCommand() {
  }

  public Elevator() {
    super("Elevator", 0.1, 0.05, 0.22);
    getPIDController().setAbsoluteTolerance(1);
    getPIDController().setInputRange(0, 100);
    getPIDController().setOutputRange(0.2 , 1.0);
    getPIDController().disable();
  }

  protected double returnPIDInput() {
    return RobotMap.ElevatorEnc.getDistance();
  }

  protected void usePIDOutput(double output) {
    speed = output;
  }

  // Move ELevator
  public void ElevatorMUp(Joystick controlator) {
    RobotMap.ElevatorLeft.set(0.7);
    RobotMap.ElevatorRight.set(-0.7);
    getPIDController().disable();
  }

  public void ElevatorMDown(Joystick controlator) {
    RobotMap.ElevatorLeft.set(-0.15);
    RobotMap.ElevatorRight.set(0.15);
    getPIDController().disable();
  }

  public void ElevatorStatic() {
    RobotMap.ElevatorLeft.set(0.3);
    RobotMap.ElevatorRight.set(-0.3);
    getPIDController().disable();
  }

  // Don't Move Elevator
  public void ElevatorStop() {
    RobotMap.ElevatorLeft.set(0);
    RobotMap.ElevatorRight.set(0);
    getPIDController().disable();
  }

  public void ELevatorPID(double setpoint) {
    if (!getPIDController().isEnabled()) {
      speed = 0;
      getPIDController().setSetpoint(setpoint);
      getPIDController().enable();
    }
    double speedValue = speed;
    RobotMap.ElevatorLeft.set(speedValue);
    RobotMap.ElevatorRight.set(speedValue);
  }
}
