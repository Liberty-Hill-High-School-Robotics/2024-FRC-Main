package frc.robot.commands.StorageCommands;


import frc.robot.subsystems.Storage;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * A simple command that grabs a hatch with the {@link HatchSubsystem}. Written explicitly for
 * pedagogical purposes. Actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
public class AutoFeedNote extends Command {
  // The subsystem the command runs on
  private final Storage m_storage;

  public AutoFeedNote(Storage subsystem) {
    m_storage = subsystem;
    addRequirements(m_storage);
  }

  @Override
  public void initialize() {
   
  }

  @Override
  public void execute(){
    m_storage.storageRollerFeed();
  }

  @Override
  public void end(boolean interrupted){
    m_storage.storageRollerStop();
  }

  @Override
  public boolean isFinished() {
    return Storage.throughSensorBrokeInvert();
  }
}