package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.explodingbacon.bcnlib.actuators.MotorGroup;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.Potentiometer;

import com.explodingbacon.bcnlib.framework.PIDController;

public class ClimbSubsystem {

    MotorGroup climber;

    WPI_VictorSPX Climb_1;
    WPI_VictorSPX Climb_2;
    WPI_VictorSPX Climb_3;
    
    Potentiometer climbPot;

    PIDController climbPID;

    ClimbPosition currentClimbPosition;

    public ClimbSubsystem() {

        Climb_1 = new WPI_VictorSPX(RobotMap.ARM_1);
        Climb_2 = new WPI_VictorSPX(RobotMap.ARM_2);
        Climb_3 = new WPI_VictorSPX(RobotMap.ARM_3);

        climber = new MotorGroup(Climb_1, Climb_2, Climb_3);

        climbPID = new PIDController(null, climbPot, 0, 0, 0, 0, 0);

    }

    public void climbPosition (ClimbPosition pos) {
        climbPID.setTarget(pos.value);
        currentClimbPosition = pos;
    }// end of climbPosition method

    private static final double GROUND_CONSTANT = 0; //can be tuned later for the correct value

    public enum ClimbPosition {
        GROUND(0), LEVEL_2(0), LEVEL_3(0);

        public double value;

        ClimbPosition(double pos) {
            this.value = pos;
            value += GROUND_CONSTANT;
        }
    }


}
