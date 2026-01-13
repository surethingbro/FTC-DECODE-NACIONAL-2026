package org.firstinspires.ftc.teamcode.Webcam;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import org.firstinspires.ftc.teamcode.Webcam.Subsystem.WebcamSubsystem;
import org.firstinspires.ftc.teamcode.Webcam.Subsystem.WebcamSubsystem.Pattern;

import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.List;

/** @noinspection FieldCanBeLocal*/
@TeleOp
public class WebcamTest extends CommandOpMode {

    private WebcamSubsystem webcamSubsystem;

    @Override
    public void initialize() {

        webcamSubsystem = new WebcamSubsystem(hardwareMap);
    }

    @Override
    public void run() {
        
        List<AprilTagDetection> detectedTags = webcamSubsystem.getDetectedTags();
        AprilTagDetection blueDetection = webcamSubsystem.getBlueBasketTag();
        AprilTagDetection redDetection = webcamSubsystem.getRedBasketTag();
        Pattern pattern = webcamSubsystem.getObeliskPattern();

        if (!detectedTags.isEmpty()) {
            telemetry.addData("tags", detectedTags.size());
            telemetry.addData("bluebasket", blueDetection);
            telemetry.addData("redbasket", redDetection);
            telemetry.addData("detected pattern", pattern);
            telemetry.update();
        }
    }
}
