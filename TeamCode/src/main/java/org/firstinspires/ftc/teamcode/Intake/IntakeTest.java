package org.firstinspires.ftc.teamcode.Intake;

import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import org.firstinspires.ftc.teamcode.Intake.Commands.RunIntakeCommand;
import org.firstinspires.ftc.teamcode.Intake.Commands.RunIntakeReversedCommand;
import org.firstinspires.ftc.teamcode.Intake.Commands.StopIntakeCommand;
import org.firstinspires.ftc.teamcode.Intake.Subsystems.IntakeSubsystem;


@TeleOp
public class IntakeTest extends CommandOpMode {

    ;
    @Override
    public void initialize() {
        GamepadEx gamepadOne = new GamepadEx(gamepad1);

        Motor intake = new Motor(hardwareMap, "intake");

        IntakeSubsystem subsystem = new IntakeSubsystem(intake, telemetry);

        gamepadOne.getGamepadButton(GamepadKeys.Button.A)
                .whileHeld(new RunIntakeCommand(subsystem))
                .whenReleased(new StopIntakeCommand(subsystem));

        gamepadOne.getGamepadButton(GamepadKeys.Button.X)
                .whileHeld(new RunIntakeReversedCommand(subsystem))
                .whenReleased(new StopIntakeCommand(subsystem));




    }
}
