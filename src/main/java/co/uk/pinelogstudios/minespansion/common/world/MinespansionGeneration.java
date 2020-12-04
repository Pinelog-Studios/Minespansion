package co.uk.pinelogstudios.minespansion.common.world;

import co.uk.pinelogstudios.minespansion.core.Minespansion;
import co.uk.pinelogstudios.minespansion.core.registry.BlockList;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;

/*
 * Author: Mr. Pineapple
 */
@Mod.EventBusSubscriber
public class MinespansionGeneration {

    /*
     * If you wanted to you could just plug this all straight into the BiomeLoadingEvent,
     * but I haven't cause there isn't really a need. And this way will be easier when
     * adding more generation later on.
     */

    private static final ArrayList<ConfiguredFeature<?, ?>> ores = new ArrayList<ConfiguredFeature<?, ?>>();
    //Array list keeps the flowers in one place (can use more than one if needed)
    private static final ArrayList<ConfiguredFeature<?, ?>> flowers = new ArrayList<ConfiguredFeature<?, ?>>();

    public static void ores() {
        ores.add(register("silver_ore", Feature.ORE.withConfiguration(new OreFeatureConfig(
                OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, BlockList.SILVER_ORE.get().getDefaultState(), 4))
                .range(64).square()
                .func_242731_b(64)));
    }

    //Method for adding flowers. Call this method inside of the FMLCommonSetup (Check Minespansion.java)
    public static void flowers() {

        //If you have more then you can copy and paste what's in this again, or use the weighted system inside
        flowers.add(register("frostberry_generation", Feature.FLOWER.withConfiguration(new BlockClusterFeatureConfig.Builder(
                new SimpleBlockStateProvider(
                        BlockList.FROSTBERRY_BUSH.get().getDefaultState()
                ), SimpleBlockPlacer.PLACER
        ).tries(64).build())
                .withPlacement(Features.Placements.VEGETATION_PLACEMENT)
                .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
        .func_242731_b(2)));
    }

    //public static final BlockClusterFeatureConfig DEAD_BUSH_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(Features.States.DEAD_BUSH), SimpleBlockPlacer.PLACER)).tries(4).build();
    //Feature.FLOWER.withConfiguration(Features.Configs.NORMAL_FLOWER_CONFIG).withPlacement(Features.Placements.VEGETATION_PLACEMENT).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(2));

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void gen(BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder generation = event.getGeneration();
        for(ConfiguredFeature<?, ?> ore : ores) {
            if(ore != null) generation.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ore);
        }
        //Iterates through the array list for each flower
        for(ConfiguredFeature<?, ?> flower : flowers) {
            //Makes sure the flower is there then generates. this is simplified to one line, but you could open it up and do checks for the biome, event.getBiome() or category, I haven't checked lol
            if(flower != null) generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, flower);
        }
    }

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(Minespansion.ID, name), configuredFeature);
    }
}
