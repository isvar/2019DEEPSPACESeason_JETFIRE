/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.DriveCommand;
import frc.robot.subsystems.*;

public class Robot extends TimedRobot {
  public static OI m_oi;
  public static Chassis m_chassis = new Chassis();
  public static Neumatica m_n = new Neumatica();
  public static Cargo m_cargo = new Cargo();
  public static Elevator m_elevator = new Elevator();
  public static Misc m_misc = new Misc();

  public static double visionAngle;

  //Network Table Components
  public static NetworkTableEntry yaw_angle;

  public Robot() {
  }

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  @Override
  public void robotInit() {
    m_oi = new OI();
    m_chooser.setDefaultOption("Default Auto", new DriveCommand());
    SmartDashboard.putData("Auto mode", m_chooser);

    // Network Table Setup
    NetworkTableInstance ntinst = NetworkTableInstance.getDefault();
    NetworkTable table = ntinst.getTable("tablaCool");
    yaw_angle = table.getEntry("YawAngle");
    yaw_angle.setDefaultDouble(0);

    // Compressor Setup
    RobotMap.Comp.setClosedLoopControl(true);

    // Encoder Setup
    RobotMap.ElevatorEnc.setDistancePerPulse(RobotMap.pulRevElevator);
    RobotMap.ChassisLeftEnc.setDistancePerPulse(RobotMap.pulRevChassis);
    RobotMap.ChassisRightEnc.setDistancePerPulse(RobotMap.pulRevChassis);

    // Camera Streaming Configuration
    new Thread(() ->{
      UsbCamera camera = CameraServer.getInstance().startAutomaticCapture(0);
      camera.setResolution(320, 240 );
      UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture(1);
      camera.setResolution(320, 240);

      CvSink cvSink = CameraServer.getInstance().getVideo();
      CvSource outputStream  = CameraServer.getInstance().putVideo("Blur", 640, 480);

      Mat source = new Mat();
      Mat output = new Mat();
      while (!Thread.interrupted()) {
        cvSink.grabFrame(source);
        Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);
        outputStream.putFrame(output);
      }
    }).start();
  }

  @Override
  public void robotPeriodic() {
    // Dashboard Widgets
    SmartDashboard.putNumber("yaw Angle", Robot.m_chassis.NavX.getYaw());

    SmartDashboard.putBoolean("Flor Status", Robot.m_n.flor);
    SmartDashboard.putBoolean("Pist Status", Robot.m_n.pist);
    SmartDashboard.putBoolean("DockShift Status", Robot.m_n.shift);

    // Periodic Updates
    visionAngle = Robot.m_chassis.NavX.getYaw() - Robot.yaw_angle.getDouble(0.0);
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
    RobotMap.ElevatorEnc.reset();
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {

    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    RobotMap.ElevatorEnc.reset();
    RobotMap.ChassisLeftEnc.reset();
    RobotMap.ChassisRightEnc.reset();
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void testPeriodic() {
  }
}
