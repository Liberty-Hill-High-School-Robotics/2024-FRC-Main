package frc.robot.subsystems;


import edu.wpi.first.math.controller.PIDController;
//import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//imports here
import edu.wpi.first.wpilibj2.command.SubsystemBase;
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
        shooterSparkMax.setInverted(false);
        shooterSparkMax.setIdleMode(IdleMode.kCoast);

        shooterSparkMax2 = new CANSparkMax(CanIDs.shooterMotor2ID, MotorType.kBrushless);
        shooterSparkMax2.restoreFactoryDefaults();
        shooterSparkMax2.setInverted(false);
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

}
