package frc.robot.commands.PivotCommmands.Pivot;

import frc.robot.subsystems.Pivot;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * A simple command that grabs a hatch with the {@link HatchSubsystem}. Written explicitly for
 * pedagogical purposes. Actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
public class PivotStop extends Command {
  // The subsystem the command runs on
  private final Pivot m_pivot;

  public PivotStop(Pivot subsystem) {
    m_pivot = subsystem;
    addRequirements(m_pivot);
  }

  @Override
  public void initialize() {
   
  }

  @Override
  public void execute(){
    m_pivot.pivotStop();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}