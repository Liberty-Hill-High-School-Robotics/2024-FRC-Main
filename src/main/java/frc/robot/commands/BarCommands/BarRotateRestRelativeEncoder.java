package frc.robot.commands.BarCommands;

import frc.robot.subsystems.Bar;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * A simple command that grabs a hatch with the {@link HatchSubsystem}. Written explicitly for
 * pedagogical purposes. Actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
public class BarRotateRestRelativeEncoder extends Command {
  // The subsystem the command runs on
  private final Bar m_bar;

  public BarRotateRestRelativeEncoder(Bar subsystem) {
    m_bar = subsystem;
    addRequirements(m_bar);
  }

  @Override
  public void initialize() {
   
  }

  @Override
  public void execute(){
    m_bar.barRotateRestRelativeEncoder();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}