package frc.robot;

import com.explodingbacon.bcnlib.controllers.Button;
import com.explodingbacon.bcnlib.controllers.XboxController;
import com.explodingbacon.bcnlib.framework.AbstractOI;

public class OI extends AbstractOI {
    public static XboxController driveController = new XboxController(0);
    public static XboxController manipController = new XboxController(1);

    public static Button panelIntake = manipController.rightBumper;
    public static Button panelOuttake = manipController.leftBumper;

    public static Button driverSuperOuttake = driveController.leftBumper;

    public static Button ballIntake = manipController.rightTrigger;
    public static Button ballOuttake = manipController.leftTrigger;

    public static Button armGround = manipController.a,
                        armPanel = manipController.b,
                        armScore = manipController.x,
                        armUp = manipController.y;

    //public static Button driveVision = driveController.leftBumper;

    public static Button driverHeck = driveController.rightTrigger;
    public static Button driverSlow = driveController.leftTrigger;
}