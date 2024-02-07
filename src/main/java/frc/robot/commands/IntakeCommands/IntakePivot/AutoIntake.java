package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

/**
 * A simple command that grabs a hatch with the {@link HatchSubsystem}. Written explicitly for
 * pedagogical purposes. Actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
public class Intake extends Command {
  // The subsystem the command runs on
  private final Intake m_intake;

  public Intake(Intake subsystem) {
    m_intake = subsystem;
    addRequirements(m_intake);
  }

  @Override
  public void initialize() {
   
  }

  @Override
  public void execute(){
    
  }

  @Override
  public void end(boolean interrupted){
    
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}