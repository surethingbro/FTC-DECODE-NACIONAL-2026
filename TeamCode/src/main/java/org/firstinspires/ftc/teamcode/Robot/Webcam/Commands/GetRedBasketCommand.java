package org.firstinspires.ftc.teamcode.Robot.Webcam.Commands;
import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Robot.Webcam.Subsystem.WebcamSubsystem;

public class GetRedBasketCommand extends CommandBase {
    private final WebcamSubsystem subsystem;

    public GetRedBasketCommand(WebcamSubsystem subsystem) {
        this.subsystem = subsystem;

        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        subsystem.getRedBasketTag();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
