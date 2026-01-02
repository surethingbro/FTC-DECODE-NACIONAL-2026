package org.firstinspires.ftc.teamcode.Shooter;

import com.pedropathing.math.MathFunctions;

import org.opencv.core.Mat;

public class ShooterConstants {

    public static double DOOR_OPEN = 0;
    public static double DOOR_CLOSED = 0;

    private static double flywheelOffset = 0;
    private static double hoodOffset = 0;

    public static double setflywheelSpeed(double goalDistance) {
        return 0;
    }

    public static double sethoodAngle(double goalDistance) {
        return 0;
    }

    public static double launchTime(double goalDistance) {
        return 0;
    }

    public static void setFlywheelOffset(double offset) {
        flywheelOffset += offset;
    }

    public static void setHoodOffset(double offset) {
        hoodOffset += offset;
    }

    public static double getHoodOffset() {
        return hoodOffset;
    }

    public static double getFlywheelOffset() {
        return flywheelOffset;
    }
}
