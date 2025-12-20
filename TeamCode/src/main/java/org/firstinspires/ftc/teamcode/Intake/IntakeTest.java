package org.firstinspires.ftc.teamcode.Intake;

import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Intake.Commands.RunIntakeCommand;
import org.firstinspires.ftc.teamcode.Intake.Commands.RunIntakeReversedCommand;
import org.firstinspires.ftc.teamcode.Intake.Commands.StopIntakeCommand;
import org.firstinspires.ftc.teamcode.Intake.Subsystems.IntakeSubsystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@TeleOp
public class IntakeTest extends CommandOpMode {

    private static final Logger log = LoggerFactory.getLogger(IntakeTest.class);
    private IntakeSubsystem subsystem;
    private GamepadEx gamepadOne;

    @Override
    public void initialize() {
        gamepadOne = new GamepadEx(gamepad1);

        subsystem = new IntakeSubsystem(hardwareMap.get(DcMotor.class, "intake"), telemetry);

        gamepadOne.getGamepadButton(GamepadKeys.Button.A)
                .whileHeld(new RunIntakeCommand(subsystem))
                .whenReleased(new StopIntakeCommand(subsystem));

        gamepadOne.getGamepadButton(GamepadKeys.Button.X)
                .whileHeld(new RunIntakeReversedCommand(subsystem))
                .whenReleased(new StopIntakeCommand(subsystem));


    }
}
