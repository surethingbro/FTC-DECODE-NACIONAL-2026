package org.firstinspires.ftc.teamcode.Subsystems;

import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

public class Transfer extends SubsystemBase {
    private final MotorEx transfer;
    private Timer timer;

    public Transfer(final HardwareMap hardwareMap, final String name, Timer timer) {
        transfer = new MotorEx(hardwareMap, name);

        transfer.setRunMode(Motor.RunMode.RawPower);
        transfer.setZeroPowerBehavior(Motor.ZeroPowerBehavior.FLOAT);
        transfer.setInverted(true);

    }

    public void transferArtifact() {
        timer.resetTimer();

        while (timer.getElapsedTime() != 1000) {
            transfer.set(1);
        }
        transfer.stopMotor();
    }

    public void stop() {
        transfer.stopMotor();
    }

}