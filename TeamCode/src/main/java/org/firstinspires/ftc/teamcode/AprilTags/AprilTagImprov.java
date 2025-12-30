package org.firstinspires.ftc.teamcode.AprilTags;
import android.annotation.SuppressLint;
import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.ExposureControl;
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.GainControl;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagGameDatabase;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;




@TeleOp
public class AprilTagImprov extends LinearOpMode {


    /**
     * This enum stores the possible patterns in an FTC DECODE obelisk
     */
    public enum Pattern {
        PPG, GPP, PGP, UNKNOWN
    }

    /**
     * This method returns the Pattern detected by our webcam when looking at the obelisk
     * @param motifID the ID detected by the camera
     * @return The detected MOTIF pattern
     */
    public static Pattern getPattern(int motifID) {
        if (motifID == 21) {
            return Pattern.GPP;
        } else if (motifID == 22) {
            return Pattern.PGP;
        } else if (motifID == 23) {
            return Pattern.PPG;
        } else {
            return Pattern.UNKNOWN;
        }
    }

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

        /*
        while (visionPortal.getCameraState() != VisionPortal.CameraState.STREAMING) {}

        ExposureControl exposure = visionPortal.getCameraControl(ExposureControl.class);

        exposure.setMode(ExposureControl.Mode.Manual);
        exposure.setExposure(15, TimeUnit.MILLISECONDS);

        GainControl gain = visionPortal.getCameraControl(GainControl.class);
        gain.setGain(255);

         */


        waitForStart();

        if (isStopRequested()) { return; }

        while (opModeIsActive()) {


            List<AprilTagDetection> currentDetections = aprilTagProcessor.getDetections();

            if (gamepad1.a) {
                telemetry.addLine("visionPortal paused!");
                visionPortal.stopStreaming();
            } else if (gamepad1.b) {
                telemetry.addData("FPS", visionPortal.getFps());
            } else if (gamepad1.x) {
                telemetry.addLine("visionPortal streaming");
                visionPortal.resumeStreaming();
            }


            for (AprilTagDetection detectedTag : currentDetections) {
                if (detectedTag.metadata != null) {
                    telemetry.addLine(String.format("\n==== (ID %d) %s", detectedTag.id, detectedTag.metadata.name));
                    telemetry.addLine(String.format("RBE %6.2f %6.2f %6.2f  (inch, deg, deg)", detectedTag.ftcPose.range, detectedTag.ftcPose.bearing, detectedTag.ftcPose.elevation));

                    Pattern pattern = getPattern(detectedTag.id);
                    telemetry.addData("Detected Pattern", pattern);
                }
            }


            telemetry.update();

        }
    }
}
