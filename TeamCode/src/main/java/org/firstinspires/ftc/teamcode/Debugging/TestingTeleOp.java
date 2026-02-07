package org.firstinspires.ftc.teamcode.Debugging;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name = "final")
public class TestingTeleOp extends LinearOpMode {

    DcMotorEx frontLeft;
    DcMotorEx frontRight;
    DcMotorEx backLeft;
    DcMotorEx backRight;

    DcMotorEx intake;
    DcMotorEx transfer;
    DcMotorEx shooterLeft;
    DcMotorEx shooterRight;

    Servo hood;

    public double hoodPosition = 1;

    double cycletime = 0;
    double looptime = 0;
    double oldtime = 0;

    @Override
    public void runOpMode() throws InterruptedException {

        frontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
        backLeft = hardwareMap.get(DcMotorEx.class, "backLeft");
        backRight = hardwareMap.get(DcMotorEx.class, "backRight");

        intake = hardwareMap.get(DcMotorEx.class, "intake");

        transfer = hardwareMap.get(DcMotorEx.class, "transfer");

        shooterLeft = hardwareMap.get(DcMotorEx.class, "shooterLeft");
        shooterRight = hardwareMap.get(DcMotorEx.class, "shooterRight");

        hood = hardwareMap.get(Servo.class, "hood");

        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        intake.setDirection(DcMotorSimple.Direction.REVERSE);
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        shooterRight.setDirection(DcMotorSimple.Direction.FORWARD);
        shooterLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        shooterLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        shooterRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        transfer.setDirection(DcMotorSimple.Direction.REVERSE);

        hood.setPosition(hoodPosition);

        waitForStart();

        if (isStopRequested()) { return; }

        while ( opModeIsActive() ) {

            double forwardPower = gamepad1.left_stick_y;
            double lateralPower = -gamepad1.left_stick_x * 1.1;
            double turnPower = -gamepad1.right_stick_x;

            double denominator = Math.max(Math.abs(forwardPower) + Math.abs(lateralPower) + Math.abs(turnPower), 1);
            double frontLeftPower = (forwardPower + turnPower + lateralPower) / denominator;
            double frontRightPower = (forwardPower - turnPower - lateralPower) / denominator;
            double backLeftPower = (forwardPower + turnPower - lateralPower) / denominator;
            double backRightPower = (forwardPower - turnPower + lateralPower) / denominator;

            frontLeft.setPower(frontLeftPower);
            backLeft.setPower(backLeftPower);
            frontRight.setPower(frontRightPower);
            backRight.setPower(backRightPower);

            if (gamepad1.x) {
                intake.setPower(0);
            } else if (gamepad1.a) {
                intake.setPower(0.8);
            }

            if (gamepad2.a) {
                shooterLeft.setPower(0.5);
                shooterRight.setPower(0.5);
            } else if (gamepad2.right_bumper) {
                shooterLeft.setPower(0.75);
                shooterRight.setPower(0.75);
            } else if (gamepad2.b) {
                shooterLeft.setPower(0);
                shooterRight.setPower(0);
            } else if (gamepad2.x) {
                transfer.setPower(1);
            } else if (gamepad2.y) {
                transfer.setPower(0);
            }


            looptime = getRuntime();
            cycletime = looptime-oldtime;
            oldtime = looptime;

            hood.setPosition(hoodPosition);

            telemetry.addData("flywheelPower", shooterLeft.getPower());
            telemetry.addData("flywheelPower", shooterRight.getPower());

            telemetry.addData("hood pos", hood.getPosition());
            telemetry.update();
        }

    }


}
