package org.firstinspires.ftc.teamcode.MecanumDrive.Subsystem;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

public class MecanumDriveSubsystem extends SubsystemBase {

    private final MecanumDrive mecanumDrive;

    public MecanumDriveSubsystem(MotorEx frontLeft,MotorEx backLeft, MotorEx frontRight, MotorEx backRight) {

        frontLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        mecanumDrive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);
    }

    public MecanumDriveSubsystem(HardwareMap hardwareMap, final String frontLeftName, String backLeftName, String frontRightName, String backRightName) {
        this(new MotorEx(hardwareMap, frontLeftName),
             new MotorEx(hardwareMap, backLeftName),
             new MotorEx(hardwareMap, frontRightName),
             new MotorEx(hardwareMap, backRightName)
        );



    }

    public void drive(double forward, double lateral, double turn) {
        mecanumDrive.driveRobotCentric(lateral,forward,turn);
    }
}
