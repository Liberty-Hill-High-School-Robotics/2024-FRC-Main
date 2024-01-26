package frc.robot.commands.IntakeCommands;

import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * A simple command that grabs a hatch with the {@link HatchSubsystem}. Written explicitly for
 * pedagogical purposes. Actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
public class IntakeStop extends Command {
  // The subsystem the command runs on
  private final Intake m_intake;

  public IntakeStop(Intake subsystem) {
    m_intake = subsystem;
    addRequirements(m_intake);
  }

  @Override
  public void initialize() {
   
  }

  @Override
  public void execute(){
    m_intake.intakeStop();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}