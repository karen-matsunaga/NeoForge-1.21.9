package net.karen.top.fluid;

import net.karen.top.Top;
import net.karen.top.block.ModBlocks;
import net.karen.top.item.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;
import java.util.function.Supplier;
import static net.karen.top.util.ChatUtils.*;

public class ModFluids {
    // Registry all custom fluids
    public static final DeferredRegister<Fluid> FLUIDS =
           DeferredRegister.create(BuiltInRegistries.FLUID, Top.MOD_ID);

    // Registry all fluids source and fluids flowing
    public static final Supplier<FlowingFluid> SOURCE_SOAP_WATER =
           FLUIDS.register("soap_water_fluid",
           () -> new BaseFlowingFluid.Source(ModFluids.SOAP_WATER_FLUID_PROPERTIES));

    public static final Supplier<FlowingFluid> FLOWING_SOAP_WATER =
           FLUIDS.register("flowing_soap_water_fluid",
           () -> new BaseFlowingFluid.Flowing(ModFluids.SOAP_WATER_FLUID_PROPERTIES));

    // ** CUSTOM FLUID block **
    // Soap Water Block custom fluid
    public static final DeferredBlock<LiquidBlock> SOAP_WATER_BLOCK =
           ModBlocks.registerBlock("soap_water_block", props ->
                                   new LiquidBlock(ModFluids.SOURCE_SOAP_WATER.get(),
                                                   props.mapColor(MapColor.WATER).replaceable().noCollision()
                                                        .strength(100.0F).pushReaction(PushReaction.DESTROY)
                                                        .noLootTable().liquid().sound(SoundType.EMPTY)), soapWaterTintColor);

    // ** CUSTOM bucket fluid **
    // Soap Water Bucket custom fluid
    public static final DeferredItem<Item> SOAP_WATER_BUCKET =
           ModItems.ITEMS.registerItem("soap_water_bucket", props ->
                                       new BucketItem(ModFluids.SOURCE_SOAP_WATER.get(),
                                                      props.craftRemainder(Items.BUCKET).stacksTo(1)) {
                                           @Override
                                           public @NotNull Component getName(@NotNull ItemStack stack) {
                                               return componentTranslatableIntColor(this.descriptionId, soapWaterTintColor);
                                           }
                                       });

    // Registry all custom fluids when to inside on custom fluid
    public static final BaseFlowingFluid.Properties SOAP_WATER_FLUID_PROPERTIES =
           new BaseFlowingFluid.Properties(ModFluidTypes.SOAP_WATER_FLUID_TYPE, SOURCE_SOAP_WATER, FLOWING_SOAP_WATER)
                               .slopeFindDistance(2).levelDecreasePerBlock(1)
                               .block(ModFluids.SOAP_WATER_BLOCK).bucket(ModFluids.SOAP_WATER_BUCKET);

    // CUSTOM METHOD - Registry all custom fluids on event
    public static void register(IEventBus eventBus) { FLUIDS.register(eventBus); }
}