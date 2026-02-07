package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

public class Turret extends SubsystemBase {

    private final MotorEx turret_motor;

    public Turret(final HardwareMap hardwareMap, String name) {
        turret_motor = new MotorEx(hardwareMap, name, Motor.GoBILDA.RPM_312);
        turret_motor.setRunMode(Motor.RunMode.RawPower);
        turret_motor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
    }

}
