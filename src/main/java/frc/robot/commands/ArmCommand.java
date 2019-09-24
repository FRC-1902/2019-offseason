package frc.robot.commands;

import com.explodingbacon.bcnlib.framework.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.ArmSubsystem;

public class ArmCommand extends Command {

    ArmSubsystem armSubsystem;

    boolean pidEnabled = armSubsystem.armPID.isEnabled();
    double pow;

    ArmCommand(Robot robot) {this.armSubsystem = robot.armSubsystem; }

    @Override
    public void onInit() {
        armSubsystem.armPID.enable();
        armSubsystem.armPosition(ArmSubsystem.ArmPosition.GROUND);
    }

    @Override
    public void onLoop() {

        if (OI.manipController.a.get()) armSubsystem.armPosition(ArmSubsystem.ArmPosition.GROUND);
        if (OI.manipController.b.get()) armSubsystem.armPosition(ArmSubsystem.ArmPosition.CARGO_SHIP);
        if (OI.manipController.y.get()) armSubsystem.armPosition(ArmSubsystem.ArmPosition.ROCKET_1);

        if (OI.manipController.a.get()||OI.manipController.b.get()||OI.manipController.y.get()){
            if(!pidEnabled) armSubsystem.armPID.enable();

        pow = armSubsystem.armPID.getMotorPower();
        armSubsystem.arm.set(pow);


        }
    }

    @Override
    public void onStop() {}

    @Override
    public boolean isFinished() {
        return !Robot.self.isEnabled();
    }
}
