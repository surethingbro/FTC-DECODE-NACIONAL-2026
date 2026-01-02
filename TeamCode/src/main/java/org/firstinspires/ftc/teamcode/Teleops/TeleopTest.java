package org.firstinspires.ftc.teamcode.Teleops;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.Intake.Commands.RunIntakeCommand;
import org.firstinspires.ftc.teamcode.Intake.Commands.RunIntakeReversedCommand;
import org.firstinspires.ftc.teamcode.Intake.Commands.StopIntakeCommand;
import org.firstinspires.ftc.teamcode.Intake.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.MecanumDrive.Commands.MecanumDriveCommand;
import org.firstinspires.ftc.teamcode.MecanumDrive.Subsystem.MecanumDriveSubsystem;

/** @noinspection FieldCanBeLocal*/
@TeleOp
public class TeleopTest extends CommandOpMode {

    private GamepadEx gamepadOne;
    private IntakeSubsystem intake_subsystem;
    private MecanumDriveSubsystem mecanum;
    private MecanumDriveCommand mecanumCommand;

    @Override
    public void initialize() {

        gamepadOne = new GamepadEx(gamepad1);

        intake_subsystem = new IntakeSubsystem(hardwareMap, "intake");

        mecanum = new MecanumDriveSubsystem(
                hardwareMap,
                "leftPar",
                "backLeftPerp",
                "right",
                "backRight"
        );

        mecanumCommand = new MecanumDriveCommand(
                mecanum,
                gamepadOne::getLeftY,
                gamepadOne::getLeftX,
                gamepadOne::getRightX
        );


        gamepadOne.getGamepadButton(GamepadKeys.Button.A)
                .whileHeld(new RunIntakeCommand(intake_subsystem))
                .whenReleased(new StopIntakeCommand(intake_subsystem));

        gamepadOne.getGamepadButton(GamepadKeys.Button.X)
                .whileHeld(new RunIntakeReversedCommand(intake_subsystem))
                .whenReleased(new StopIntakeCommand(intake_subsystem));

        register(mecanum);
        mecanum.setDefaultCommand(mecanumCommand);
    }


}
