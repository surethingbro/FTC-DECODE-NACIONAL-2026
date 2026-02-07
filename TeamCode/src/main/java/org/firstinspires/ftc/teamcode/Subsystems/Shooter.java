package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

public class Shooter extends SubsystemBase {

    private final MotorEx shooterLeft;
    private final MotorEx shooterRight;
    private final ServoEx hood;

    public Shooter(final HardwareMap hardwareMap, String SLname, String SRname, String hoodName) {
        shooterLeft = new MotorEx(hardwareMap, SLname);
        shooterRight = new MotorEx(hardwareMap, SRname);
        hood = new ServoEx(hardwareMap, hoodName);

        shooterRight.setInverted(false);
        shooterLeft.setInverted(true);

        shooterLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.FLOAT);
        shooterRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.FLOAT);

        hood.set(1);

    }

    public void shoot() {
        shooterLeft.set(0.5);
        shooterRight.set(0.5);
    }

    public void stopShoot() {
        shooterLeft.stopMotor();
        shooterRight.stopMotor();
    }

}
