/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.DriveCommand;
import edu.wpi.first.wpilibj.Timer;

public class Chassis extends PIDSubsystem {

  // Encoder Variables
  public double encoderAverage;

  // NavX
  public AHRS NavX = new AHRS(SPI.Port.kMXP);
  
  // Differential Drive
  DifferentialDrive m_Drive = new DifferentialDrive(RobotMap.DriveLeft, RobotMap.DriveRight);

  // PID Variables
  double rotate;

  // Acceleration Variables
  double previousX = 0;
	double dx = 0.3;

	double previousY = 0;
	double dy = 0.3;

	double maxX = 0.85;
  double maxY = 0.95;

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DriveCommand());
  }

  // PID Genesis
  public Chassis() {
    super("Chassis", 0.14, 0.07, 0.39);
    getPIDController().setAbsoluteTolerance(2.0f);
    getPIDController().setInputRange(-180.0f, 180.0f);
    getPIDController().setContinuous(true);
    getPIDController().setOutputRange(-1.0, 1.0);
    getPIDController().disable();
  }

  // PID Entry Point
  protected double returnPIDInput() {
    return NavX.getYaw();
}

// Result of PID
protected void usePIDOutput(double output) {
  rotate = output;
}

// Teleop Drive
public void Drive(Joystick manejator) {
  // Restict Y
  double y = RobotMap.manejator.getRawAxis(1) * maxY;
  if (y > previousY + dy) {
    y = previousY + dy;
  } else if (y < previousY - dy) {
    y = previousY - dy;
  }
  previousY = y;
  // Restrict X
  double x = RobotMap.manejator.getRawAxis(4) * maxX;
  if (x > previousX + dx) {
    x = previousX + dx;
  } else if (x < previousX - dx) {
    x = previousX - dx;
  }
  previousX = x;

  m_Drive.arcadeDrive(-y, x);
  getPIDController().disable();
  Timer.delay(0.005);
  }

  // PID Turn
  public void Turn(double setpoint) {
    if (!getPIDController().isEnabled()) {
      rotate = 0;
      getPIDController().setSetpoint(setpoint);
      getPIDController().enable();
    }
    double rotationValue = rotate;
    m_Drive.arcadeDrive(0, rotationValue*maxY);
  }

  // PID Drive Straight
  public void DriveStraight() {
    if (!getPIDController().isEnabled()) {
      rotate = 0;
      getPIDController().setSetpoint(NavX.getYaw());
      getPIDController().enable();
    }
    double rotationValue = rotate;
    m_Drive.arcadeDrive(RobotMap.manejator.getRawAxis(1)*maxY, rotationValue*maxX);
  }  

  // NavX Yaw Reset
  public void ResetYaw() {
    NavX.zeroYaw();
    getPIDController().disable();
  }

  // Chassis Full Stop
  public void DriveStop() {
    m_Drive.arcadeDrive(0, 0);
    getPIDController().disable();
  }

  //Autonomous Test Routines
  public void Go(double distance) {
    encoderAverage = (RobotMap.ChassisLeft.getDistance() + RobotMap.ChassisRight.getDistance()) / 2;
    if (distance > encoderAverage) {
      if (!getPIDController().isEnabled()) {
        rotate = 0;
        getPIDController().setSetpoint(NavX.getYaw());
        getPIDController().enable();
      }
      double rotationValue = rotate;
      m_Drive.arcadeDrive(-0.7, rotationValue*maxX);
    } else {
      m_Drive.arcadeDrive(0, 0);
    }
  }

  public void GoBack(double distance) {
    encoderAverage = (RobotMap.ChassisLeft.getDistance() + RobotMap.ChassisRight.getDistance()) / 2;
    if (distance > encoderAverage) {
      if (!getPIDController().isEnabled()) {
        rotate = 0;
        getPIDController().setSetpoint(NavX.getYaw());
        getPIDController().enable();
      }
      double rotationValue = rotate;
      m_Drive.arcadeDrive(0.7, rotationValue*maxX);
    } else {
      m_Drive.arcadeDrive(0, 0);
    }
  }
}