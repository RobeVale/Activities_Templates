/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {
  
  //TODO: Set the motor ports in Constants.java
  //TODO: Set the motor to the right type (Talon, CAN, etc.)

  // The motors on the left side of the drive.
  private final SpeedControllerGroup m_leftMotors =
      new SpeedControllerGroup(new WPI_TalonFX(DriveConstants.kLeftMotor1Port),
                               new WPI_TalonFX(DriveConstants.kLeftMotor2Port));

  // The motors on the right side of the drive.
  private final SpeedControllerGroup m_rightMotors =
      new SpeedControllerGroup(new WPI_TalonFX(DriveConstants.kRightMotor1Port),
                               new WPI_TalonFX(DriveConstants.kRightMotor2Port));

  
  /**
   * Creates a new DriveSubsystem.
   */
  public DriveSubsystem() {
    m_rightMotors.setInverted(true);
  }

  /**
   * Drives the robot using tank drive controls
   * Tank drive is slightly easier to code but less intuitive to control, so this is here as an example for now
   * @param leftPower the commanded power to the left motors
   * @param rightPower the commanded power to the right motors
   */

  public void tankDrive(double leftPower, double rightPower) {
    m_leftMotors.set(leftPower);
    m_rightMotors.set(rightPower);
  }

  /**
   * Drives the robot using arcade controls.
   *
   * @param forward the commanded forward movement
   * @param turn the commanded turn rotation
   */
  public void arcadeDrive(double forward, double turn) {
    m_leftMotors.set(forward + turn);
    m_rightMotors.set(forward - turn);
  }
}
