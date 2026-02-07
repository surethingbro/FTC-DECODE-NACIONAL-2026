package org.firstinspires.ftc.teamcode;

import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;
import org.firstinspires.ftc.teamcode.Commands.StopTransfer;
import org.firstinspires.ftc.teamcode.Commands.TransferArtifact;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Commands.MecanumDriveCommand;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDrivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.Shooter;
import org.firstinspires.ftc.teamcode.Subsystems.Transfer;

@SuppressWarnings("FieldCanBeLocal")
@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends CommandOpMode {


    private MecanumDrivetrain mecanum;
    private MecanumDriveCommand mecanumCommand;
    private Intake intake;
    private Transfer transfer;
    private Shooter shooter;

    @Override
    public void initialize() {

        GamepadEx leo = new GamepadEx(gamepad1);
        GamepadEx kakeru = new GamepadEx(gamepad2);

        mecanum = new MecanumDrivetrain(
                hardwareMap,
                "frontLeft",
                "frontRight",
                "backLeft",
                "backRight"
        );

        mecanumCommand = new MecanumDriveCommand(
            mecanum,
                () -> leo.getLeftX(),
                () -> leo.getLeftY(),
                () -> leo.getRightX()
        );

        intake = new Intake(hardwareMap, "intake");
        transfer = new Transfer(hardwareMap, "transfer");

        shooter = new Shooter(hardwareMap,
                "shooterLeft",
                "shooterRight",
                "hood"
        );


        leo.getGamepadButton(GamepadKeys.Button.A)
                        .whileHeld(new InstantCommand(intake::runIntake, intake))
                        .whenReleased(new InstantCommand(intake::stop, intake));

        leo.getGamepadButton(GamepadKeys.Button.B)
                .whenPressed(new TransferArtifact(transfer));

        leo.getGamepadButton(GamepadKeys.Button.X)
                .whileHeld(new StopTransfer(transfer));

        register(mecanum, intake, transfer);
        mecanum.setDefaultCommand(mecanumCommand);
    }

    @Override
    public void run() {
        telemetry.addLine("ts working");
    }
}
