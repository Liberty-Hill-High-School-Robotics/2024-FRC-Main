package frc.robot.subsystems;


import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//imports here
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.*;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class Storage extends SubsystemBase {

    //motors & variables here
    private CANSparkMax storageRollerSparkMax;
    public static DigitalInput throughSensor = new DigitalInput(0);
    public static boolean noteInTB;


    public Storage(){
        //config motor settings here
        storageRollerSparkMax = new CANSparkMax(CanIDs.storageRollerMotorID, MotorType.kBrushless);
        storageRollerSparkMax.restoreFactoryDefaults();
        storageRollerSparkMax.setInverted(true);
        storageRollerSparkMax.setIdleMode(IdleMode.kBrake);
        storageRollerSparkMax.setSmartCurrentLimit(60);

    }

  

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        SmartDashboard.putBoolean("throughSensorBroke()", throughSensorBroke());
        if(!throughSensor.get()){
            noteInTB = true;
        }
        else{
            noteInTB = false;
        }
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void storageRollerFeed(){
        storageRollerSparkMax.set(MotorSpeeds.storageRollerSpeed);
    }

    public void storageRollerBackFeed(){
        storageRollerSparkMax.set(-MotorSpeeds.storageRollerSpeed);
    }

    public void storageRollerStop(){
        storageRollerSparkMax.set(0);
    }

    public static boolean throughSensorBroke(){
        return (!throughSensor.get());
    }

    public boolean throughSensorBrokeS(){
        return (!throughSensor.get());
    }

    public static boolean throughSensorBrokeInvert(){
        return (throughSensor.get());
    }
}
