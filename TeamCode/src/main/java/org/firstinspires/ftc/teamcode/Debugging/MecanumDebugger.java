package org.firstinspires.ftc.teamcode.Debugging;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp
public class MecanumDebugger extends OpMode {

    DcMotorEx frontLeft;
    DcMotorEx frontRight;
    DcMotorEx backLeft;
    DcMotorEx backRight;


    @Override
    public void init() {

        frontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
        backLeft = hardwareMap.get(DcMotorEx.class, "backLeft");
        backRight = hardwareMap.get(DcMotorEx.class, "backRight");

    }

    @Override
    public void loop() {

        if (gamepad1.a) {
            frontLeft.setPower(1);
            telemetry.addData("frontleft", frontLeft.getPower());
        } else if (gamepad1.b) {
            frontRight.setPower(1);
            telemetry.addData("frontRight", frontRight.getPower());
        } else if (gamepad1.x) {
            backLeft.setPower(1);
            telemetry.addData("backLeft", backLeft.getPower());
        } else if (gamepad1.y) {
            backRight.setPower(1);
            telemetry.addData("backRight", backRight.getPower());
        }
    }
}
