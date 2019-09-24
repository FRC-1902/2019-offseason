package frc.robot.subsystems;

import com.explodingbacon.bcnlib.actuators.MotorGroup;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.VictorSP;
import frc.robot.RobotMap;

public class BallSubsystem {

    public Victor left, right;
    public MotorGroup ball;

    public BallSubsystem() {
        ball = new MotorGroup(left = new Victor(RobotMap.BALL_1), right = new Victor(RobotMap.BALL_2));
        right.setInverted(true);
    }


    public void setIntakePower(double speed){
        ball.set(speed);
    }//end of intake method
}
