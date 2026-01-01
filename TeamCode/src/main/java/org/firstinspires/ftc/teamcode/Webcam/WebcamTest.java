package org.firstinspires.ftc.teamcode.Webcam;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.Webcam.Subsystem.WebcamSubsystem;
import org.firstinspires.ftc.teamcode.Webcam.Subsystem.WebcamSubsystem.Pattern;

import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.List;

/** @noinspection FieldCanBeLocal*/
@TeleOp
public class WebcamTest extends CommandOpMode {

    private GamepadEx gamepadOne;
    private WebcamSubsystem webcamSubsystem;

    @Override
    public void initialize() {
        gamepadOne = new GamepadEx(gamepad1);
        webcamSubsystem = new WebcamSubsystem(hardwareMap);



        List<AprilTagDetection> detectedTags = webcamSubsystem.getDetectedTags();
        AprilTagDetection blueDetection = webcamSubsystem.getBlueBasketTag();
        AprilTagDetection redDetection = webcamSubsystem.getRedBasketTag();
        Pattern pattern = webcamSubsystem.getObeliskPattern();

        telemetry.addData("tags", detectedTags);
        telemetry.addData("bluebasket", blueDetection);
        telemetry.addData("redbasket", redDetection);
        telemetry.addData("detected pattern", pattern);


    }
}
