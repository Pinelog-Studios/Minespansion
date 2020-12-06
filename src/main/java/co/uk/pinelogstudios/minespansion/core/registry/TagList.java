package co.uk.pinelogstudios.minespansion.core.registry;

import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;

/**
 * Author: Mr. Pineapple
 */
public class TagList {
    public static class Blocks {
        public static final ITag.INamedTag<Block> FROSTBERRY_BASE_BLOCKS = createTag("frostberry_base_blocks");

        private static ITag.INamedTag<Block> createTag(String name) {
            return BlockTags.makeWrapperTag("minespansion:" + name);
        }
    }
}
