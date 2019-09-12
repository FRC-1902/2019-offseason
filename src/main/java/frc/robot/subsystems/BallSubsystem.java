package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.VictorSP;

public class BallSubsystem {

    public Victor left, right;

    public BallSubsystem(){
        left = new Victor(1);
        right = new Victor(2);
//        right.setInverted(true);
    }

    public void intake(double speed){
        left.set(speed);
        right.set(speed);
    }//end of intake method
}
