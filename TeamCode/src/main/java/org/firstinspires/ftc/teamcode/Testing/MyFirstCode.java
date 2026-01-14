package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
public class MyFirstCode extends LinearOpMode {

    //Motores

    DcMotor motor;
    Servo cervo;

    @Override
    public void runOpMode() throws InterruptedException {

        motor = hardwareMap.get(DcMotor.class,"motor");
        cervo = hardwareMap.get(Servo.class, "inki");

        motor.setDirection(DcMotorSimple.Direction.REVERSE);


        waitForStart();

        while (opModeIsActive()) {

            double power = gamepad1.left_stick_y;

            cervo.setPosition(1);
            motor.setPower(power);

        }



    }
}

