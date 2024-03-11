// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMMotorController;
//import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;



/**
 * This is a demo program showing the use of the DifferentialDrive class, specifically it contains
 * the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private DifferentialDrive m_robotDrive;
  private GenericHID xboxController;
  

  private final VictorSP m_frontLeft = new VictorSP(5);
  private final VictorSP m_frontRight = new VictorSP(1);
  private final VictorSP m_rearLeft = new VictorSP(0);
  private final VictorSP m_rearRight = new VictorSP(3);


  @Override
  public void robotInit() {
  
    m_frontLeft.addFollower(m_rearLeft);
    m_frontRight.addFollower(m_rearRight);

    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    m_frontRight.setInverted(true);

    m_robotDrive = new DifferentialDrive(m_frontLeft::set, m_frontRight::set);
    xboxController = new XboxController(0);
    
  }

  @Override
  public void teleopPeriodic() {
    m_robotDrive.tankDrive(-xboxController.getRawAxis(1), -xboxController.getRawAxis(3));
  }
}
