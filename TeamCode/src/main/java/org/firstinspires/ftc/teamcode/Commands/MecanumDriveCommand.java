package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.MecanumDrivetrain;

import java.util.function.DoubleSupplier;

public class MecanumDriveCommand extends CommandBase {
    private final MecanumDrivetrain mecanum;
    private final DoubleSupplier fwd, lat, trn;

    public MecanumDriveCommand(MecanumDrivetrain subsystem, DoubleSupplier forward, DoubleSupplier lateral, DoubleSupplier turn) {
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
