package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

public class Transfer extends SubsystemBase {
    private final MotorEx transfer;

    public Transfer(final HardwareMap hardwareMap, final String name) {
        transfer = new MotorEx(hardwareMap, name);

        transfer.setRunMode(Motor.RunMode.RawPower);
        transfer.setZeroPowerBehavior(Motor.ZeroPowerBehavior.FLOAT);
        transfer.setInverted(true);
    }

    public void transferArtifact() {
        transfer.set(1);
    }

    public void stop() {
        transfer.stopMotor();
    }

}
