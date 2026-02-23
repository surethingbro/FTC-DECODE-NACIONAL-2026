package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class tutorialezezezzeze extends LinearOpMode {

    DcMotorEx motor;

    @Override
    public void runOpMode() throws InterruptedException {

        motor = hardwareMap.get(DcMotorEx.class, "motor1");

        motor.setDirection(DcMotorSimple.Direction.REVERSE);
        motor.setPower(0);

        waitForStart();

        if (isStopRequested()) {
            return;
        }

        while (opModeIsActive()) {
            motor.setPower(1);
        }


    }
}


