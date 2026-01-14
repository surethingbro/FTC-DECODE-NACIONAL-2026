package org.firstinspires.ftc.teamcode.Robot.Webcam.Subsystem;

import android.util.Size;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagGameDatabase;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.ArrayList;
import java.util.List;

public class WebcamSubsystem extends SubsystemBase {

    public enum Pattern {
        PPG, GPP, PGP, UNKNOWN
    }

    private final AprilTagProcessor processor;
    private final VisionPortal visionPortal;

    private List<AprilTagDetection> currentDetections = new ArrayList<>();

    public WebcamSubsystem(final HardwareMap hardwareMap) {

        processor = new AprilTagProcessor.Builder()
                .setDrawAxes(true)
                .setDrawCubeProjection(true)
                .setDrawTagOutline(true)
                .setDrawTagID(true)
                .setTagLibrary(AprilTagGameDatabase.getDecodeTagLibrary())
                .build();

        visionPortal = new VisionPortal.Builder()
                .addProcessor(processor)
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .setCameraResolution(new Size(640, 480))
                .setStreamFormat(VisionPortal.StreamFormat.MJPEG)
                .build();

    }

    public List<AprilTagDetection> getDetectedTags() {
        currentDetections = processor.getDetections();
        return currentDetections;
    }

    public void updateDetections() {
        currentDetections = processor.getDetections();
    }

    public AprilTagDetection getBlueBasketTag() {

        updateDetections();

        for (AprilTagDetection detection : currentDetections) {
            if (detection.metadata != null && detection.id == 20) {
                return detection;
            }
        }
        return null;
    }

    public AprilTagDetection getRedBasketTag() {

        updateDetections();

        for (AprilTagDetection detection : currentDetections) {
            if (detection.metadata != null && detection.id == 24) {
                return detection;
            }
        }
        return null;
    }

    public Pattern getObeliskPattern() {

        updateDetections();

        for (AprilTagDetection detection : currentDetections) {
            if (detection.metadata != null && detection.id == 21) {
                return Pattern.GPP;
            } else if (detection.metadata != null && detection.id == 22) {
                return Pattern.PGP;
            } else if (detection.metadata != null && detection.id == 23) {
                return Pattern.PPG;
            }
        }
        return Pattern.UNKNOWN;
    }


    public void stop() {
        if (visionPortal != null) {
            visionPortal.close();
        }
    }

}




