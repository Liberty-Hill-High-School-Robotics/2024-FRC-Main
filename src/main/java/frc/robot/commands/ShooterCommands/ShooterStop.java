package frc.robot.commands.ShooterCommands;

import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * A simple command that grabs a hatch with the {@link HatchSubsystem}. Written explicitly for
 * pedagogical purposes. Actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
public class ShooterStop extends Command {
  // The subsystem the command runs on
  private final Shooter m_shooter;

  public ShooterStop(Shooter subsystem) {
    m_shooter = subsystem;
    addRequirements(m_shooter);
  }

  @Override
  public void initialize() {
   
  }

  @Override
  public void execute(){
    m_shooter.shooterStop();
  }

  @Override
  public void end(boolean interrupted){
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}