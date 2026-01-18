package org.firstinspires.ftc.teamcode.Robot;


import android.util.Size;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.pedropathing.geometry.Pose;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import java.util.List;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Robot.Intake.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Robot.MecanumDrive.Subsystem.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.Robot.Webcam.Subsystem.WebcamSubsystem;
import org.firstinspires.ftc.teamcode.Robot.utilities.BasicFilter;
import org.firstinspires.ftc.teamcode.Robot.utilities.RunningAverageFilter;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagGameDatabase;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

public class Robot {
    private static final Robot inst = new Robot();
    public static final double IDEAL_VOLTAGE = 13.0;

    public enum Mode {
        AUTONOMOUS,
        TELEOP
    }

    public enum Alliance {
        RED, BLUE
    }

    //alliance settings by default
    public static Alliance alliance = Alliance.BLUE;
    public static Mode mode = Mode.AUTONOMOUS;

    //hardware
    public MotorEx intake_motor;

    //sensors
    public VoltageSensor batterySensor;
    private final BasicFilter batteryFilter = new RunningAverageFilter(10);
    public AprilTagProcessor processor;
    public VisionPortal visionPortal;


    //Hubs
    public List<LynxModule> lynxModules;
    public HardwareMap hardwareMap;

    //Subsystems
    public Hubs hubs;
    public MecanumDriveSubsystem mecanumDrive;
    public Intake intake;
    public WebcamSubsystem webcamSubsystem;

    //for writing telemetry logs
    public MultipleTelemetry multipleTelemetry;

    //for feedforward
    private double lastMeasuredVoltage = IDEAL_VOLTAGE;

    public static Robot get() {
        return inst;
    }

    public Robot() {}

    public void reset() {
        initProcessor();
        initVisionPortal();
    }

    public Robot init(Mode mode, Pose pose, HardwareMap map, Telemetry telemetry) {
        CommandScheduler.getInstance().reset();
        batteryFilter.reset();

        Robot.mode = mode;
        hardwareMap = map;

        //lynx for cache stuff
        lynxModules = hardwareMap.getAll(LynxModule.class);

        //sensor
        batterySensor = hardwareMap.getAll(VoltageSensor.class)
                .iterator()
                .next();

        //TODO: initialize the motor
        intake_motor = new MotorEx(hardwareMap, "intake").setCachingTolerance(0.001);
        intake_motor.setInverted(true); //change if reversed


        multipleTelemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        hubs = new Hubs();
        mecanumDrive = new MecanumDriveSubsystem(map, pose);
        intake = new Intake();
        webcamSubsystem = new WebcamSubsystem();

        reset();
        return inst;
    }

    public double getVoltageFeedForwardConstant() {
        double safeVoltage = Math.max(lastMeasuredVoltage, 9.0);
        batteryFilter.update(IDEAL_VOLTAGE / safeVoltage);
        return batteryFilter.getFilteredOutput();
    }

    public void endLoop() {
        multipleTelemetry.addData("ALLIANCE:", alliance.toString());
        lastMeasuredVoltage = batterySensor.getVoltage();
        multipleTelemetry.addData("BATTERY STATE", lastMeasuredVoltage);

        CommandScheduler.getInstance().run();
        multipleTelemetry.update();
    }

    public void initProcessor() {
        processor = new AprilTagProcessor.Builder()
                .setDrawAxes(true)
                .setDrawCubeProjection(true)
                .setDrawTagOutline(true)
                .setDrawTagID(true)
                .setTagLibrary(AprilTagGameDatabase.getDecodeTagLibrary())
                .build();
    }

    public void initVisionPortal() {
        visionPortal = new VisionPortal.Builder()
                .addProcessor(processor)
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .setCameraResolution(new Size(640, 480))
                .setStreamFormat(VisionPortal.StreamFormat.MJPEG)
                .build();
    }

}
