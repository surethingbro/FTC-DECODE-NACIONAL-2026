package org.firstinspires.ftc.teamcode.Robot.Intake.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class IntakeSubsystem extends SubsystemBase {

    private final Motor intake_motor;

    public IntakeSubsystem(final HardwareMap hardwareMap, final String name) {
        intake_motor = new Motor(hardwareMap, name);

        intake_motor.setRunMode(Motor.RunMode.RawPower);
        intake_motor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
    }

    public void runIntake() {
        intake_motor.set(0.7);
    }

    public void stopIntake() {
        intake_motor.stopMotor();
    }

    public void runIntakeReverse() {
        intake_motor.set(-0.7);
    }


}
