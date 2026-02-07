package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.Intake;

public class RunIntake extends CommandBase {
    private Intake intake;

    public RunIntake(Intake intake) {
        this.intake = intake;

        addRequirements(intake);
    }

    @Override
    public void initialize() {
        intake.runIntake();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
