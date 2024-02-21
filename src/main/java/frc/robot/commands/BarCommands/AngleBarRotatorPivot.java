package frc.robot.commands.BarCommands;

import frc.robot.subsystems.Bar;

import edu.wpi.first.wpilibj2.command.Command;

/**
 * A simple command that grabs a hatch with the {@link HatchSubsystem}. Written explicitly for
 * pedagogical purposes. Actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
public class AngleBarRotatorPivot extends Command {
  // The subsystem the command runs on
  private final Bar m_bar;
  private final double m_setpoint;

  public AngleBarRotatorPivot(Bar subsystem, double setpoint) {
    m_bar = subsystem;
    addRequirements(m_bar);
    m_setpoint = setpoint;
  }

  @Override
  public void initialize() {
   
  }

  @Override
  public void execute(){
    m_bar.angleBar(m_setpoint);
  }

  @Override
  public void end(boolean interrupted){
   //m_pivot.pivotDown();
   m_bar.barRotateStop();
  }

  @Override
  public boolean isFinished() {

    if(m_bar.barRotatorRelativeEncoder.getPosition() >= m_setpoint){
      return true;
    }else {
      return false;
    }
    
  }
  
}