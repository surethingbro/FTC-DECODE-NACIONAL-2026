package org.firstinspires.ftc.teamcode.Intake.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class IntakeSubsystem extends SubsystemBase {
    private final DcMotor intake;
    private final Telemetry telemetry;

    public IntakeSubsystem(DcMotor intake, Telemetry telemetry) {
        this.intake = intake;
        this.telemetry = telemetry;
    }

    @Override
    public void periodic() {
        super.periodic();
        telemetry.addData("Intake running", intake.getPower() != 0);
        telemetry.addData("Intake Power", intake.getPower());
        telemetry.update();
    }

    public void runIntake() {
        intake.setPower(0.7);
    }

    public void stopIntake() {
        intake.setPower(0);
    }

    public void runIntakeReverse() {
        intake.setPower(-0.7);
    }
}
