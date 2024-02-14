package frc.robot.subsystems;


import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//imports here
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.*;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.SparkAbsoluteEncoder.Type;
//import java.lang.Math.*;



public class Pivot extends SubsystemBase {

    //motors & variables here
    private CANSparkMax pivotSparkMax;
    private CANSparkMax pivotSparkMax2;
    private CANSparkMax transferRollerSparkMax;
    private final AbsoluteEncoder pivotAbsoluteEncoder;
    private final RelativeEncoder pivotRelativeEncoder;
    private final RelativeEncoder pivotRelativeEncoder2;


    private final DigitalInput pivotHallEffectSensor;

     PIDController pivotPID = new PIDController(PivotConstants.pP, PivotConstants.pI, PivotConstants.pD);

    public Pivot(){
        //config motor settings here
        pivotSparkMax = new CANSparkMax(CanIDs.pivotMotorID, MotorType.kBrushless);
        pivotSparkMax.restoreFactoryDefaults();
        pivotSparkMax.setInverted(false);
        pivotSparkMax.setIdleMode(IdleMode.kBrake);

        pivotSparkMax2 = new CANSparkMax(CanIDs.pivotMotor2ID, MotorType.kBrushless);
        pivotSparkMax2.restoreFactoryDefaults();
        pivotSparkMax2.setInverted(true);
        pivotSparkMax2.setIdleMode(IdleMode.kBrake);

        transferRollerSparkMax = new CANSparkMax(CanIDs.transferRollerID, MotorType.kBrushless);
        transferRollerSparkMax.restoreFactoryDefaults();
        transferRollerSparkMax.setInverted(true);
        transferRollerSparkMax.setIdleMode(IdleMode.kCoast);

        pivotAbsoluteEncoder = pivotSparkMax.getAbsoluteEncoder(Type.kDutyCycle); 
        pivotAbsoluteEncoder.setPositionConversionFactor(180/(Math.PI));
        //pivotAbsoluteEncoder.setZeroOffset(0);

        pivotHallEffectSensor = new DigitalInput(2);

        pivotRelativeEncoder = pivotSparkMax.getEncoder();
        pivotRelativeEncoder2 = pivotSparkMax2.getEncoder();



    }

  

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        SmartDashboard.putBoolean( "pivotHallEffectSensor", pivotAtReverseLimit() );
        SmartDashboard.putNumber("pivotAbsoluteEncoder.getPosition()", pivotAbsoluteEncoder.getPosition());
        SmartDashboard.putBoolean("pivotAtReverseLimit", pivotAtReverseLimit());
        SmartDashboard.putNumber("pivotRelativeEncoder.getPosition()", pivotRelativeEncoder.getPosition());

       if(pivotAtReverseLimit() == true){
            pivotResetRelativeEncoder();
       }

    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void anglePivot(double degree){
        pivotSparkMax.set(pivotPID.calculate(pivotAbsoluteEncoder.getPosition(), degree));
        pivotSparkMax2.set(pivotPID.calculate(pivotAbsoluteEncoder.getPosition(), degree));
    }

    public double calculateAngle(){
        //effectively a linear equation (y=mx+b) where x is feet away from subwoofer, b = angle @ 0ft, m = angle subtracted each foot away from sub.
        double angle = 50; //starting angle @ 0 ft
        angle = angle - ((Limelight.roundDistance()) * PivotConstants.Slope); //subtract x angle for x number of feet away
        return angle;
    }

    public void pivotUp(){
        pivotSparkMax.set(MotorSpeeds.pivotSpeed);
        pivotSparkMax2.set(MotorSpeeds.pivotSpeed);
    }

    public void pivotDown(){
        pivotSparkMax.set(-MotorSpeeds.pivotSpeed);
        pivotSparkMax2.set(-MotorSpeeds.pivotSpeed);
    }

    public void pivotStop(){
        pivotSparkMax.set(0);
        pivotSparkMax2.set(0);
    }

    //transfer roller
    public void tRollerFeed(){
        transferRollerSparkMax.set(MotorSpeeds.transferRollerSpeed);
    }

    public void tRollerBackFeed(){
        transferRollerSparkMax.set(-MotorSpeeds.transferRollerSpeed);
    }

    public void tRollerStop(){
        transferRollerSparkMax.set(0);
    }

    public boolean pivotAtReverseLimit(){
        return (!pivotHallEffectSensor.get());
    }

    public void pivotResetRelativeEncoder(){
            pivotRelativeEncoder.setPosition(0);
            pivotRelativeEncoder2.setPosition(0);
    }
    
    


}
