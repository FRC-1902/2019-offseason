package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import frc.robot.RobotMap;


public class PanelSubsystem {

    public WPI_VictorSPX panel;

    public PanelSubsystem() {
        panel = new WPI_VictorSPX(1);
    }

    public void intake(double speed){
        panel.set(speed);
    }
}
