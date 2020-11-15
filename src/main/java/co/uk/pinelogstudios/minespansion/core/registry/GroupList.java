package co.uk.pinelogstudios.minespansion.core.registry;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

/**
 * Author: Mr. Pineapple
 */
public class GroupList {
    public static class MinespansionGroup extends ItemGroup {

        public MinespansionGroup() {
            super("minespansion");
        }

        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemList.SILVER_INGOT.get());
        }
    }
}
