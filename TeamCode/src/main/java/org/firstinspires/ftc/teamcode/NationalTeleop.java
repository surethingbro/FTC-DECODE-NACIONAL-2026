package org.firstinspires.ftc.teamcode;

import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveCommand;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDrivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.Shooter;
import org.firstinspires.ftc.teamcode.Subsystems.Transfer;

@Deprecated
@TeleOp
@SuppressWarnings("FieldCanbeLocal")
public class NationalTeleop extends CommandOpMode {

    private MecanumDrivetrain mecanum;

    private Intake intake;
    private Transfer transfer;
    private Shooter shooter;

    private GamepadEx leo;
    private GamepadEx kakeru;

    private MecanumDriveCommand mecanumcmd;


    @Override
    public void initialize() {

        leo = new GamepadEx(gamepad1);
        kakeru = new GamepadEx(gamepad2);

        intake = new Intake(hardwareMap, "intake");
        transfer = new Transfer(hardwareMap, "transfer", new Timer());
        shooter = new Shooter(hardwareMap, "shooterLeft", "shooterRight", "hood");

        mecanum = new MecanumDrivetrain(hardwareMap,
                "frontLeft",
                "backLeft",
                "frontRight",
                "backRight"
        );

        mecanumcmd = new MecanumDriveCommand(mecanum,
                () -> -leo.getLeftX(),
                () -> -leo.getLeftY(),
                () -> -leo.getRightX()
        );

        leo.getGamepadButton(GamepadKeys.Button.A)
                .whileHeld(intake::runIntake)
                .whenReleased(intake::stop);

        kakeru.getGamepadButton(GamepadKeys.Button.A)
                .whenPressed(shooter::shootClose);

        kakeru.getGamepadButton(GamepadKeys.Button.B)
                .whenPressed(shooter::stopShoot);

        kakeru.getGamepadButton(GamepadKeys.Button.X)
                .whenPressed(transfer::transferArtifact);

        register(mecanum);
        mecanum.setDefaultCommand(mecanumcmd);
    }

}
