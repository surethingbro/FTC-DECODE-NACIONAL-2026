package org.firstinspires.ftc.teamcode.Teleops;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.Robot.Intake.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Robot.MecanumDrive.Commands.MecanumDriveCommand;
import org.firstinspires.ftc.teamcode.Robot.MecanumDrive.Subsystem.MecanumDriveSubsystem;


/** @noinspection FieldCanBeLocal*/
@TeleOp
public class TeleopTest extends CommandOpMode {

    private GamepadEx gamepadOne;
    private IntakeSubsystem intake_subsystem;
    private MecanumDriveSubsystem mecanum;
    private MecanumDriveCommand mecanumCommand;

    @Override
    public void initialize() {

        mecanum = new MecanumDriveSubsystem(
                hardwareMap,
                "leftPar",
                "backLeftPerp",
                "right",
                "backRight"
        );

        intake_subsystem = new IntakeSubsystem(hardwareMap, "intake");

        mecanumCommand = new MecanumDriveCommand(
                mecanum,
                () -> -gamepadOne.getLeftX(),
                () -> -gamepadOne.getLeftY(),
                () -> -gamepadOne.getRightX()
        );

        register(mecanum, intake_subsystem);
        mecanum.setDefaultCommand(mecanumCommand);
    }

}
