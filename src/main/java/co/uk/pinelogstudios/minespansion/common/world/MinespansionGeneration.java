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
    private static final ArrayList<ConfiguredFeature<?, ?>> stones = new ArrayList<>();
    private static ConfiguredFeature<?, ?> frostberry;
    private static ConfiguredFeature<?, ?> sand_rock;

    private static void ores() {
        ores.add(register("silver_ore", Feature.ORE.withConfiguration(new OreFeatureConfig(
                OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, BlockList.SILVER_ORE.get().getDefaultState(), 4))
                .range(64).square()
                .func_242731_b(64)));
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

    private static void setSandRock() {
        sand_rock = register("sand_rock", Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, BlockList.SAND_ROCK.get().getDefaultState(), 33)).range(80).square().func_242731_b(10));
    }

    private static void setFrostberry() {
        frostberry = register("frostberry", Feature.RANDOM_PATCH.withConfiguration((new BlockClusterFeatureConfig.Builder(
                new SimpleBlockStateProvider(BlockList.FROSTBERRY_BUSH.get().getDefaultState()), SimpleBlockPlacer.PLACER))
                    .tries(64)
                    .whitelist(ImmutableSet.of(
                            Blocks.ICE,
                            Blocks.BLUE_ICE,
                            Blocks.FROSTED_ICE,
                            Blocks.PACKED_ICE
                    )).func_227317_b_().build()).withPlacement(Features.Placements.PATCH_PLACEMENT).chance(12));
    }

    public static void init() {
        ores();
        stones();
        setSandRock();
        setFrostberry();
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void gen(BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder generation = event.getGeneration();
        for(ConfiguredFeature<?, ?> ore : ores) {
            if(ore != null) generation.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ore);
        }

        for(ConfiguredFeature<?, ?> stone : stones) {
            if(stone != null) generation.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, stone);
        }

        if(event.getCategory() == Biome.Category.DESERT) {
            generation.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, sand_rock);
        }

            generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, frostberry);
    }

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(Minespansion.ID, name), configuredFeature);
    }
}
