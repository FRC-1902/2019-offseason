/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ArmCommand;
import frc.robot.commands.BallCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.PanelCommand;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.BallSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.PanelSubsystem;

public class Robot extends TimedRobot {
  public static DriveSubsystem driveSubsystem;
  public static PanelSubsystem panelSubsystem;
  public static ArmSubsystem armSubsystem;
  public static BallSubsystem ballSubsystem;
  public static Robot self;

  @Override
  public void robotInit() {
    driveSubsystem   = new DriveSubsystem();
    panelSubsystem  = new PanelSubsystem();
    armSubsystem    = new ArmSubsystem();
    ballSubsystem   = new BallSubsystem();

    self = this;
    /*
    SmartDashboard.putNumber("kP", 0);
    SmartDashboard.putNumber("kI", 0);
    SmartDashboard.putNumber("kD", 0);*/

  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void disabledPeriodic() {
    System.out.println("Arm Pot: " + armSubsystem.pot.getForPID());
  }

  @Override
  public void autonomousInit() {
    OI.runCommand(new ArmCommand(this));
    OI.runCommand(new DriveCommand());
    OI.runCommand(new PanelCommand(this));
    OI.runCommand(new BallCommand(this));
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    
    armSubsystem.armPIDSlow.reTune(SmartDashboard.getNumber("kP", 0),
    SmartDashboard.getNumber("kI", 0),
    SmartDashboard.getNumber("kD", 0));
    
    OI.runCommand(new ArmCommand(this));
    OI.runCommand(new DriveCommand());
    OI.runCommand(new PanelCommand(this));
    OI.runCommand(new BallCommand(this));
  }


  @Override
  public void teleopPeriodic() {
      armSubsystem.armPIDSlow.logVerbose();
      armSubsystem.armPID.logVerbose();
  }

  @Override
  public void testInit() {
    //Drive Subsystem Test
    /*
    driveSubsystem.left.testEachWait(0.5, 1);
    driveSubsystem.right.testEachWait(0.5, 1);
    ballSubsystem.ball.testEachWait(0.5, 1); 
    panelSubsystem.setIntakePower(0.5);
    armSubsystem.arm.testEachWait(0.5, 0.5);
    */
  }

  @Override
  public void testPeriodic() {
  }
}
