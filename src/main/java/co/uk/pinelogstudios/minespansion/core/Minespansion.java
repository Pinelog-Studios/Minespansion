package co.uk.pinelogstudios.minespansion.core;

import co.uk.pinelogstudios.minespansion.common.world.MinespansionGeneration;
import co.uk.pinelogstudios.minespansion.core.registry.BlockList;
import co.uk.pinelogstudios.minespansion.core.registry.GroupList;
import co.uk.pinelogstudios.minespansion.core.registry.ItemList;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * Author: Mr. Pineapple
 */
@SuppressWarnings("deprecation")
@Mod(Minespansion.ID)
public class Minespansion {
    public static final String ID = "minespansion";
    public static final String PROTOCOL_VERSION = ID.toUpperCase();
    public static final ItemGroup MINESPANSION_TAB = new GroupList.MinespansionGroup();

    public Minespansion() {
        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        registerDeferredRegistries(bus);
        bus.addListener(this::commonSetup);
        bus.addListener(this::clientSetup);


        MinecraftForge.EVENT_BUS.register(this);
    }

    private void registerDeferredRegistries(final IEventBus bus) {
        ItemList.ITEMS.register(bus);
        BlockList.BLOCKS.register(bus);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        //Must use DeferredWorkQueue for threading issues
        DeferredWorkQueue.runLater(() -> {
            MinespansionGeneration.ores();
            MinespansionGeneration.flowers();
        });
    }

    private void clientSetup(final FMLClientSetupEvent event) {}
}
