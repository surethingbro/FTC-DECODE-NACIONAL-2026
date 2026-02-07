package org.firstinspires.ftc.teamcode.Debugging;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class HoodTuner extends OpMode {

    Servo hood;

    @Override
    public void init() {
        hood = hardwareMap.get(Servo.class, "hood");

        hood.setPosition(0);
    }

    @Override
    public void loop() {

        if (gamepad1.x) {
            hood.setPosition(0.2);
        } else if (gamepad1.y) {
            hood.setPosition(0.4);
        } else if (gamepad1.a) {
            hood.setPosition(0.6);
        } else if (gamepad1.b) {
            hood.setPosition(0.8);
        } else if (gamepad1.right_bumper) {
            hood.setPosition(1);
        } else if (gamepad1.left_bumper) {
            hood.setPosition(0);
        }


    }
}
