package org.firstinspires.ftc.teamcode.Robot.Intake.Subsystems;

import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.Robot.Robot;

public class Intake extends SubsystemBase {

    public enum Mode {
        EATING,
        DISCARDING,
        OFF
    }

    public static Mode mode = Mode.OFF;
    public static double EATING_SPEED = 0.7;
    public static double DISCARDING_SPEED = -0.7;

    public Intake() {
    }

    public void setMode(Mode mode) {
        Intake.mode = mode;
    }

    @Override
    public void periodic() {
        Robot robot = Robot.get();

        switch (mode) {
            case EATING:
                robot.intake_motor.set(EATING_SPEED * robot.getVoltageFeedForwardConstant());
                break;

            case DISCARDING:
                robot.intake_motor.set(DISCARDING_SPEED * robot.getVoltageFeedForwardConstant());
                break;

            case OFF:
                robot.intake_motor.set(0.0);
                break;

            default:
                break;
        }
    }
}
