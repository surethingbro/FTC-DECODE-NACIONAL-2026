package org.firstinspires.ftc.teamcode;

import com.pedropathing.geometry.Pose;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.configuration.annotations.DeviceProperties;
import com.seattlesolvers.solverslib.geometry.Pose2d;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

import java.util.Locale;


public class pinpoint extends LinearOpMode {

    GoBildaPinpointDriver driver;

    @Override
    public void runOpMode() throws InterruptedException {

        driver = hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");

        driver.setOffsets(0,0 , DistanceUnit.MM);

        //distance = Math.sqrt((x1-x2)(x1-x2) + (y1-y2)(y1-y2));

        driver.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        //driver.setEncoderDirections();

        driver.resetPosAndIMU();

        waitForStart();

        while (opModeIsActive()) {
            driver.update();

            Pose2D pos = driver.getPosition();

            String data = String.format(Locale.US, "{X: %.3f, Y: %.3f, H: %.3f}", pos.getX(DistanceUnit.MM), pos.getY(DistanceUnit.MM), pos.getHeading(AngleUnit.DEGREES));

            telemetry.addData("Position", data);
        }
    }
}
