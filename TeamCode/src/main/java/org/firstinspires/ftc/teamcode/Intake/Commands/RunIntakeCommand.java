package org.firstinspires.ftc.teamcode.Intake.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.teamcode.Intake.Subsystems.IntakeSubsystem;

public class RunIntakeCommand extends CommandBase {

     private IntakeSubsystem subsystem;

     public RunIntakeCommand(IntakeSubsystem subsystem) {
         this.subsystem = subsystem;

         addRequirements(subsystem);
     }


    @Override
    public void initialize() {
        subsystem.runIntake();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
