package net.valeria.mod.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.valeria.mod.ValeriaMod;
import net.valeria.mod.item.CloudsJewelsItem;

public final class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ValeriaMod.MOD_ID);

    // Block items
    public static final DeferredItem<BlockItem> CELESTIAL_STONE = ITEMS.registerSimpleBlockItem(ModBlocks.CELESTIAL_STONE);
    public static final DeferredItem<BlockItem> SMOOTH_CELESTIAL_STONE = ITEMS.registerSimpleBlockItem(ModBlocks.SMOOTH_CELESTIAL_STONE);
    public static final DeferredItem<BlockItem> VALERIAN_TURF = ITEMS.registerSimpleBlockItem(ModBlocks.VALERIAN_TURF);
    public static final DeferredItem<BlockItem> WHISPER_WILLOW_LOG = ITEMS.registerSimpleBlockItem(ModBlocks.WHISPER_WILLOW_LOG);
    public static final DeferredItem<BlockItem> WHISPER_WILLOW_PLANKS = ITEMS.registerSimpleBlockItem(ModBlocks.WHISPER_WILLOW_PLANKS);
    public static final DeferredItem<BlockItem> LUMINESCENT_PETALS = ITEMS.registerSimpleBlockItem(ModBlocks.LUMINESCENT_PETALS);
    public static final DeferredItem<BlockItem> AETHERIUM_CRYSTAL_ORE = ITEMS.registerSimpleBlockItem(ModBlocks.AETHERIUM_CRYSTAL_ORE);

    // Plain items
    public static final DeferredItem<Item> AETHERIUM_CRYSTAL = ITEMS.registerSimpleItem("aetherium_crystal");
    public static final DeferredItem<Item> SHIMMERING_VELVET = ITEMS.registerSimpleItem("shimmering_velvet");

    // Curios neck item
    public static final DeferredItem<CloudsJewelsItem> CLOUDS_JEWELS = ITEMS.registerItem("clouds_jewels",
            CloudsJewelsItem::new, new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).fireResistant());

    // Spawn eggs
    public static final DeferredItem<DeferredSpawnEggItem> GLIMMER_STAG_SPAWN_EGG = ITEMS.register("glimmer_stag_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.GLIMMER_STAG, 0xE2CDFA, 0xFFE082, new Item.Properties()));
    public static final DeferredItem<DeferredSpawnEggItem> CLOUD_RAY_SPAWN_EGG = ITEMS.register("cloud_ray_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.CLOUD_RAY, 0xA0ECF4, 0xC8B6F6, new Item.Properties()));

    private ModItems() {}
}
