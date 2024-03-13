package frc.robot.commands.DriveAutonCommands;


import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.Command;


public class DriveBoostmode extends Command {
   
     private final DriveSubsystem m_robotDrive;


     public DriveBoostmode( DriveSubsystem subsystem

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
      m_robotDrive.setBoost();
    }


    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {
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
