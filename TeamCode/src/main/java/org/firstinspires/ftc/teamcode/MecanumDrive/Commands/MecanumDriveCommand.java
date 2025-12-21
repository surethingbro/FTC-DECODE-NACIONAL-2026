package org.firstinspires.ftc.teamcode.MecanumDrive.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.MecanumDrive.Subsystem.MecanumDriveSubsystem;

import java.util.function.DoubleSupplier;

public class MecanumDriveCommand extends CommandBase {

    private final MecanumDriveSubsystem mecanum;
    private final DoubleSupplier fwd;
    private final DoubleSupplier lat;
    private final DoubleSupplier trn;

    public MecanumDriveCommand(MecanumDriveSubsystem subsystem, DoubleSupplier forward, DoubleSupplier lateral, DoubleSupplier turn) {
        mecanum = subsystem;
        fwd = forward;
        lat = lateral;
        trn = turn;

        addRequirements(mecanum);
    }

    @Override
    public void execute() {
        mecanum.drive(fwd.getAsDouble(), lat.getAsDouble(), trn.getAsDouble());
    }
}
