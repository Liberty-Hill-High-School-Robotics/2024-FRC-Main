package frc.robot.commands.IntakeCommands;



import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Storage;
//import frc.robot.subsystems.Storage;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * A simple command that grabs a hatch with the {@link HatchSubsystem}. Written explicitly for
 * pedagogical purposes. Actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
public class IntakeTogether extends Command {
  // The subsystem the command runs on
  private final Intake m_intake;

  public IntakeTogether(Intake subsystem) {
    m_intake = subsystem;
    addRequirements(m_intake);
  }

  @Override
  public void initialize() {
   
  }

  @Override
  public void execute() {
     m_intake.intakeRollerFeed();
     //m_intake.intakePivotDown();
  }

  @Override
  public void end(boolean interrupted){
    m_intake.intakeRollerStop();
    //m_intake.intakeRollerBackFeedTogeather();
    //m_intake.intakePivotUp();
  }

  @Override
  public boolean isFinished() {
    //return false;
    return Storage.throughSensorBroke();
  }

  

 
}