package frc.robot.commands;

import com.explodingbacon.bcnlib.actuators.FakeMotor;
import com.explodingbacon.bcnlib.framework.Command;
import com.explodingbacon.bcnlib.framework.PIDController;
import com.explodingbacon.bcnlib.utils.Utils;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.FakePIDSource;
import frc.robot.OI;
import frc.robot.RevColorDistance;
import frc.robot.Robot;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.vision.VisionThread;

import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;

public class DriveCommand extends Command {
    Robot robot;
    DriveSubsystem driveSubsystem;
    boolean shiftToggle = false, isShifted = false, isBrake = false; // TODO:
    // on
    // ham
    // solo
    // shifting
    // may
    // be
    // backwards
    public PIDController leftBrakePID, rightBrakePID;

    RevColorDistance distance;
    NetworkTable table;
    NetworkTableEntry tx, ta, ts, cameraMode, ledMode, camtran;

    DriveCommand instance;


    PIDController turn = new PIDController(null, Robot.driveSubsystem.getGyro(), 0.02, 0, 0);

    public DriveCommand(Robot robot) {
        this.robot = robot;
        hasVision = false;
        distance = new RevColorDistance();


    }

    public DriveCommand(Robot robot) {
        this.robot = robot;

        distance = new RevColorDistance();

    }

    
    @Override
    public void onInit() {
        driveSubsystem = Robot.driveSubsystem;
        instance = this;
        leftBrakePID = new PIDController(new FakeMotor(), driveSubsystem.leftDriveEncoder, 0.001, 0, 0);
        leftBrakePID.enable();
        rightBrakePID = new PIDController(new FakeMotor(), driveSubsystem.rightDriveEncoder, 0.001, 0, 0);
        rightBrakePID.enable();
    }

    @Override
    public void onLoop() {
        // Log.v("Tx: " + getPixelOffset());
        // System.out.println("Drivey boi");
        // dist = distance.getDistance();
        // short dShort = dist.getShort();
        // int dInt = Byte.toUnsignedInt(dist.get(1)) * 256 +
        // Byte.toUnsignedInt(dist.get(0));

        // System.out.println("Upper: " + Byte.toUnsignedInt(dist.get(1)) + " Lower: " +
        // Byte.toUnsignedInt(dist.get(0)) + " Distance: " + dInt);
        double y;
        double x;
        if (OI.ARCADE_DRIVE) {
            x = OI.driveController.getX2();
            y = OI.driveController.getY();
            y = -y;
        } else {
            x = OI.driveController.getY2();
            y = OI.driveController.getY();
        }
        x = Math.pow(Utils.deadzone(x, 0.1), 2) * Utils.sign(x);
        y = Math.pow(Utils.deadzone(y, 0.1), 2) * Utils.sign(y);

        if (x != 0 || y != 0) {
            justLimelighted = false;
            turn.disable();
        }

        /*
         * if (OI.driveController.rightTrigger.get()) { if (!shiftToggle) { shiftToggle
         * = true; isShifted = !isShifted; } } else { shiftToggle = false; }
         */
        if (OI.driveController.rightTrigger.get()) {
            driveSubsystem.shift(false);
        } else {
            driveSubsystem.shift(true);
        }

        if (OI.driveController.leftTrigger.get()) {
            x = x / 3.0;
            y = y / 3.0;
        }
        // driveSubsystem.shift(isShifted);
        // System.out.println(driveSubsystem.getHeading());

        if (OI.driveController.y.get()) {
            driveSubsystem.resetGyro();
        }

        
    }

    @Override
    public void onStop() {

    }

    @Override
    public boolean isFinished() {
        return !Robot.self.isEnabled();
    }

    Runnable sublimeAlign = new Runnable() {
        @Override
        public void run() {
            driveSubsystem.shift(true);
            FakePIDSource fakePIDSource = new FakePIDSource();
            PIDController turn = new PIDController(null, fakePIDSource, 0.02, 0, 0);
            turn.setRotational(true);
            turn.setFinishedTolerance(0.5);
            turn.enable();
            turn.setTarget(0);
            System.out.println("Running before while");
            while (OI.driveVision.get()) {
                System.out.println("TS: " + ts.getDouble(0));

                double driverX = Math.pow(Utils.deadzone(OI.driveController.getX2(), 0.1), 2)
                        * Utils.sign(OI.driveController.getX2());
                double driverY = Math.pow(Utils.deadzone(OI.driveController.getY(), 0.1), 2)
                        * Utils.sign(-OI.driveController.getY());

                double driverY2 = Math.pow(Utils.deadzone(OI.driveController.getY2(), 0.1), 2)
                        * Utils.sign(-OI.driveController.getY2());

                double driverTrigger = Utils.deadzone(OI.driveController.getRightTrigger(), 0.1) * 0.5;

                if (OI.driveController.leftTrigger.get()) {
                    driverX = driverX / 3.0;
                    driverY = driverY / 3.0;
                }
                if (isTargetValid()) {
                    fakePIDSource.setCurrent(getPixelOffset());
                    if (OI.ARCADE_DRIVE) {
                        driveSubsystem.arcadeDrive(-turn.getMotorPower(), driverY);
                    } else {
                        driveSubsystem.arcadeDrive(-turn.getMotorPower(), driverTrigger);
                    }
                } else {
                    if (OI.ARCADE_DRIVE) {
                        driveSubsystem.arcadeDrive(driverX, driverY);
                    } else {
                        driveSubsystem.tankDrive(driverY, driverY2);
                    }
                }
                justLimelighted = false;
                limelightHoldTarget = Robot.driveSubsystem.getGyro().getForPID();
            }
        }

        ;
    };
}
