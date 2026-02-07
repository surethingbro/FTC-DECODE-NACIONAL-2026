package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

public class Intake extends SubsystemBase {

    private final MotorEx intake;

    public Intake(final HardwareMap hardwareMap, final String name) {
        intake = new MotorEx(hardwareMap, name);

        intake.setRunMode(Motor.RunMode.RawPower);
        intake.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        intake.setInverted(true);
    }

    public void runIntake() {
        intake.set(0.8);
    }

    public void stop() {
        intake.stopMotor();
    }


}
