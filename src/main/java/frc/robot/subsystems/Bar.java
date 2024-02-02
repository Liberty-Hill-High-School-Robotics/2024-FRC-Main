package frc.robot.subsystems;


//imports here
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.*;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;



public class Bar extends SubsystemBase {

    //motors & variables here
    private CANSparkMax barRotatorSparkMax;



    public Bar(){
        //config motor settings here
        barRotatorSparkMax = new CANSparkMax(CanIDs.barRotatorID, MotorType.kBrushless);
        barRotatorSparkMax.restoreFactoryDefaults();
        barRotatorSparkMax.setInverted(true);
        barRotatorSparkMax.setIdleMode(IdleMode.kBrake);
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
        barRotatorSparkMax.set(MotorSpeeds.barRotatorSpeed);
    }

    public void barRotateBackwards(){
        barRotatorSparkMax.set(-MotorSpeeds.barRotatorSpeed);
    }

    public void barRotateStop(){
        barRotatorSparkMax.set(0);
    }

}
