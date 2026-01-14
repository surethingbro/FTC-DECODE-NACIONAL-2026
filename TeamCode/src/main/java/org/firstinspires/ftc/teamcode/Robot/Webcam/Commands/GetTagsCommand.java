package org.firstinspires.ftc.teamcode.Robot.Webcam.Commands;
import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Robot.Webcam.Subsystem.WebcamSubsystem;

public class GetTagsCommand extends CommandBase {

    private final WebcamSubsystem subsystem;

    public GetTagsCommand(WebcamSubsystem subsystem) {
        this.subsystem = subsystem;

        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        subsystem.getDetectedTags();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
