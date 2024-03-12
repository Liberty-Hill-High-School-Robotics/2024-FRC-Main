package frc.robot.subsystems;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//imports here
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.*;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkLimitSwitch;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
//import com.revrobotics.SparkLimitSwitch.Type;


public class Elevator extends SubsystemBase {

    //motors & variables here
    private CANSparkMax elevatorSparkMax;
    private CANSparkMax elevatorSparkMax2;
    private RelativeEncoder elevatorRelativeEncoder;
    private RelativeEncoder elevatorRelativeEncoder2;
    private  SparkLimitSwitch elevatorReverseLimit;
    private SparkLimitSwitch elevatorForwardLimit;
    
    //private




    public Elevator(){
        //config motor settings here
        elevatorSparkMax = new CANSparkMax(CanIDs.elevatorMotorID, MotorType.kBrushless);
        elevatorSparkMax.restoreFactoryDefaults();
        elevatorSparkMax.setInverted(false);
        elevatorSparkMax.setIdleMode(IdleMode.kBrake);
        elevatorSparkMax.setSmartCurrentLimit(40);

        elevatorReverseLimit = elevatorSparkMax.getReverseLimitSwitch(com.revrobotics.SparkLimitSwitch.Type.kNormallyOpen);

        elevatorSparkMax2 = new CANSparkMax(CanIDs.elevatorMotor2ID, MotorType.kBrushless);
        elevatorSparkMax2.restoreFactoryDefaults();
        elevatorSparkMax2.setInverted(true);
        elevatorSparkMax2.setIdleMode(IdleMode.kBrake);
        elevatorSparkMax2.setSmartCurrentLimit(40);

        elevatorForwardLimit = elevatorSparkMax2.getForwardLimitSwitch(com.revrobotics.SparkLimitSwitch.Type.kNormallyOpen);
        
        //elevatorRelativeEncoder.
        elevatorRelativeEncoder = elevatorSparkMax.getEncoder();
        elevatorRelativeEncoder2 = elevatorSparkMax2.getEncoder();
    }

  

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        SmartDashboard.putNumber("elevatorRelativeEncoder.getPosition()", elevatorRelativeEncoder.getPosition());
        SmartDashboard.putNumber("elevatorRelativeEncoder2.getPosition()", elevatorRelativeEncoder2.getPosition());
        SmartDashboard.putBoolean("elevatorAtForwardLimit", elevatorAtForwardLimit());
        SmartDashboard.putBoolean("elevatorAtReverseLimit", elevatorAtReverseLimit());
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public double getencoderspeed(){
        if(elevatorRelativeEncoder.getPosition() != elevatorRelativeEncoder2.getPosition()){
            return -1; //error
        }
        else{
            return elevatorRelativeEncoder.getPosition();
        }
    }

    public void elevatorUp(){
        elevatorSparkMax.set(MotorSpeeds.elevatorSpeed);
        elevatorSparkMax2.set(MotorSpeeds.elevatorSpeed);
    }

    public void elevatorDown(){
        elevatorSparkMax.set(-MotorSpeeds.elevatorSpeed);
        elevatorSparkMax2.set(-MotorSpeeds.elevatorSpeed);
    }

    public void elevatorConstant(){
        elevatorSparkMax.set(-.1);
        elevatorSparkMax2.set(-.1);
    }

    public void elevatorStop(){
        elevatorSparkMax.set(0);
        elevatorSparkMax2.set(0);
    }

    public boolean elevatorAtReverseLimit(){
        return elevatorReverseLimit.isPressed();
    }

    public boolean elevatorAtForwardLimit(){
        return elevatorForwardLimit.isPressed();
    }

   // public void barRotatorRestRelativeEncoder(){
   //     barRotatorRelativeEncoder.setPosition(0);
   // }

}