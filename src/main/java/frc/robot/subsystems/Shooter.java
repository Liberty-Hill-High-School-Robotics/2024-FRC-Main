package frc.robot.subsystems;


//imports here
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import frc.robot.Constants.*;




public class Shooter extends SubsystemBase {

    //motors & variables here
    private TalonFX flywheelTalonFX;
    private TalonFX flywheelTalonFX2;




    public Shooter(){
        //config motor settings here
        flywheelTalonFX = new TalonFX(CanIDs.flywheelMotorID);
        flywheelTalonFX.setInverted(false);
        flywheelTalonFX.setNeutralMode(NeutralModeValue.Coast);

        flywheelTalonFX2 = new TalonFX(CanIDs.flywheelMotor2ID);
        flywheelTalonFX2.setInverted(false);
        flywheelTalonFX2.setNeutralMode(NeutralModeValue.Coast);
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

    public void shooterForward(){
        flywheelTalonFX.set(MotorSpeeds.flywheelSpeed);
        flywheelTalonFX2.set(MotorSpeeds.flywheelSpeed);
    }

    public void shooterBackward(){
        flywheelTalonFX.set(-MotorSpeeds.flywheelSpeed);
        flywheelTalonFX2.set(-MotorSpeeds.flywheelSpeed);
    }

    public void shooterStop(){
        flywheelTalonFX.set(0);
        flywheelTalonFX2.set(0);
    }

}
