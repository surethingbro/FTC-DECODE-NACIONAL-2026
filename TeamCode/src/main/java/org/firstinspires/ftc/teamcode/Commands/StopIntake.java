package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.Intake;

public class StopIntake extends CommandBase {
    private Intake intake;

    public StopIntake(Intake intake) {
        this.intake = intake;

        addRequirements(intake);
    }

    @Override
    public void initialize() {
        intake.stop();
    }

    @Override
    public boolean isFinished() {
        return true;
    }


}
