package frc.robot.commands.PivotCommmands.Pivot;

import frc.robot.subsystems.Pivot;
import frc.robot.subsystems.Storage;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * A simple command that grabs a hatch with the {@link HatchSubsystem}. Written explicitly for
 * pedagogical purposes. Actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
public class AngleAndFeed extends Command {
  // The subsystem the command runs on
  private final Pivot m_pivot;
  //private final double m_degree;

  public AngleAndFeed(Pivot subsystem) {
    m_pivot = subsystem;
    addRequirements(m_pivot);
   // m_degree = degree;
  }

  @Override
  public void initialize() {
   
  }

  @Override
  public void execute(){
    m_pivot.anglePivot();
    m_pivot.tRollerFeed();
  }

  @Override
  public void end(boolean interrupted){
   //m_pivot.pivotDown();
   m_pivot.pivotStop();
   m_pivot.tRollerStop();
  }

  @Override
  public boolean isFinished() {
    return Storage.throughSensorBroke();
  }


}