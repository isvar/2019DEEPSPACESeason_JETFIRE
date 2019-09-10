/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.*;

public class OI {
  // Driver Control
  Button mav_A = new JoystickButton(RobotMap.manejator, 1);
  Button mav_B = new JoystickButton(RobotMap.manejator, 2);
  Button mav_X = new JoystickButton(RobotMap.manejator, 3);
  Button mav_Y = new JoystickButton(RobotMap.manejator, 4);
  Button mav_LB = new JoystickButton(RobotMap.manejator, 5);
  Button mav_RB= new JoystickButton(RobotMap.manejator, 6);
  Button mav_Select = new JoystickButton(RobotMap.manejator, 7);
  Button mav_Start = new JoystickButton(RobotMap.manejator, 8);
  Button mav_LSClick = new JoystickButton(RobotMap.manejator, 9);
  Button mav_RSClick = new JoystickButton(RobotMap.manejator, 10);

  // Mechanism Control
  Button nav_A = new JoystickButton(RobotMap.controlator, 1);
  Button nav_B = new JoystickButton(RobotMap.controlator, 2);
  Button nav_X = new JoystickButton(RobotMap.controlator, 3);
  Button nav_Y = new JoystickButton(RobotMap.controlator, 4);
  Button nav_LB = new JoystickButton(RobotMap.controlator, 5);
  Button nav_RB= new JoystickButton(RobotMap.controlator, 6);
  Button nav_Select = new JoystickButton(RobotMap.controlator, 7);
  Button nav_Start = new JoystickButton(RobotMap.controlator, 8);
  Button nav_LSClick = new JoystickButton(RobotMap.controlator, 9);
  Button nav_RSClick = new JoystickButton(RobotMap.controlator, 10);

  public OI() {
    // Manejator Controls //
    //PID Controls
    mav_A.whileHeld(new DriveStraightCommand());
    mav_LSClick.whenPressed(new DockShiftCommand());
    mav_RSClick.whileHeld(new DriveTurnCommand(Robot.visionAngle));

    mav_Select.whileHeld(new DriveTurnCommand(-90));
    mav_Start.whileHeld(new DriveTurnCommand(90));

    mav_X.whileHeld(new DriveTurnCommand(28.75));
    mav_B.whileHeld(new DriveTurnCommand(151));

    // Servo Control
    mav_Y.whenPressed(new CameraTurretCommand(90));
    mav_LB.whenPressed(new CameraTurretCommand(20));
    mav_RB.whenPressed(new CameraTurretCommand(180));

    // Controlator Controls //
    // Pneumatics Control
    nav_LB.whenPressed(new FlorCommand());
    nav_RB.whenPressed(new PistCommand());

    // Cargo Mechanism Control
    nav_Y.whileHeld(new CargoEjectCommand());
    nav_B.whileHeld(new CargoRightCommand());
    nav_A.whileHeld(new CargoInCommand());
    nav_X.whileHeld(new CargoLeftCommand());

    // ELevator Control
    nav_LSClick.whileHeld(new ElevatorUpCommand());
    nav_Select.whileHeld(new ElevatorStaticCommand());
    nav_RSClick.whileHeld(new ElevatorDownCommand());
  }

  public Joystick getManejator() {
    return RobotMap.manejator;
  }

  public Joystick getControlator() {
    return RobotMap.controlator;
  }
}
