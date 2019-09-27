package frc.robot.commands;

import com.explodingbacon.bcnlib.framework.Command;
import com.explodingbacon.bcnlib.utils.Utils;

import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ArmSubsystem.ArmPosition;

public class ArmCommand extends Command {

    ArmSubsystem armSubsystem;

    double pow;

    boolean scoreHeld = false;

    public ArmCommand(Robot robot) {this.armSubsystem = robot.armSubsystem; }

    @Override
    public void onInit() {
        armSubsystem.armPID.enable();
        armSubsystem.armPosition(ArmPosition.NONE);
    }

    @Override
    public void onLoop() {
        ArmPosition newPos = null;
        ArmPosition currentPos = armSubsystem.currentArmPosition;
        if (OI.armGround.get()) newPos = ArmSubsystem.ArmPosition.GROUND;
        if (OI.armPanel.get()) newPos = ArmPosition.PANEL;
        if (OI.armUp.get() || OI.driverHeck.get()) newPos = ArmPosition.UP;
        if (OI.armScore.get()) {
            if (!scoreHeld && (currentPos == ArmPosition.CARGO_SHIP || currentPos == ArmPosition.ROCKET_1)) {
                if (currentPos == ArmPosition.CARGO_SHIP) newPos = ArmPosition.ROCKET_1;
                if (currentPos == ArmPosition.ROCKET_1) newPos = ArmPosition.CARGO_SHIP;
                OI.manipController.rumble(1, 1, 0.5);
            } else {
                if (currentPos == ArmPosition.CARGO_SHIP || currentPos == ArmPosition.ROCKET_1) {
                    newPos = currentPos;
                } else {
                    newPos = ArmPosition.ROCKET_1;
                }
            }
            scoreHeld = true;
        } else {
            scoreHeld = false;
        }
        if (newPos != null && newPos != currentPos) {
            if(!armSubsystem.armPID.isEnabled()) armSubsystem.armPID.enable();
            armSubsystem.armPosition(newPos);
            currentPos = newPos;
        }

        
        /*
        if (currentPos == ArmPosition.GROUND && Math.abs(armSubsystem.armPID.getCurrentError()) <= .005) {
            armSubsystem.armPID.disable();
            System.out.println("DISABLE ARM DROP");
        }
        */
        pow = armSubsystem.armPID.getMotorPower();
        //(currentPos == ArmPosition.GROUND) pow = pow * 0.25;


        //Dominic
        if (armSubsystem.armPID.getCurrentError() < 0) {
            pow = Utils.minMax(pow, 0.05, 0.1);
        } else {
            pow = Utils.minMax(pow, 0.05, 0.5);
        }

        if (currentPos == ArmPosition.GROUND && Math.abs(armSubsystem.armPID.getCurrentError()) <= .005) {
            armSubsystem.armPID.disable();
        }

        if (!armSubsystem.armPID.isEnabled()) pow = 0;
        armSubsystem.arm.set(pow);
    }

    @Override
    public void onStop() {}

    @Override
    public boolean isFinished() {
        return !Robot.self.isEnabled();
    }
}
