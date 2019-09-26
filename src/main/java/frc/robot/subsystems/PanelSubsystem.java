package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import frc.robot.RobotMap;


public class PanelSubsystem {

    public WPI_VictorSPX panel;

    public PanelSubsystem() {
        panel = new WPI_VictorSPX(RobotMap.PANEL_1);
    }

    public void setIntakePower(double speed){
        panel.set(speed);
    }//end of intake method
}
