package org.firstinspires.ftc.teamcode.Intake.Subsystems;

import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class IntakeSubsystem extends SubsystemBase {
    private final Motor intake_motor;
    private final Telemetry telemetry;

    public IntakeSubsystem(Motor intake_motor, Telemetry telemetry) {
        this.intake_motor = intake_motor;
        this.telemetry = telemetry;

    }

    @Override
    public void periodic() {
        telemetry.addData("Intake running", intake_motor.get() != 0);
        telemetry.addData("Intake Power", intake_motor.get());
        telemetry.update();
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
