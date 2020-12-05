package co.uk.pinelogstudios.minespansion.core.registry;

import co.uk.pinelogstudios.minespansion.common.blocks.FrostberryBush;
import co.uk.pinelogstudios.minespansion.core.Minespansion;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.world.gen.feature.structure.FortressPieces;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Author: Mr. Pineapple
 */
public class BlockList {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Minespansion.ID);

    public static final RegistryObject<Block> SILVER_ORE = createBlock("silver_ore", AbstractBlock.Properties.from(Blocks.IRON_ORE));
    public static final RegistryObject<Block> SILVER_BLOCK = createBlock("silver_block", AbstractBlock.Properties.from(Blocks.IRON_BLOCK));

    public static final RegistryObject<Block> FROSTBERRY_BUSH = createBlockNone("frostberry_bush", new FrostberryBush());

    public static final RegistryObject<Block> PEARLSTONE = createStoneBlock("pearlstone");
    public static final RegistryObject<Block> POLISHED_PEARLSTONE = createStoneBlock("polished_pearlstone");
    public static final RegistryObject<Block> PEARLSTONE_BRICKS = createStoneBlock("pearlstone_bricks");
    public static final RegistryObject<Block> PEARLSTONE_TILES = createStoneBlock("pearlstone_tiles");
    public static final RegistryObject<Block> CHISELED_PEARLSTONE = createStoneBlock("chiseled_pearlstone");

    public static final RegistryObject<Block> PEARLSTONE_STAIRS = createStoneStair(PEARLSTONE, "pearlstone_stairs");
    public static final RegistryObject<Block> POLISHED_PEARLSTONE_STAIRS = createStoneStair(POLISHED_PEARLSTONE, "polished_pearlstone_stairs");
    public static final RegistryObject<Block> PEARLSTONE_BRICK_STAIRS = createStoneStair(PEARLSTONE_BRICKS, "pearlstone_brick_stairs");

    public static final RegistryObject<Block> PEARLSTONE_SLAB = createStoneSlab("pearlstone_slab");
    public static final RegistryObject<Block> POLISHED_PEARLSTONE_SLAB = createStoneSlab("polished_pearlstone_slab");
    public static final RegistryObject<Block> PEARLSTONE_BRICK_SLAB = createStoneSlab("pearlstone_brick_slab");

    public static final RegistryObject<Block> SLATE = createStoneBlock("slate");
    public static final RegistryObject<Block> POLISHED_SLATE = createStoneBlock("polished_slate");
    public static final RegistryObject<Block> SLATE_BRICKS = createStoneBlock("slate_bricks");
    public static final RegistryObject<Block> SLATE_TILES = createStoneBlock("slate_tiles");
    public static final RegistryObject<Block> CHISELED_SLATE = createStoneBlock("chiseled_slate");
    public static final RegistryObject<Block> SLATE_STAIRS = createStoneStair(PEARLSTONE, "slate_stairs");
    public static final RegistryObject<Block> POLISHED_SLATE_STAIRS = createStoneStair(POLISHED_PEARLSTONE, "polished_slate_stairs");
    public static final RegistryObject<Block> SLATE_BRICK_STAIRS = createStoneStair(PEARLSTONE_BRICKS, "slate_brick_stairs");
    public static final RegistryObject<Block> SLATE_SLAB = createStoneSlab("slate_slab");
    public static final RegistryObject<Block> POLISHED_SLATE_SLAB = createStoneSlab("polished_slate_slab");
    public static final RegistryObject<Block> SLATE_BRICK_SLAB = createStoneSlab("slate_brick_slab");

    public static final RegistryObject<Block> SAND_ROCK = createStoneBlock("sand_rock");
    public static final RegistryObject<Block> POLISHED_SAND_ROCK = createStoneBlock("polished_sand_rock");
    public static final RegistryObject<Block> SAND_ROCK_BRICKS = createStoneBlock("sand_rock_bricks");
    public static final RegistryObject<Block> SAND_ROCK_TILES = createStoneBlock("sand_rock_tiles");
    public static final RegistryObject<Block> CHISELED_SAND_ROCK = createStoneBlock("chiseled_sand_rock");
    public static final RegistryObject<Block> SAND_ROCK_STAIRS = createStoneStair(PEARLSTONE, "sand_rock_stairs");
    public static final RegistryObject<Block> POLISHED_SAND_ROCK_STAIRS = createStoneStair(POLISHED_PEARLSTONE, "polished_sand_rock_stairs");
    public static final RegistryObject<Block> SAND_ROCK_BRICK_STAIRS = createStoneStair(PEARLSTONE_BRICKS, "sand_rock_brick_stairs");
    public static final RegistryObject<Block> SAND_ROCK_SLAB = createStoneSlab("sand_rock_slab");
    public static final RegistryObject<Block> POLISHED_SAND_ROCK_SLAB = createStoneSlab("polished_sand_rock_slab");
    public static final RegistryObject<Block> SAND_ROCK_BRICK_SLAB = createStoneSlab("sand_rock_brick_slab");

    private static RegistryObject<Block> createStoneBlock(String name) {
        RegistryObject<Block> block = BLOCKS.register(name, () -> new Block(AbstractBlock.Properties.from(Blocks.STONE)));
        ItemList.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().group(Minespansion.MINESPANSION_TAB)));
        return block;
    }

    private static RegistryObject<Block> createStoneStair(RegistryObject<Block> block, String name) {
        RegistryObject<Block> blockIn = BLOCKS.register(name, () -> new StairsBlock(() -> block.get().getDefaultState(), AbstractBlock.Properties.from(Blocks.STONE)));
        ItemList.ITEMS.register(name, () -> new BlockItem(blockIn.get(), new Item.Properties().group(Minespansion.MINESPANSION_TAB)));
        return blockIn;
    }

    private static RegistryObject<Block> createStoneSlab(String name) {
        RegistryObject<Block> blockIn = BLOCKS.register(name, () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.STONE)));
        ItemList.ITEMS.register(name, () -> new BlockItem(blockIn.get(), new Item.Properties().group(Minespansion.MINESPANSION_TAB)));
        return blockIn;
    }

    private static RegistryObject<Block> createBlock(String name, AbstractBlock.Properties properties) {
        RegistryObject<Block> block = BLOCKS.register(name, () -> new Block(properties));
        ItemList.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().group(Minespansion.MINESPANSION_TAB)));
        return block;
    }

    private static RegistryObject<Block> createBlockNone(String name, Block block) {
        RegistryObject<Block> blockIn = BLOCKS.register(name, () -> block);
        ItemList.ITEMS.register(name, () -> new BlockItem(blockIn.get(), new Item.Properties().group(Minespansion.MINESPANSION_TAB)));
        return blockIn;
    }
}