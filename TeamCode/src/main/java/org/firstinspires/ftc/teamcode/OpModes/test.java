package org.firstinspires.ftc.teamcode.OpModes;

import static org.firstinspires.ftc.teamcode.cmds.Commandlet.intakeSet;
import static org.firstinspires.ftc.teamcode.cmds.Commandlet.run;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.Command;

import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.Robot.Intake.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Robot.Intake.Subsystems.Intake.Mode;

import org.firstinspires.ftc.teamcode.Robot.MecanumDrive.Subsystem.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.Robot.Robot;

@TeleOp
@Configurable
public class test extends OpMode {

    private Robot robot;
    private GamepadEx gamepad;

    @Override
    public void init() {
        robot = Robot.get().init(Robot.Mode.TELEOP, MecanumDriveSubsystem.lastPose, hardwareMap, telemetry);
        robot.mecanumDrive.follower.startTeleOpDrive();

        gamepad = new GamepadEx(gamepad1);

        /*CommandScheduler.getInstance().schedule(new SequentialCommandGroup(
                run(() -> robot)
                )

        ); Set idle for other hardware like turret
         */

        bind(GamepadKeys.Button.A,
                intakeSet(Mode.EATING),
                intakeSet(Mode.OFF)
        );

        bind(GamepadKeys.Button.B,
                run(() -> Intake.EATING_SPEED = 0.7).alongWith(intakeSet(Intake.Mode.EATING)),
                run(() -> Intake.DISCARDING_SPEED = -0.85).alongWith(intakeSet(Intake.Mode.OFF))
        );

        bind(GamepadKeys.Button.X,
                intakeSet(Intake.Mode.DISCARDING),intakeSet(Intake.Mode.OFF)
        );
    }

    @Override
    public void loop() {
        robot.endLoop();

        robot.mecanumDrive.setTeleOpDrive(
                -gamepad.getLeftY(),
                -gamepad.getRightY(),
                 gamepad.getRightX()
        );
    }

    public void bind(GamepadKeys.Button button, Command pressedCmd, Command releasedCmd) {
        gamepad.getGamepadButton(button).whenPressed(pressedCmd).whenInactive(releasedCmd);
    }
}
