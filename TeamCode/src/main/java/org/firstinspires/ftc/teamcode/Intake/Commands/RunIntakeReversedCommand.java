package org.firstinspires.ftc.teamcode.Intake.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.teamcode.Intake.Subsystems.IntakeSubsystem;

public class RunIntakeReversedCommand extends CommandBase {

    private IntakeSubsystem subsystem;

    public RunIntakeReversedCommand(IntakeSubsystem subsystem) {
        this.subsystem = subsystem;

        addRequirements(subsystem);
    }


    @Override
    public void initialize() {
        subsystem.runIntakeReverse();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
