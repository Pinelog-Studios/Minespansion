package co.uk.pinelogstudios.minespansion.core.registry;

import co.uk.pinelogstudios.minespansion.common.blocks.FrostberryBush;
import co.uk.pinelogstudios.minespansion.core.Minespansion;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
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