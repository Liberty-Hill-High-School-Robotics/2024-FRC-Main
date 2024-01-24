package frc.robot.subsystems;


//imports here
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.*;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;



public class Elevator extends SubsystemBase {

    //motors & variables here
    private CANSparkMax elevatorSparkMax;
    private CANSparkMax elevatorSparkMax2;



    public Elevator(){
        //config motor settings here
        elevatorSparkMax = new CANSparkMax(CanIDs.elevatorMotorID, MotorType.kBrushless);
        elevatorSparkMax.restoreFactoryDefaults();
        elevatorSparkMax.setInverted(false);
        elevatorSparkMax.setIdleMode(IdleMode.kBrake);

        elevatorSparkMax2 = new CANSparkMax(CanIDs.elevatorMotor2ID, MotorType.kBrushless);
        elevatorSparkMax2.restoreFactoryDefaults();
        elevatorSparkMax2.setInverted(false);
        elevatorSparkMax2.setIdleMode(IdleMode.kBrake);
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

    public void barRotateForward(){
        elevatorSparkMax.set(MotorSpeeds.elevatorSpeed);
        elevatorSparkMax2.set(MotorSpeeds.elevatorSpeed);
    }

    public void barRotateBackwards(){
        elevatorSparkMax.set(-MotorSpeeds.elevatorSpeed);
        elevatorSparkMax2.set(-MotorSpeeds.elevatorSpeed);
    }

    public void barRotateStop(){
        elevatorSparkMax.set(0);
        elevatorSparkMax2.set(0);
    }

}
