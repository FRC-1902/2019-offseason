package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.explodingbacon.bcnlib.actuators.MotorGroup;


import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

import frc.robot.Potentiometer;

import com.explodingbacon.bcnlib.framework.PIDController;

public class ArmSubsystem {

    public MotorGroup arm;

    WPI_VictorSPX Arm1;
    WPI_VictorSPX Arm2;
    WPI_VictorSPX Arm3;

    ArmPosition currentArmPosition;

    public PIDController armPID;

    public Potentiometer pot;
    
    public ArmSubsystem() {

        Arm1 = new WPI_VictorSPX(RobotMap.ARM_1);
        Arm2 = new WPI_VictorSPX(RobotMap.ARM_2);
        Arm3 = new WPI_VictorSPX(RobotMap.ARM_3);
        Arm3.setInverted(true);

        arm = new MotorGroup(Arm1, Arm2, Arm3);

        pot = new Potentiometer(RobotMap.ARM_POT_1);

        armPID = new PIDController(null, pot, 0, 0, 0, 0, 0);

    }

    public void armPosition(ArmPosition pos) {
        armPID.setTarget(pos.value);
        currentArmPosition = pos;
    }//end of armPosition method
    
    private static final double GROUND_CONSTANT = 0; //can be tuned later for the correct value

    public enum ArmPosition {
        GROUND(0), CARGO_SHIP(0), ROCKET_1(0); 

        public double value;

        ArmPosition(double pos) {
            this.value = pos;
            value += GROUND_CONSTANT;
        }
    }


}
