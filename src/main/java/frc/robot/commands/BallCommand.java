package frc.robot.commands;

import com.explodingbacon.bcnlib.framework.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.BallSubsystem;

public class BallCommand extends Command {

    BallSubsystem ballSubsystem;

    public BallCommand(Robot robot) {this.ballSubsystem = robot.ballSubsystem; }

    @Override
    public void onInit() {
        ballSubsystem.setIntakePower(0);
    }

    @Override
    public void onLoop() {
//        boolean intake = OI.manipController. .get();
//        boolean outtake = OI.manipController. .get();
//        if(intake){
//            ballSubsystem.setIntakePower(1);
//        }
//        else if(outtake){
//            ballSubsystem.setIntakePower(-1);
//        }
//        else ballSubsystem.setIntakePower(0);
    }

    @Override
    public void onStop() {

    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
