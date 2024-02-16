package frc.robot.subsystems;


import edu.wpi.first.math.controller.PIDController;
//import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//imports here
import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import frc.robot.RobotContainer;
import frc.robot.Constants.*;

import com.revrobotics.CANSparkFlex;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;



public class Shooter extends SubsystemBase {

    //motors & variables here
    private CANSparkFlex shooterVortex;
    private CANSparkFlex shooterVortex2;
    private RelativeEncoder shooterVortexRelativeEncoder;
    private RelativeEncoder shooterVortex2RelativeEncoder;

    PIDController shooterPID = new PIDController(ShooterConstants.sP, ShooterConstants.sI, ShooterConstants.sD);
    



    public Shooter(){
        //config motor settings here
        shooterVortex = new CANSparkFlex(CanIDs.shooterMotorID, MotorType.kBrushless);
        shooterVortex.restoreFactoryDefaults();
        shooterVortex.setInverted(false);
        shooterVortex.setIdleMode(IdleMode.kCoast);

        shooterVortex2 = new CANSparkFlex(CanIDs.shooterMotor2ID, MotorType.kBrushless);
        shooterVortex2.restoreFactoryDefaults();
        shooterVortex2.setInverted(false);
        shooterVortex2.setIdleMode(IdleMode.kCoast);

        shooterVortexRelativeEncoder = shooterVortex.getEncoder();
        shooterVortex2RelativeEncoder = shooterVortex2.getEncoder();

    }

  

    @Override
    public void periodic() {
        // This method will be called once per scheduler run

    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

        SmartDashboard.putNumber("shooterSparkMax.get()", shooterVortex.get());
        SmartDashboard.putNumber("shooterSparkMax2.get()", shooterVortex2.get());

        SmartDashboard.putNumber("shooterSparkMaxRelativeEncoder.getVelocity()", shooterVortexRelativeEncoder.getVelocity());
        SmartDashboard.putNumber("shooterSparkMax2RelativeEncoder.getVelocity()", shooterVortex2RelativeEncoder.getVelocity());
        
        SmartDashboard.putNumber("calculateSpeed", calculateSpeed());
        
        


    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public void revShooter(double setpoint){
       
        shooterVortex.set(setpoint);
        shooterVortex2.set(setpoint);
    }

    
    //TODO: tune these values for the commands below and replace .getTa with a .getDistance command

    public double calculateSpeed(){
        //effectively a linear equation (y=mx+b) where x is feet away from subwoofer, b = speed @ 0ft, m = speed added each foot away from sub.
        //speed is on a scale from -1 -> 1
        double speed = .2; //starting speed @ 0 ft
        speed = speed - ((Limelight.getDistance()) * PivotConstants.Slope); //subtract x angle for x number of feet away
        return speed;
    }
    
    public void shooterFeed(){
        shooterVortex.set(MotorSpeeds.shooterSpeed);
        shooterVortex2.set(MotorSpeeds.shooterSpeed);
    }

    public void shooterBackfeed(){
        shooterVortex.set(-MotorSpeeds.shooterSpeed);
        shooterVortex2.set(-MotorSpeeds.shooterSpeed);
    }

    public void shooterStop(){
        shooterVortex.stopMotor();
        shooterVortex2.stopMotor();
    }
}