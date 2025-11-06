package net.karen.top.screen;

import net.karen.top.Top;
import net.karen.top.screen.custom.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
           DeferredRegister.create(Registries.MENU, Top.MOD_ID);

    // Register all custom block entity menus
    public static final DeferredHolder<MenuType<?>, MenuType<PedestalMenu>> PEDESTAL_MENU =
           registerMenuType("pedestal_menu", PedestalMenu::new);

    public static final DeferredHolder<MenuType<?>, MenuType<GrowthChamberMenu>> GROWTH_CHAMBER_MENU =
           registerMenuType("growth_chamber_menu", GrowthChamberMenu::new);

    public static final DeferredHolder<MenuType<?>, MenuType<KaupenFurnaceMenu>> KAUPEN_FURNACE_MENU =
           registerMenuType("kaupen_furnace_menu", KaupenFurnaceMenu::new);

    public static final DeferredHolder<MenuType<?>, MenuType<CraftingPlusMenu>> CRAFTING_PLUS_MENU =
           registerMenuType("crafting_plus_menu", CraftingPlusMenu::new);

    public static final DeferredHolder<MenuType<?>, MenuType<GemEmpoweringStationMenu>> GEM_EMPOWERING_STATION_MENU =
           registerMenuType("gem_empowering_menu", GemEmpoweringStationMenu::new);

    // CUSTOM METHOD - Registry all custom menus
    private static <T extends AbstractContainerMenu> DeferredHolder<MenuType<?>, MenuType<T>>
                              registerMenuType(String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IMenuTypeExtension.create(factory));
    }

    // CUSTOM METHOD - Registry all custom menus on event bus
    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}