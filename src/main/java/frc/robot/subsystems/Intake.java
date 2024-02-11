package frc.robot.subsystems;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//imports here
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.*;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkLimitSwitch;

import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkBase.SoftLimitDirection;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.SparkLimitSwitch.Type;



public class Intake extends SubsystemBase {

    //motors & variables here
    private CANSparkMax pivotIntakeSparkMax;
    private CANSparkMax groundRollerSparkMax;
    
    private SparkLimitSwitch pivotInakeReverseLimitSwitch;
    public RelativeEncoder pivotIntakeRelativeEncoder;



    public Intake(){
        //config motor settings here
        pivotIntakeSparkMax = new CANSparkMax(CanIDs.pivotIntakeID, MotorType.kBrushless);
        pivotIntakeSparkMax.restoreFactoryDefaults();
        pivotIntakeSparkMax.setInverted(false);
        pivotIntakeSparkMax.setIdleMode(IdleMode.kBrake);

        pivotInakeReverseLimitSwitch = pivotIntakeSparkMax.getReverseLimitSwitch(Type.kNormallyOpen);
        pivotIntakeRelativeEncoder = pivotIntakeSparkMax.getEncoder();

        pivotIntakeSparkMax.enableSoftLimit(SoftLimitDirection.kForward, true);
        pivotIntakeSparkMax.setSoftLimit(SoftLimitDirection.kForward, IntakeConstants.fLimit);

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

        SmartDashboard.putNumber("pivotIntakeRelativeEncoder", pivotIntakeRelativeEncoder.getPosition());

        if(pivotInakeAtReverseLimit() == true){
            pivotIntakeResetRelativeEncoder();
        }


    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    //intake rollers
    public void intakeRollerFeed(){
        groundRollerSparkMax.set(MotorSpeeds.groundRollerSpeed);
    }
   

    public void intakeRollerBackFeed(){
        groundRollerSparkMax.set(-MotorSpeeds.groundRollerSpeed);
    }

    public void intakeRollerStop(){
        groundRollerSparkMax.set(0);
    }


    //intakePivot
    public void intakePivotUp(){
        pivotIntakeSparkMax.set(MotorSpeeds.pivotIntakeSpeed);
    }
    public void intakePivotDown(){
        pivotIntakeSparkMax.set(-MotorSpeeds.pivotIntakeSpeed);
    }
    public void intakePivotStop(){
        pivotIntakeSparkMax.set(0);
    }

    public boolean pivotInakeAtReverseLimit(){
    
        return pivotInakeReverseLimitSwitch.isPressed();
    }

    public boolean pivotInakeAtForwardLimit(){
        return pivotIntakeSparkMax.isSoftLimitEnabled(SoftLimitDirection.kForward);
    }

    public void pivotIntakeResetRelativeEncoder(){
        pivotIntakeRelativeEncoder.setPosition(0);
    }

  



   

}






