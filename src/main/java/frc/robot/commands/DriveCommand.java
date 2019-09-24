package frc.robot.commands;

import com.explodingbacon.bcnlib.framework.Command;
import com.explodingbacon.bcnlib.framework.PIDController;
import com.explodingbacon.bcnlib.utils.Utils;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.DriveSubsystem;

public class DriveCommand extends Command {

    double x = OI.driveController.getX2();
    double y = OI.driveController.getY();

    if (y >= -0.1 && y <= 0.1) {
        y = 0;
    } dd

    if (x >= -0.1 && x <= 0.1) {
        x = 0;
    } 

    

    DriveSubsystem.arcadeDrive(x, y);

@Override
    public void onInit() {

    }
@Override
    public void onLoop() {

    }
@Override
    public void onStop() {

    }
@Override
    public boolean isFinished() {
        return true;
    }
}