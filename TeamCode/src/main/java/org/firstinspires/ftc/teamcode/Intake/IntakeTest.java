package org.firstinspires.ftc.teamcode.Intake;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Intake.Commands.RunIntakeCommand;
import org.firstinspires.ftc.teamcode.Intake.Commands.RunIntakeReversedCommand;
import org.firstinspires.ftc.teamcode.Intake.Commands.StopIntakeCommand;
import org.firstinspires.ftc.teamcode.Intake.Subsystems.IntakeSubsystem;

@TeleOp
public class IntakeTest extends CommandOpMode {

    private IntakeSubsystem subsystem;
    private GamepadEx gamepadOne;

    @Override
    public void initialize() {
        gamepadOne = new GamepadEx(gamepad1);

        subsystem = new IntakeSubsystem(hardwareMap.get(DcMotor.class, "intake"), telemetry);

        gamepadOne.getGamepadButton(GamepadKeys.Button.A)
                .whenPressed(new RunIntakeCommand(subsystem));

        gamepadOne.getGamepadButton(GamepadKeys.Button.B)
                .whenPressed(new StopIntakeCommand(subsystem));

        gamepadOne.getGamepadButton(GamepadKeys.Button.X)
                .whenPressed(new RunIntakeReversedCommand(subsystem));

    }
}
