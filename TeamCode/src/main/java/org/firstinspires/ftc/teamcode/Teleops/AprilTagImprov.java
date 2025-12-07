package org.firstinspires.ftc.teamcode.Teleops;
import android.annotation.SuppressLint;
import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.ExposureControl;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagGameDatabase;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

@TeleOp
public class AprilTagImprov extends LinearOpMode {


    @SuppressLint("DefaultLocale")
    @Override
    public void runOpMode() throws InterruptedException {

        AprilTagProcessor aprilTagProcessor = new AprilTagProcessor.Builder()
                .setDrawAxes(true)
                .setDrawCubeProjection(true)
                .setDrawTagOutline(true)
                .setDrawTagID(true)
                .setTagLibrary(AprilTagGameDatabase.getDecodeTagLibrary())
                .build();

        VisionPortal visionPortal = new VisionPortal.Builder()
                .addProcessor(aprilTagProcessor)
                .setCamera(hardwareMap.get(WebcamName.class,"Webcam 1"))
                .setCameraResolution(new Size(640,480))
                .setStreamFormat(VisionPortal.StreamFormat.MJPEG)
                .build();

        while (visionPortal.getCameraState() != VisionPortal.CameraState.STREAMING) {}

        ExposureControl exposure = visionPortal.getCameraControl(ExposureControl.class);



        waitForStart();

        if (isStopRequested()) { return; }

        while (opModeIsActive()) {


            List<AprilTagDetection> currentDetections = aprilTagProcessor.getDetections();
            telemetry.addData("AprilTags Detected rn ", currentDetections.size());

            if (gamepad1.a) {
                telemetry.addLine("visionPortal paused!");
                visionPortal.stopStreaming();
            } else if (gamepad1.b) {
                telemetry.addData("FPS", visionPortal.getFps());
            } else if (gamepad1.x) {
                telemetry.addLine("visionPortal streaming");
                visionPortal.resumeStreaming();
            }

            telemetry.addData("exposure: ", exposure.isExposureSupported());

            for (AprilTagDetection detectedTag : currentDetections) {
                if (detectedTag.metadata != null) {
                    telemetry.addLine(String.format("\n==== (ID %d) %s", detectedTag.id, detectedTag.metadata.name));
                    telemetry.addLine(String.format("RBE %6.2f %6.2f %6.2f  (inch, deg, deg)", detectedTag.ftcPose.range, detectedTag.ftcPose.bearing, detectedTag.ftcPose.elevation));
                }

            }

            telemetry.update();

        }
    }
}
