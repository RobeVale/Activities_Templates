/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.revrobotics.CANSparkMax;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.ArmConstants;

public class ArmSubsystem extends SubsystemBase {

  TalonSRX leftArmMotor = new TalonSRX(ArmConstants.kLeftArmMotorPort);
  TalonSRX rightArmMotor = new TalonSRX(ArmConstants.kRightArmMotorPort);
  TalonSRX leftIntakeMotor = new TalonSRX(ArmConstants.kLeftIntakeMotorPort);
  TalonSRX rightIntakeMotor = new TalonSRX(ArmConstants.kRightIntakeMotorPort);
  PIDController pid = new PIDController(1, 0, 0);

  public ArmSubsystem() {
    rightArmMotor.set(ControlMode.Follower, ArmConstants.kLeftArmMotorPort);       
    rightIntakeMotor.set(ControlMode.Follower, ArmConstants.kLeftIntakeMotorPort);
    leftArmMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 100);
  }
 
  public void run(double armPower) {
    double currentPosition = leftArmMotor.getSensorCollection().getQuadraturePosition();
    leftArmMotor.set(ControlMode.PercentOutput, pid.calculate(currentPosition, setpoint));
    leftIntakeMotor.set(ControlMode.PercentOutput, armPower);
  }
}
