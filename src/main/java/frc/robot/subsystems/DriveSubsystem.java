package frc.robot.subsystems;

import com.explodingbacon.bcnlib.actuators.MotorGroup;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import frc.robot.RobotMap;

public class DriveSubsystem {

    public MotorGroup left, right;

    public DriveSubsystem() {
        left = new MotorGroup(
                new CANSparkMax(RobotMap.LEFT_DRIVE_1, CANSparkMaxLowLevel.MotorType.kBrushless),
                new CANSparkMax(RobotMap.LEFT_DRIVE_2, CANSparkMaxLowLevel.MotorType.kBrushless),
                new CANSparkMax(RobotMap.LEFT_DRIVE_3, CANSparkMaxLowLevel.MotorType.kBrushless));

        right = new MotorGroup(
                new CANSparkMax(RobotMap.RIGHT_DRIVE_1, CANSparkMaxLowLevel.MotorType.kBrushless),
                new CANSparkMax(RobotMap.RIGHT_DRIVE_2, CANSparkMaxLowLevel.MotorType.kBrushless),
                new CANSparkMax(RobotMap.RIGHT_DRIVE_3, CANSparkMaxLowLevel.MotorType.kBrushless));
        left.set(1);
        right.set(1);
    }

    public void tankDrive(double leftPow, double rightPow) {
        left.set(leftPow);
        right.set(rightPow);
    }


}
