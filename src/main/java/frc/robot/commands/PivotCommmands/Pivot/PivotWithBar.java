package frc.robot.commands.PivotCommmands.Pivot;

import frc.robot.subsystems.Pivot;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * A simple command that grabs a hatch with the {@link HatchSubsystem}. Written explicitly for
 * pedagogical purposes. Actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
public class PivotWithBar extends Command {
  // The subsystem the command runs on
  private final Pivot m_pivot;
  private final double m_setpoint;

  public PivotWithBar(Pivot subsystem, double setpoint) {
    m_pivot = subsystem;
    addRequirements(m_pivot);
    m_setpoint = setpoint;
   // m_degree = degree;
  }

  @Override
  public void initialize() {
   
  }

  @Override
  public void execute(){
    m_pivot.pivotWithBar(m_setpoint);
  }

  @Override
  public void end(boolean interrupted){
   //m_pivot.pivotDown();
   m_pivot.pivotStop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }


}