package org.firstinspires.ftc.teamcode.Teleops;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.ImuOrientationOnRobot;

@TeleOp
public class MecanumTest extends LinearOpMode {

    public DcMotor frontLeft;
    public DcMotor backLeft;
    public DcMotor frontRight;
    public DcMotor backRight;

    public DcMotor intake;

    //Test
    public Pose redBasket = new Pose(131,132);
    public Pose robotPos = new Pose(72,72);
    @Override
    public void runOpMode() throws InterruptedException {

        frontLeft = hardwareMap.get(DcMotorEx.class, "leftPar");
        backLeft = hardwareMap.get(DcMotorEx.class, "backLeftPerp");
        frontRight = hardwareMap.get(DcMotorEx.class, "right");
        backRight = hardwareMap.get(DcMotorEx.class, "backRight");

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.REVERSE);

        intake = hardwareMap.get(DcMotor.class, "intake");

        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        if (isStopRequested()) { return; }

        while ( opModeIsActive() ) {

            double forwardPower = -gamepad1.left_stick_y;
            double lateralPower = gamepad1.left_stick_x * 1.1;
            double turnPower = gamepad1.right_stick_x;

            double denominator = Math.max(Math.abs(forwardPower) + Math.abs(lateralPower) + Math.abs(turnPower), 1);
            double frontLeftPower = (forwardPower + turnPower + lateralPower) / denominator;
            double frontRightPower = (forwardPower - turnPower - lateralPower) / denominator;
            double backLeftPower = (forwardPower + turnPower - lateralPower) / denominator;
            double backRightPower = (forwardPower - turnPower + lateralPower) / denominator;

            frontLeft.setPower(frontLeftPower);
            backLeft.setPower(backLeftPower);
            frontRight.setPower(frontRightPower);
            backRight.setPower(backRightPower);

            if (gamepad1.a) {
                intake.setPower(0.7);
            } else if (gamepad1.b) {
                intake.setPower(0);
            } else if (gamepad1.x) {
                intake.setPower(-0.7);
            }

            telemetry.addData("Distance (IN)", robotPos.distanceFrom(redBasket));
            telemetry.update();

        }
    }
}
