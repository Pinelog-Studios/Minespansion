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

    private static final ArrayList<ConfiguredFeature<?, ?>> ores = new ArrayList<ConfiguredFeature<?, ?>>();
    private static final ArrayList<ConfiguredFeature<?, ?>> flowers = new ArrayList<ConfiguredFeature<?, ?>>();
    private static final ArrayList<ConfiguredFeature<?, ?>> stones = new ArrayList<>();
    private static ConfiguredFeature<?, ?> sand_rock;

    //builder.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Features.ORE_ANDESITE);
    //public static final ConfiguredFeature<?, ?> ORE_ANDESITE = register("ore_andesite", Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, Features.States.ANDESITE, 33)).range(80).square().func_242731_b(10));

    private static void ores() {
        ores.add(register("silver_ore", Feature.ORE.withConfiguration(new OreFeatureConfig(
                OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, BlockList.SILVER_ORE.get().getDefaultState(), 4))
                .range(64).square()
                .func_242731_b(64)));
    }

    private static void flowers() {
        flowers.add(register("frostberry_generation", Feature.FLOWER.withConfiguration(new BlockClusterFeatureConfig.Builder(
                new SimpleBlockStateProvider(
                        BlockList.FROSTBERRY_BUSH.get().getDefaultState()
                ), SimpleBlockPlacer.PLACER
        ).tries(64).build())
                .withPlacement(Features.Placements.VEGETATION_PLACEMENT)
                .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
        .func_242731_b(2)));
    }

    private static void stones() {
        stones.add(register("pearlstone", Feature.ORE.withConfiguration(new OreFeatureConfig(
                OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, BlockList.PEARLSTONE.get().getDefaultState(), 33))
                .range(80).square()
                .func_242731_b(10)));
        stones.add(register("slate", Feature.ORE.withConfiguration(new OreFeatureConfig(
                OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, BlockList.SLATE.get().getDefaultState(), 33))
                .range(80).square()
                .func_242731_b(10)));
    }

    private static void sandRock() {
        sand_rock = register("sand_rock", Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, BlockList.SAND_ROCK.get().getDefaultState(), 33)).range(80).square().func_242731_b(10));
    }

    public static void init() {
        ores();
        flowers();
        stones();
        sandRock();
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void gen(BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder generation = event.getGeneration();
        for(ConfiguredFeature<?, ?> ore : ores) {
            if(ore != null) generation.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ore);
        }
        for(ConfiguredFeature<?, ?> flower : flowers) {
            if(flower != null) generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, flower);
        }
        for(ConfiguredFeature<?, ?> stone : stones) {
            if(stone != null) generation.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, stone);
        }
        generation.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, sand_rock);
    }

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(Minespansion.ID, name), configuredFeature);
    }
}
