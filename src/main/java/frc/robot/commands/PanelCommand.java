package frc.robot.commands;

import com.explodingbacon.bcnlib.framework.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.PanelSubsystem;

public class PanelCommand extends Command {

    PanelSubsystem panelSubsystem;

    public PanelCommand(Robot robot) {this.panelSubsystem = robot.panelSubsystem; }

    @Override
    public void onInit() {
        panelSubsystem.setIntakePower(0);
    }

    @Override
    public void onLoop() {

        //change these values for real buttons
        boolean intake = OI.panelIntake.get();
        boolean outtake = OI.panelOuttake.get() || OI.driverSuperOuttake.get();

        if (intake){
            System.out.println("INTAKE INTAKE");
            panelSubsystem.setIntakePower(1);
        }
        else if (outtake){
            panelSubsystem.setIntakePower(-1);
        }
        else{
            panelSubsystem.setIntakePower(0);
        }
    }

    @Override
    public void onStop() {

    }

    @Override
    public boolean isFinished() {
        return !Robot.self.isEnabled();
    }
}
