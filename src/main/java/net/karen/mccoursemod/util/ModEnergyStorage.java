package net.karen.mccoursemod.util;

import net.neoforged.neoforge.transfer.energy.SimpleEnergyHandler;
import net.neoforged.neoforge.transfer.transaction.TransactionContext;
import org.jetbrains.annotations.NotNull;

public abstract class ModEnergyStorage extends SimpleEnergyHandler {
    public ModEnergyStorage(int capacity, int maxTransfer) {
        super(capacity, maxTransfer);
    }

    @Override
    public int extract(int amount, @NotNull TransactionContext transaction) {
        int extractedEnergy = super.extract(maxExtract, transaction);
        if (extractedEnergy != 0) { onEnergyChanged(); }
        return extractedEnergy;
    }

    @Override
    public int insert(int amount, @NotNull TransactionContext transaction) {
        int receiveEnergy = super.insert(amount, transaction);
        if (receiveEnergy != 0) { onEnergyChanged(); }
        return receiveEnergy;
    }

    public int setEnergy(int energy) {
        this.energy = energy;
        return energy;
    }

    public abstract void onEnergyChanged();
}