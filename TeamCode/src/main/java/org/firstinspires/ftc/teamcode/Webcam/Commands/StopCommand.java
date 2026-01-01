package org.firstinspires.ftc.teamcode.Webcam.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Webcam.Subsystem.WebcamSubsystem;

public class StopCommand extends CommandBase {

    private final WebcamSubsystem subsystem;

    public StopCommand(WebcamSubsystem subsystem) {
        this.subsystem = subsystem;

        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        subsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
