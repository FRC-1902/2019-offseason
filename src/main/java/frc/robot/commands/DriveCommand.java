package frc.robot.commands;

import com.explodingbacon.bcnlib.framework.Command;
import com.explodingbacon.bcnlib.framework.PIDController;
import com.explodingbacon.bcnlib.utils.Utils;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.DriveSubsystem;

public class DriveCommand extends Command {
    
@Override
    public void onInit() {

    }
@Override
    public void onLoop() {
    double x = OI.driveController.getX2();
    double y = OI.driveController.getY();

    y = -y;

    x = Math.pow(Utils.deadzone(x, 0.1), 2) * Utils.sign(x);
    y = Math.pow(Utils.deadzone(y, 0.1), 2) * Utils.sign(y);

    //y *= 0.5;

    if (OI.driverSlow.get()) {
        x *= 0.75;
        y *= 0.75;
    }

    DriveSubsystem.arcadeDrive(x, y);

    //System.out.println(x + y);
    }
@Override
    public void onStop() {

    }
@Override
    public boolean isFinished() {
        return !Robot.self.isEnabled();
    }
}