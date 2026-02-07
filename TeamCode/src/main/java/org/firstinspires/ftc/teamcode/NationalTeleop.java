package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDriveCommand;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDrivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.Shooter;
import org.firstinspires.ftc.teamcode.Subsystems.Transfer;

@TeleOp
@SuppressWarnings("FieldCanbeLocal")
public class NationalTeleop extends CommandOpMode {

    private MecanumDrivetrain mecanum;

    private Intake intake;
    private Transfer transfer;
    private Shooter shooter;

    private GamepadEx driver1;
    private GamepadEx driver2;

    private MecanumDriveCommand mecanumcmd;

    @Override
    public void initialize() {

        driver1 = new GamepadEx(gamepad1);
        driver2 = new GamepadEx(gamepad2);

        intake = new Intake(hardwareMap, "intake");
        transfer = new Transfer(hardwareMap, "transfer");
        shooter = new Shooter(hardwareMap, "shooterLeft", "shooterRight", "hood");

        mecanum = new MecanumDrivetrain(hardwareMap,
                "frontLeft",
                "backLeft",
                "frontRight",
                "backRight"
        );

        mecanumcmd = new MecanumDriveCommand(mecanum,
                () -> driver1.getLeftX(),
                () -> driver1.getLeftY(),
                () -> driver1.getRightX()
        );

        CommandScheduler.getInstance().setDefaultCommand(mecanum, mecanumcmd);

        driver1.getGamepadButton(GamepadKeys.Button.A)
                .whileHeld(intake::runIntake)
                .whenReleased(intake::stop);


        register(intake, transfer, shooter, mecanum);
        schedule(mecanumcmd);
    }

    @Override
    public void run() {
        telemetry.addLine("dsfgrth");
        telemetry.update();
    }

}
