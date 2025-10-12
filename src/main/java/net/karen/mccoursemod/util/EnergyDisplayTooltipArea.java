package net.karen.mccoursemod.util;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.transfer.energy.EnergyHandler;
import java.util.Collections;
import java.util.List;

/* BluSunrize - Copyright (c) 2021 - This code is licensed under "Blu's License of Common Sense"
   https://github.com/BluSunrize/ImmersiveEngineering/blob/1.19.2/LICENSE - Slightly Modified Version by: Kaupenjoe */
public class EnergyDisplayTooltipArea {
    private final int xPos, yPos, width, height;
    private final EnergyHandler energy;

    public EnergyDisplayTooltipArea(int xMin, int yMin, EnergyHandler energy)  {
        this(xMin, yMin, energy,8,64);
    }

    public EnergyDisplayTooltipArea(int xMin, int yMin, EnergyHandler energy, int width, int height)  {
        xPos = xMin;
        yPos = yMin;
        this.width = width;
        this.height = height;
        this.energy = energy;
    }

    public List<Component> getTooltips() {
        return List.of(Component.literal(energy.getAmountAsInt() + " / " + energy.getCapacityAsInt() + " FE"));
    }


    public List<ClientTooltipComponent> getClientTooltip() {
        return Collections.singletonList(ClientTooltipComponent.create(getTooltips().getFirst().getVisualOrderText()));
    }

    public void render(GuiGraphics guiGraphics) {
        int maxEnergy = energy.getCapacityAsInt(), energyStored = energy.getAmountAsInt();
        if (maxEnergy > 0 && energyStored > 0) {
            int stored = Math.max(1, (int)(height * (energyStored / (float) maxEnergy)));
            guiGraphics.fill(xPos, yPos, xPos + width, yPos + height, 0xFF333333);
            guiGraphics.fillGradient(xPos,yPos + (height - stored),xPos + width, yPos + height,
                                     0xffb51500, 0xff600b00);
        }
    }
}
