package frc.robot.commands;

import com.explodingbacon.bcnlib.framework.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.BallSubsystem;

public class BallCommand extends Command {

    BallSubsystem ballSubsystem;

    public BallCommand(Robot robot) {
        this.ballSubsystem = robot.ballSubsystem; 
    }

    @Override
    public void onInit() {
        ballSubsystem.setIntakePower(0);
    }

    @Override
    public void onLoop() {
        boolean intake = OI.ballIntake.get();
        boolean outtake = OI.ballOuttake.get() || OI.driverSuperOuttake.get();
        if(intake){
            ballSubsystem.setIntakePower(1);
        }
        else if(outtake){
            ballSubsystem.setIntakePower(-0.3333);
        }
        else ballSubsystem.setIntakePower(0);
    }

    @Override
    public void onStop() {

    }

    @Override
    public boolean isFinished() {
        return !Robot.self.isEnabled();
    }
}
