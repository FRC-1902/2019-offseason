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

    public ArmPosition currentArmPosition;

    public PIDController armPID, armPIDSlow;

    public Potentiometer pot;
    
    public ArmSubsystem() {

        Arm1 = new WPI_VictorSPX(RobotMap.ARM_1);
        Arm2 = new WPI_VictorSPX(RobotMap.ARM_2);
        Arm3 = new WPI_VictorSPX(RobotMap.ARM_3);
        Arm3.setInverted(true);

        arm = new MotorGroup(Arm1, Arm2, Arm3);

        pot = new Potentiometer(RobotMap.ARM_POT_1);

        // p: 65, i: 0, d: 65
        // Dominic: 110, 4, 50
        armPID = new PIDController(null, pot, 110, 4, 50);
        armPID.setFinishedTolerance(0.002);

        armPIDSlow = new PIDController(null, pot, 110, 4, 50);
    }

    public void armPosition(ArmPosition pos) {
        double target = pos.value;
        if (pos == ArmPosition.NONE) target = pot.getForPID();
        armPID.setTarget(target);
        armPIDSlow.setTarget(target);
        currentArmPosition = pos;
    }
    
    private static final double GROUND_CONSTANT = 0.0822;

    public enum ArmPosition {
        NONE(-1), GROUND(0), PANEL(0.00154), ROCKET_1(0.0181), CARGO_SHIP(0.0181), UP(0); 

        public double value;

        ArmPosition(double pos) {
            this.value = pos;
            value += GROUND_CONSTANT;
        }
    }


}
