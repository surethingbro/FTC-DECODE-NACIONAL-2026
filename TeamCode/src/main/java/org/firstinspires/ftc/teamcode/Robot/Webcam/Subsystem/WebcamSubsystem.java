package org.firstinspires.ftc.teamcode.Robot.Webcam.Subsystem;

import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.ArrayList;
import java.util.List;

public class WebcamSubsystem extends SubsystemBase {
    private final Robot robot;
    private final AprilTagProcessor processor;

    public enum Pattern {
        PPG, GPP, PGP, UNKNOWN
    }

    public Pattern currentPattern = Pattern.UNKNOWN;

    private List<AprilTagDetection> currentDetections = new ArrayList<>();

    public WebcamSubsystem() {
        robot = Robot.get();
        processor = robot.processor;
    }


    @Override
    public void periodic() {

        updateDetections();

        for (AprilTagDetection detection : currentDetections ) {
            if (detection.metadata == null) continue;

            switch (detection.id) {
                case 21:
                    currentPattern = Pattern.GPP;
                    return;
                case 22:
                    currentPattern = Pattern.PGP;
                    return;
                case 23:
                    currentPattern = Pattern.PPG;
                    return;
            }

            robot.multipleTelemetry.addData("Pattern,", currentPattern);

        }


    }

    public void updateDetections() {
        currentDetections = processor.getDetections();
    }

    public Pattern getPattern() {

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


}




