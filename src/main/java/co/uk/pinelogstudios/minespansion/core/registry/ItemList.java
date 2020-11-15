package co.uk.pinelogstudios.minespansion.core.registry;

import co.uk.pinelogstudios.minespansion.common.materials.ArmorMaterialList;
import co.uk.pinelogstudios.minespansion.core.Minespansion;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Author: Mr. Pineapple
 */
public class ItemList {
    public static final Item.Properties item = new Item.Properties().group(Minespansion.MINESPANSION_TAB);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Minespansion.ID);

    public static final RegistryObject<Item> SILVER_INGOT = ITEMS.register("silver_ingot", () -> new Item(item));
    public static final RegistryObject<Item> SILVER_NUGGET = ITEMS.register("silver_nugget", () -> new Item(item));

    public static final RegistryObject<Item> SILVER_HELMET = ITEMS.register("silver_helmet", () -> new ArmorItem(ArmorMaterialList.SILVER, EquipmentSlotType.HEAD, item));
    public static final RegistryObject<Item> SILVER_CHESTPLATE = ITEMS.register("silver_chestplate", () -> new ArmorItem(ArmorMaterialList.SILVER, EquipmentSlotType.CHEST, item));
    public static final RegistryObject<Item> SILVER_LEGGINGS = ITEMS.register("silver_leggings", () -> new ArmorItem(ArmorMaterialList.SILVER, EquipmentSlotType.LEGS, item));
    public static final RegistryObject<Item> SILVER_BOOTS = ITEMS.register("silver_boots", () -> new ArmorItem(ArmorMaterialList.SILVER, EquipmentSlotType.FEET, item));

    public static final RegistryObject<Item> SILVER_SWORD = ITEMS.register("silver_sword", () -> new SwordItem(ItemTier.IRON, 3, -2.4F, item));
    public static final RegistryObject<Item> SILVER_PICKAXE = ITEMS.register("silver_pickaxe", () -> new PickaxeItem(ItemTier.IRON, 1, -2.8F, new Item.Properties().addToolType(ToolType.PICKAXE, 2).group(Minespansion.MINESPANSION_TAB)));
    public static final RegistryObject<Item> SILVER_AXE = ITEMS.register("silver_axe", () -> new AxeItem(ItemTier.IRON, 6, -3.1F, new Item.Properties().addToolType(ToolType.AXE, 2).group(Minespansion.MINESPANSION_TAB)));
    public static final RegistryObject<Item> SILVER_SHOVEL = ITEMS.register("silver_shovel", () -> new ShovelItem(ItemTier.IRON, 1.5F, -3, new Item.Properties().addToolType(ToolType.SHOVEL, 2).group(Minespansion.MINESPANSION_TAB)));
    public static final RegistryObject<Item> SILVER_HOE = ITEMS.register("silver_hoe", () -> new HoeItem(ItemTier.IRON, -2, -1.0F, new Item.Properties().addToolType(ToolType.HOE, 2).group(Minespansion.MINESPANSION_TAB)){});

}
