package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.Transfer;

public class StopTransfer extends CommandBase {
    private Transfer transfer;

    public StopTransfer(Transfer transfer) {
        this.transfer = transfer;

        addRequirements(transfer);
    }

    @Override
    public void initialize() {
        transfer.stop();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
