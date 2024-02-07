package frc.robot.subsystems;


import edu.wpi.first.math.controller.PIDController;
//import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//imports here
import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import frc.robot.RobotContainer;
import frc.robot.Constants.*;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;



public class Shooter extends SubsystemBase {

    //motors & variables here
    private CANSparkMax shooterSparkMax;
    private CANSparkMax shooterSparkMax2;
    PIDController shooterPID = new PIDController(ShooterConstants.sP, ShooterConstants.sI, ShooterConstants.sD);
    



    public Shooter(){
        //config motor settings here
        shooterSparkMax = new CANSparkMax(CanIDs.shooterMotorID, MotorType.kBrushless);
        shooterSparkMax.restoreFactoryDefaults();
        shooterSparkMax.setInverted(true);
        shooterSparkMax.setIdleMode(IdleMode.kCoast);

        shooterSparkMax2 = new CANSparkMax(CanIDs.shooterMotor2ID, MotorType.kBrushless);
        shooterSparkMax2.restoreFactoryDefaults();
        shooterSparkMax2.setInverted(true);
        shooterSparkMax2.setIdleMode(IdleMode.kCoast);

    }

  

    @Override
    public void periodic() {
        // This method will be called once per scheduler run

    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

        SmartDashboard.putNumber("shooterSparkMax.get()", shooterSparkMax.get());
        SmartDashboard.putNumber("shooterSparkMax2.get()", shooterSparkMax2.get());

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void revShooter(double setpoint){
        shooterSparkMax.set(shooterPID.calculate(shooterSparkMax.get(), setpoint));
        shooterSparkMax2.set(shooterPID.calculate(shooterSparkMax2.get(), setpoint));
    }

    
    public void shooterFeed(){
        shooterSparkMax.set(MotorSpeeds.shooterSpeed);
        shooterSparkMax2.set(MotorSpeeds.shooterSpeed);
    }

    public void shooterBackfeed(){
        shooterSparkMax.set(-MotorSpeeds.shooterSpeed);
        shooterSparkMax2.set(-MotorSpeeds.shooterSpeed);
    }

    public void shooterStop(){
        shooterSparkMax.set(0);
        shooterSparkMax2.set(0);
    }



    //TODO: tune these values for the commands below and replace .getTa with a .getDistance command
    public double calculateAngle(){
        //effectively a linear equation (y=mx+b) where x is feet away from subwoofer, b = angle @ 0ft, m = angle subtracted each foot away from sub.
        double angle = 50; //starting angle @ 0 ft
        angle = angle - ((Limelight.roundDistance()) * PivotConstants.Slope); //subtract x angle for x number of feet away
        return angle;
    }

    public double calculateSpeed(){
        //effectively a linear equation (y=mx+b) where x is feet away from subwoofer, b = speed @ 0ft, m = speed added each foot away from sub.
        //speed is on a scale from -1 -> 1
        double speed = .2; //starting speed @ 0 ft
        speed = speed - ((Limelight.roundDistance()) * PivotConstants.Slope); //subtract x angle for x number of feet away
        return speed;
    }

}