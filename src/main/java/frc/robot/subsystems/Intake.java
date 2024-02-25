package frc.robot.subsystems;


import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//imports here
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.*;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
//import com.revrobotics.SparkLimitSwitch;

import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkBase.SoftLimitDirection;
import com.revrobotics.CANSparkLowLevel.MotorType;
//import com.revrobotics.SparkLimitSwitch.Type;



public class Intake extends SubsystemBase {

    //motors & variables here
    private CANSparkMax intake1SparkMax;
    private CANSparkMax intake2SparkMax;
    
    //private SparkLimitSwitch pivotInakeReverseLimitSwitch;
    //public RelativeEncoder pivotIntakeRelativeEncoder;

   // private final DigitalInput intakePivotHallEffectSensor;


    public Intake(){
        //config motor settings here
        intake1SparkMax = new CANSparkMax(CanIDs.pivotIntakeID, MotorType.kBrushless);
        intake1SparkMax.restoreFactoryDefaults();
        intake1SparkMax.setInverted(true);
        intake1SparkMax.setIdleMode(IdleMode.kBrake);
        intake1SparkMax.setSmartCurrentLimit(40);

       // intakePivotHallEffectSensor = new DigitalInput(3);

        
        //pivotIntakeRelativeEncoder = intake1.getEncoder();
        

       // pivotIntakeSparkMax.enableSoftLimit(SoftLimitDirection.kForward, true);
       // pivotIntakeSparkMax.setSoftLimit(SoftLimitDirection.kForward, IntakeConstants.fLimit);

        intake2SparkMax = new CANSparkMax(CanIDs.groundRollerID, MotorType.kBrushless);
        intake2SparkMax.restoreFactoryDefaults();
        intake2SparkMax.setInverted(false);
        intake2SparkMax.setIdleMode(IdleMode.kCoast);
        intake2SparkMax.setSmartCurrentLimit(40);
    }

  

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        
        //SmartDashboard.putNumber("pivotIntakeRelativeEncoder", pivotIntakeRelativeEncoder.getPosition());
        //SmartDashboard.putBoolean("pivotInakeAtReverseLimit", pivotInakeAtReverseLimit());
        SmartDashboard.getNumber("groundRollerSparkMax.getMotorTemperature", intake1SparkMax.getMotorTemperature());
        //SmartDashboard.putBoolean("pivotInakeAtForwardLimit", pivotInakeAtForwardLimit());

        /*
        if(pivotInakeAtReverseLimit() == true){
            pivotIntakeResetRelativeEncoder();
            //intakeRollerBackFeedTogeather().end(true);
        }
        */
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation


    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    
    //intake rollers
    public void intakeRollerFeed(){
        intake1SparkMax.set(MotorSpeeds.groundRollerSpeed);
        intake2SparkMax.set(MotorSpeeds.groundRollerSpeed);
    }
   

    public void intakeRollerBackFeed(){
        intake1SparkMax.set(-MotorSpeeds.groundRollerSpeed);
        intake2SparkMax.set(-MotorSpeeds.groundRollerSpeed);
    }

    public void intakeRollerBackFeedTogeather(){
        intake1SparkMax.set(-MotorSpeeds.groundRollerBackFeedSpeed);
        intake2SparkMax.set(-MotorSpeeds.groundRollerBackFeedSpeed);
    }

    public void intakeRollerStop(){
        intake1SparkMax.set(0);
        intake2SparkMax.set(0);
    }


    /*
    //intakePivot
    public void intakePivotUp(){
        pivotIntakeSparkMax.set(-MotorSpeeds.pivotIntakeSpeed);
    }
    public void intakePivotDown(){
        pivotIntakeSparkMax.set(MotorSpeeds.pivotIntakeSpeed);
    }
    public void intakePivotStop(){
        pivotIntakeSparkMax.set(0);
    }

    public boolean pivotInakeAtReverseLimit(){
    
        return (!intakePivotHallEffectSensor.get());
    }

    public boolean pivotInakeAtForwardLimit(){
        return pivotIntakeSparkMax.isSoftLimitEnabled(SoftLimitDirection.kForward);
    }

    public void pivotIntakeResetRelativeEncoder(){
        pivotIntakeRelativeEncoder.setPosition(0);
    }
    */
  



   

}






