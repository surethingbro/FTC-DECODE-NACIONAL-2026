package org.firstinspires.ftc.teamcode.Panucho;



import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.seattlesolvers.solverslib.command.Robot;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.Intake.Subsystems.IntakeSubsystem;



public class Panucho extends Robot {


    public LinearOpMode opMode;
    public GamepadEx leo;
    public GamepadEx kakeru;

    public IntakeSubsystem subsystem;
    public MecanumDrive drive;

    public Panucho(LinearOpMode opMode) {
        this.opMode = opMode;
        leo = new GamepadEx(opMode.gamepad1);
        kakeru = new GamepadEx(opMode.gamepad2);

    }


    
}
