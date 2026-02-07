package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.Transfer;

public class TransferArtifact extends CommandBase {
    private Transfer transfer;

    public TransferArtifact(Transfer transfer) {
        this.transfer = transfer;

        addRequirements(transfer);
    }

    @Override
    public void initialize() {
        transfer.transferArtifact();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
