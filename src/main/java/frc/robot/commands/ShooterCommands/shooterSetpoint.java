package frc.robot.commands.ShooterCommands;

import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * A simple command that grabs a hatch with the {@link HatchSubsystem}. Written explicitly for
 * pedagogical purposes. Actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
public class shooterSetpoint extends Command {
  // The subsystem the command runs on
  private final Shooter m_shooter;
  private final double m_setpoint;

  public shooterSetpoint(Shooter subsystem, double setpoint) {
    m_shooter = subsystem;
    addRequirements(m_shooter);
    m_setpoint = setpoint;
   // m_degree = degree;
  }

  @Override
  public void initialize() {
   
  }

  @Override
  public void execute(){
    m_shooter.shooterSetpoint(m_setpoint);
  }

  @Override
  public void end(boolean interrupted){
   //m_pivot.pivotDown();
   m_shooter.shooterStop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }


}