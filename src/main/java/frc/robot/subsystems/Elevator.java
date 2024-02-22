package frc.robot.subsystems;


//imports here
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.*;

import com.fasterxml.jackson.databind.AnnotationIntrospector.ReferenceProperty.Type;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkLimitSwitch;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.SparkLimitSwitch.Type;


public class Elevator extends SubsystemBase {

    //motors & variables here
    private CANSparkMax elevatorSparkMax;
    private CANSparkMax elevatorSparkMax2;
    private RelativeEncoder elevatorRelativeEncoder;
    private RelativeEncoder elevatorRelativeEncoder2;
    private static SparkLimitSwitch elevatorReverseLimit;
    private SparkLimitSwitch elevatorForwardLimit;
    
    //private




    public Elevator(){
        //config motor settings here
        elevatorSparkMax = new CANSparkMax(CanIDs.elevatorMotorID, MotorType.kBrushless);
        elevatorSparkMax.restoreFactoryDefaults();
        elevatorSparkMax.setInverted(true);
        elevatorSparkMax.setIdleMode(IdleMode.kCoast);
        elevatorSparkMax.setSmartCurrentLimit(60);

        elevatorReverseLimit = elevatorSparkMax.getReverseLimitSwitch(Type.kOpen);

        elevatorSparkMax2 = new CANSparkMax(CanIDs.elevatorMotor2ID, MotorType.kBrushless);
        elevatorSparkMax2.restoreFactoryDefaults();
        elevatorSparkMax2.setInverted(true);
        elevatorSparkMax2.setIdleMode(IdleMode.kCoast);
        elevatorSparkMax2.setSmartCurrentLimit(60);

        //elevatorRelativeEncoder.
    }

  

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
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
        //elevatorSparkMax.set(MotorSpeeds.elevatorSpeed);
        elevatorSparkMax2.set(MotorSpeeds.elevatorSpeed);
    }

    public void elevatorDown(){
        //elevatorSparkMax.set(-MotorSpeeds.elevatorSpeed);
        elevatorSparkMax2.set(-MotorSpeeds.elevatorSpeed);
    }

    public void elevatorStop(){
        elevatorSparkMax.set(0);
        elevatorSparkMax2.set(0);
    }

}
