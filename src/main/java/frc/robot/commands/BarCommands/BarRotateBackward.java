package frc.robot.commands.BarCommands;

import frc.robot.subsystems.Bar;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * A simple command that grabs a hatch with the {@link HatchSubsystem}. Written explicitly for
 * pedagogical purposes. Actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
public class BarRotateBackward extends Command {
  // The subsystem the command runs on
  private final Bar m_bar;
 
  public BarRotateBackward(Bar subsystem) {
    m_bar = subsystem;
    addRequirements(m_bar);
  }

  @Override
  public void initialize() {
   
  }

  @Override
  public void execute(){
    m_bar.barRotateBackward();
  }

  @Override
  public void end(boolean interrupted){
    
    m_bar.barRotateStop();
    m_bar.barRotateRestRelativeEncoder();
    SmartDashboard.putBoolean("barRotateBackEnds", true);
  }

  @Override
  public boolean isFinished() {
    return m_bar.barAtReverseLimit();
  }
}