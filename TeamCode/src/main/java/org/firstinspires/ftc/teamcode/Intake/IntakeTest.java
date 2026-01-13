package org.firstinspires.ftc.teamcode.Intake;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.Intake.Commands.RunIntakeCommand;
import org.firstinspires.ftc.teamcode.Intake.Commands.RunIntakeReversedCommand;
import org.firstinspires.ftc.teamcode.Intake.Commands.StopIntakeCommand;
import org.firstinspires.ftc.teamcode.Intake.Subsystems.IntakeSubsystem;

@TeleOp
public class IntakeTest extends CommandOpMode {

    private GamepadEx gamepadOne;
    private IntakeSubsystem intake_subsystem;

    @Override
    public void initialize() {
        gamepadOne = new GamepadEx(gamepad1);

        intake_subsystem = new IntakeSubsystem(hardwareMap, "intake");
    }

    @Override
    public void run() {

        gamepadOne.getGamepadButton(GamepadKeys.Button.A)
                .whileHeld(new RunIntakeCommand(intake_subsystem))
                .whenReleased(new StopIntakeCommand(intake_subsystem));

        gamepadOne.getGamepadButton(GamepadKeys.Button.X)
                .whileHeld(new RunIntakeReversedCommand(intake_subsystem))
                .whenReleased(new StopIntakeCommand(intake_subsystem));
    }
}
