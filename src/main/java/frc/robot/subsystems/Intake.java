package frc.robot.subsystems;


//imports here
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.*;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;



public class Intake extends SubsystemBase {

    //motors & variables here
    private CANSparkMax pivotIntakeSparkMax;
    private CANSparkMax groundRollerSparkMax;



    public Intake(){
        //config motor settings here
        pivotIntakeSparkMax = new CANSparkMax(CanIDs.pivotIntakeID, MotorType.kBrushless);
        pivotIntakeSparkMax.restoreFactoryDefaults();
        pivotIntakeSparkMax.setInverted(false);
        pivotIntakeSparkMax.setIdleMode(IdleMode.kBrake);

        groundRollerSparkMax = new CANSparkMax(CanIDs.groundRollerID, MotorType.kBrushless);
        groundRollerSparkMax.restoreFactoryDefaults();
        groundRollerSparkMax.setInverted(false);
        groundRollerSparkMax.setIdleMode(IdleMode.kCoast);
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

    public void intakeIn(){
        groundRollerSparkMax.set(MotorSpeeds.groundRollerSpeed);
    }

    public void intakeOut(){
        groundRollerSparkMax.set(-MotorSpeeds.groundRollerSpeed);
    }

    public void intakeStop(){
        groundRollerSparkMax.set(0);
    }

}
