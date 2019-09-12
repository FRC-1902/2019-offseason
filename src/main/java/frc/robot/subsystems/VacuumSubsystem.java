package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.explodingbacon.bcnlib.actuators.MotorGroup;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class VacuumSubsystem {


    WPI_VictorSPX Vacuum_1;
    

    public VacuumSubsystem() {

        Vacuum_1 = new WPI_VictorSPX(RobotMap.ARM_1);


    }

    public void vacuum(double vacuumPower) {
        
        Vacuum_1.set(vacuumPower);

    }//end of vacuum method

    private static final double OFF_CONSTANT = 0; //can be tuned later for the correct value

    public enum VacuumPosition {
        ON(0), OFF(0);

        public double value;

        VacuumPosition(double pos) {
            this.value = pos;
            value += OFF_CONSTANT;
        }
    }


}
