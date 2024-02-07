package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Storage;

/**
 * A simple command that grabs a hatch with the {@link HatchSubsystem}. Written explicitly for
 * pedagogical purposes. Actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
public class AutoIntake extends Command {
  // The subsystem the command runs on
  private Intake m_intake;
  private Storage m_storage;

  public void Intake(Intake subsystem) {
    m_intake = subsystem;
    addRequirements(m_intake);
  }
  public void Storage(Storage subsystem) {
    m_storage = subsystem;
    addRequirements(m_storage);
  }

  @Override
  public void initialize() {
   
  }

  @Override
  public void execute(){
    m_intake.intakePivotDown();
    m_intake.intakeRollerFeed();
    m_intake.intakePivotDown();
    m_storage.storageRollerFeed();
  }

  @Override
  public void end(boolean interrupted){
    
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}