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



public class Bar extends SubsystemBase {

    //motors & variables here
    private CANSparkMax barRotatorSparkMax;
    private SparkLimitSwitch barReverseLimitSwitch;
    public RelativeEncoder barRotatorRelativeEncoder;



    public Bar(){
        //config motor settings here
        barRotatorSparkMax = new CANSparkMax(CanIDs.barRotatorID, MotorType.kBrushless);
        barRotatorSparkMax.restoreFactoryDefaults();
        barRotatorSparkMax.setInverted(true);
        barRotatorSparkMax.setIdleMode(IdleMode.kBrake);

        barReverseLimitSwitch = barRotatorSparkMax.getReverseLimitSwitch(Type.kNormallyOpen);

        barRotatorSparkMax.enableSoftLimit(SoftLimitDirection.kForward, true);
        barRotatorSparkMax.setSoftLimit(SoftLimitDirection.kForward, BarConstants.fLimit);

        barRotatorRelativeEncoder = barRotatorSparkMax.getEncoder();
    }

  

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        SmartDashboard.putNumber("barRotatorRelativeEncoder", barRotatorRelativeEncoder.getPosition());

        if(barAtReverseLimit() == true){
            barRotateRestRelativeEncoder();
        }

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

    public void barRotateBackward(){
        barRotatorSparkMax.set(-MotorSpeeds.barRotatorSpeed);
    }

    public void barRotateStop(){
        barRotatorSparkMax.set(0);
    }

    public boolean barAtReverseLimit(){
    
        return barReverseLimitSwitch.isPressed();
    }

    public boolean barAtRotateForwardLimit(){
        return barRotatorSparkMax.isSoftLimitEnabled(SoftLimitDirection.kForward);
    }

    public void barRotateRestRelativeEncoder(){
        barRotatorRelativeEncoder.setPosition(0);
    }



}
