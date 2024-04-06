package frc.robot.subsystems;






import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//imports here
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.*;

//import javax.swing.text.Position;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkBase.SoftLimitDirection;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.SparkAbsoluteEncoder.Type;
import edu.wpi.first.math.MathUtil;




public class Pivot extends SubsystemBase {

    //motors & variables here
    private CANSparkMax pivotSparkMax;
    private CANSparkMax pivotSparkMax2;
    private CANSparkMax transferRollerSparkMax;
    private final AbsoluteEncoder pivotAbsoluteEncoder;
    public  RelativeEncoder pivotRelativeEncoder;
    public  RelativeEncoder pivotRelativeEncoder2;

    private final DigitalInput pivotHallEffectSensor;
    private double angleCalc;

    private double barPos;

    PIDController pivotPID = new PIDController(PivotConstants.pP, PivotConstants.pI, PivotConstants.pD);
    PIDController pivotPIDSlow = new PIDController(PivotConstants.psP, PivotConstants.pI, PivotConstants.pD);

    public Pivot(){
        //config motor settings here
        pivotSparkMax = new CANSparkMax(CanIDs.pivotMotorID, MotorType.kBrushless);
        pivotSparkMax.restoreFactoryDefaults();
        pivotSparkMax.setInverted(false);
        pivotSparkMax.setIdleMode(IdleMode.kBrake);
        pivotSparkMax.setSmartCurrentLimit(60);

        pivotSparkMax2 = new CANSparkMax(CanIDs.pivotMotor2ID, MotorType.kBrushless);
        pivotSparkMax2.restoreFactoryDefaults();
        pivotSparkMax2.setInverted(true);
        pivotSparkMax2.setIdleMode(IdleMode.kBrake);
        pivotSparkMax2.setSmartCurrentLimit(60);


        transferRollerSparkMax = new CANSparkMax(CanIDs.transferRollerID, MotorType.kBrushless);
        transferRollerSparkMax.restoreFactoryDefaults();
        transferRollerSparkMax.setInverted(true);
        transferRollerSparkMax.setIdleMode(IdleMode.kCoast);
        transferRollerSparkMax.setSmartCurrentLimit(30);

        pivotAbsoluteEncoder = pivotSparkMax.getAbsoluteEncoder(Type.kDutyCycle); 
        //pivotAbsoluteEncoder.setPositionConversionFactor(.209502431724654);
        //pivotAbsoluteEncoder.setZeroOffset(0);
        

        pivotHallEffectSensor = new DigitalInput(2);

        pivotRelativeEncoder = pivotSparkMax.getEncoder();
        pivotRelativeEncoder2 = pivotSparkMax2.getEncoder();

        pivotRelativeEncoder.setPositionConversionFactor(1/.209502431724654);
        pivotRelativeEncoder2.setPositionConversionFactor(1/.209502431724654);


        pivotSparkMax.enableSoftLimit(SoftLimitDirection.kForward, true);
        pivotSparkMax2.enableSoftLimit(SoftLimitDirection.kForward, true);

        pivotSparkMax.setSoftLimit(SoftLimitDirection.kForward, 50);
        pivotSparkMax2.setSoftLimit(SoftLimitDirection.kForward, 50);

        


    }

  

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        SmartDashboard.putBoolean( "pivotHallEffectSensor", pivotAtReverseLimit() );
        SmartDashboard.putNumber("pivotRelativeEncoder.getPosition()", pivotRelativeEncoder.getPosition());
        SmartDashboard.putBoolean("pivotAtReverseLimit", pivotAtReverseLimit());
        SmartDashboard.putNumber("pivotRelativeEncoder2.getPosition()", pivotRelativeEncoder2.getPosition());

        SmartDashboard.putNumber("calculateAngle", calculateAngle());

        SmartDashboard.putNumber("pivotPID.getPositionError", pivotPID.getPositionError());
        

       if(pivotAtReverseLimit() == true){
            pivotResetRelativeEncoder();
       }

       if(Limelight.canSee){
        angleCalc = (PivotConstants.pCalcC*Math.pow(Limelight.getDistance(),PivotConstants.pCalucP) + PivotConstants.pCaluK); 
       }
       else{
        angleCalc = 0;
       }

       barPos = Bar.barRotatorRelativeEncoder.getPosition();
    

    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public double getencoderspeed(){
        return pivotAbsoluteEncoder.getPosition();
    }

    public void anglePivot(){
        double temp = pivotPID.calculate(pivotRelativeEncoder.getPosition(), calculateAngle());
        if(Limelight.canSee == false){
            pivotSparkMax.stopMotor();
            pivotSparkMax2.stopMotor();
        }
        else{
            pivotSparkMax.set(temp);
            pivotSparkMax2.set(temp);
        }
    }


    public double calculateAngle(){
        //effectively a linear equation (y=mx+b) where x is feet away from subwoofer, b = angle @ 0ft, m = angle subtracted each foot away from sub.
        double angle = angleCalc;
        return angle;
        //starting angle 40 0 ft //58.496x^{-.216}
        //subtract x angle for x number of feet away //
       
    }

    public boolean atAngle(){
        //See if the delievered and actual value for the speed of motors is within x (tolerance)
        return MathUtil.isNear(angleCalc, pivotAbsoluteEncoder.getPosition(), 3);
    }


    public void forceSubAngle(){
        double angle = 47;
        double temp = pivotPID.calculate(pivotRelativeEncoder.getPosition(), angle);
        pivotSparkMax.set(temp);
        pivotSparkMax2.set(temp);
    }

    public void forceFeedAngle(){
        double angle = 25;
        double temp = pivotPID.calculate(pivotRelativeEncoder.getPosition(), angle);
        pivotSparkMax.set(temp);
        pivotSparkMax2.set(temp);
    }

    public void pivotSetpoint(double setpoint){
        pivotSparkMax.set(pivotPID.calculate(pivotRelativeEncoder.getPosition(), setpoint));
        pivotSparkMax2.set(pivotPID.calculate(pivotRelativeEncoder.getPosition(), setpoint));
    }

    public void pivotWithBar(double setpoint){
        if(barPos >= 10 || barPos <= .5){
            pivotSparkMax.set(pivotPIDSlow.calculate(pivotRelativeEncoder.getPosition(), setpoint));
            pivotSparkMax2.set(pivotPIDSlow.calculate(pivotRelativeEncoder.getPosition(), setpoint));
        }else{
            pivotSparkMax.set(pivotPIDSlow.calculate(pivotRelativeEncoder.getPosition(), pivotRelativeEncoder.getPosition()));
            pivotSparkMax2.set(pivotPIDSlow.calculate(pivotRelativeEncoder.getPosition(), pivotRelativeEncoder.getPosition()));
        }
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
        pivotSparkMax.stopMotor();
        pivotSparkMax2.stopMotor();
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

    public void forcePivotDown(){
        double angle = 0;
        double temp = pivotPID.calculate(pivotRelativeEncoder.getPosition(), angle);
        pivotSparkMax.set(temp);
        pivotSparkMax2.set(temp);
    }
    
    


}
