package frc.robot.subsystems;

import com.explodingbacon.bcnlib.actuators.MotorGroup;
import com.explodingbacon.bcnlib.framework.PIDController;
import com.explodingbacon.bcnlib.sensors.BNOGyro;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import frc.robot.RobotMap;

public class DriveSubsystem {

    public MotorGroup left, right;
    public BNOGyro gyro;
    public PIDController gyroPID;

    public DriveSubsystem() {
           left = new MotorGroup(
                new CANSparkMax(RobotMap.LEFT_DRIVE_1, CANSparkMaxLowLevel.MotorType.kBrushless),
                new CANSparkMax(RobotMap.LEFT_DRIVE_2, CANSparkMaxLowLevel.MotorType.kBrushless),
                new CANSparkMax(RobotMap.LEFT_DRIVE_3, CANSparkMaxLowLevel.MotorType.kBrushless));

           right = new MotorGroup(
                new CANSparkMax(RobotMap.RIGHT_DRIVE_1, CANSparkMaxLowLevel.MotorType.kBrushless),
                new CANSparkMax(RobotMap.RIGHT_DRIVE_2, CANSparkMaxLowLevel.MotorType.kBrushless),
                new CANSparkMax(RobotMap.RIGHT_DRIVE_3, CANSparkMaxLowLevel.MotorType.kBrushless));

        gyro = new BNOGyro(true);
        gyro.rezero();

        gyroPID = new PIDController(null, gyro, 1,1,1);
        gyroPID.setRotational(true);
        gyroPID.setFinishedTolerance(0.5);

        left.set(1);
        right.set(1);
    }

    public void tankDrive(double leftPow, double rightPow) {
        left.set(leftPow);
        right.set(rightPow);
    }//end of tankDrive method

    public void arcadeDrive(double x, double y){
        tankDrive(y + x,y - x);
    }

    public void setLeft(double set) {
        left.set(set);
    }

    public void setRight(double set) {
        right.set(set);
    }

    public double getHeading() {
        return gyro.getHeading();
    }

    public void resetGyro() {
        gyro.rezero();
    }

    public BNOGyro getGyro() {
        return gyro;
    }

}
