package co.uk.pinelogstudios.minespansion.core.registry;

import co.uk.pinelogstudios.minespansion.core.Minespansion;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Author: Mr. Pineapple
 */
public class ItemList {
    private static final Item.Properties item = new Item.Properties().group(Minespansion.MINESPANSION_TAB);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Minespansion.ID);

    public static final RegistryObject<Item> SILVER_INGOT = ITEMS.register("silver_ingot", () -> new Item(item));
    public static final RegistryObject<Item> SILVER_NUGGET = ITEMS.register("silver_nugget", () -> new Item(item));


}
