package frc.robot.commands.DriveAutonCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;

/**
 * A simple command that grabs a hatch with the {@link HatchSubsystem}. Written explicitly for
 * pedagogical purposes. Actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
public class SetDriveRatio extends Command {
  // The subsystem the command runs on
  private final DriveSubsystem m_DriveSubsystem;
  private final double m_ratio;

  public SetDriveRatio(DriveSubsystem subsystem, double setpoint) {
    m_DriveSubsystem = subsystem;
    addRequirements(m_DriveSubsystem);
    m_ratio = setpoint;
   // m_degree = degree;
  }

  @Override
  public void initialize() {
   
  }

  @Override
  public void execute(){
    m_DriveSubsystem.setSpeedRatio(m_ratio);
  }

  @Override
  public void end(boolean interrupted){
  }

  @Override
  public boolean isFinished() {
    return false;
  }


}