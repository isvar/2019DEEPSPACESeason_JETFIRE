/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.Servo;

public class RobotMap {
  // Joysticks
  public static Joystick manejator = new Joystick(0);
  public static Joystick controlator = new Joystick(1);
  
  // Speed controllers
  public static PWMVictorSPX DriveLeft = new PWMVictorSPX(0);
  public static PWMVictorSPX DriveRight = new PWMVictorSPX(1);
  public static PWMVictorSPX ElevatorRight = new PWMVictorSPX(5);
  public static PWMVictorSPX ElevatorLeft = new PWMVictorSPX(6);
  public static PWMVictorSPX RollerLeft = new PWMVictorSPX(2);
  public static PWMVictorSPX RollerRight = new PWMVictorSPX(3);

  //Compressor
  public static Compressor Comp = new Compressor(0);

  // Solenoids
  public static DoubleSolenoid DockShift = new DoubleSolenoid(4, 5);
  public static DoubleSolenoid Flor = new DoubleSolenoid(0, 1);
  public static DoubleSolenoid Pist = new DoubleSolenoid(2, 3);

  //Encoders
  public static Encoder ChassisLeft = new Encoder(0, 1, true, Encoder.EncodingType.k4X);
  public static Encoder ChassisRight = new Encoder(2, 3, false, Encoder.EncodingType.k4X);
  public static Encoder Elevator = new Encoder(4, 5, false, Encoder.EncodingType.k4X);

  // Servo
  public static Servo CameraTurret = new Servo(4);

  // Variables
  public static double d1 = 67;
  public static double a1 = 135;
  public static double e1= 0;
  public static double e2;
  public static double e3;

  // Camer Number
  public static int server = 0;
}