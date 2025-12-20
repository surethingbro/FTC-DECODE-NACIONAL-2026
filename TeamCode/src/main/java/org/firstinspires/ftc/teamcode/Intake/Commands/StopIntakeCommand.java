package org.firstinspires.ftc.teamcode.Intake.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.teamcode.Intake.Subsystems.IntakeSubsystem;

public class StopIntakeCommand extends CommandBase {

    private IntakeSubsystem subsystem;

    public StopIntakeCommand(IntakeSubsystem subsystem) {
        this.subsystem = subsystem;

        addRequirements(subsystem);
    }


    @Override
    public void initialize() {
        subsystem.stopIntake();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}

