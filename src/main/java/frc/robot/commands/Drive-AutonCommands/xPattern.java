package frc.robot.commands;

/*
unused imports

import java.util.List;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.DriveConstants;

*/
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.Command;


public class xPattern extends Command {
   
     private final DriveSubsystem m_robotDrive;


    public xPattern( DriveSubsystem subsystem

    ) {

        m_robotDrive = subsystem;
        addRequirements(m_robotDrive);

      
        
        
        // m_subsystem = subsystem;
        // addRequirements(m_subsystem);    


        
    }

    

    // Called just before this Command runs the first time
    @Override
    public void initialize() {
        
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        //potentially add a if statement that does not activate while joysticks have value?
        //robot will have conflicting commands if drivers drive and hold the set X button at the same time
      m_robotDrive.setX();
    }


    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
    }


    @Override
    public boolean isFinished(){
        return false;
    }
   

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }

}
