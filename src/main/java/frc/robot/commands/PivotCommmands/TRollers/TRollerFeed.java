package frc.robot.commands.PivotCommmands.TRollers;

import frc.robot.subsystems.Pivot;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * A simple command that grabs a hatch with the {@link HatchSubsystem}. Written explicitly for
 * pedagogical purposes. Actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
public class TRollerFeed extends Command {
  // The subsystem the command runs on
  private final Pivot m_pivot;

  public TRollerFeed(Pivot subsystem) {
    m_pivot = subsystem;
    addRequirements(m_pivot);
  }

  @Override
  public void initialize() {
   
  }

  @Override
  public void execute(){
    m_pivot.tRollerFeed();
  }

  @Override
  public void end(boolean interrupted){
   m_pivot.tRollerStop();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}