package net.valeria.mod;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.valeria.mod.registry.ModBlocks;
import net.valeria.mod.registry.ModCreativeModeTabs;
import net.valeria.mod.registry.ModEntities;
import net.valeria.mod.registry.ModItems;
import net.valeria.mod.registry.ModStructures;
import org.slf4j.Logger;

/**
 * Valeria - a peaceful pastel sky-island dimension.
 * Curios integration is fully data-driven in Curios 9.x (slot json + item tag), so no InterMod messages are needed.
 */
@Mod(ValeriaMod.MOD_ID)
public class ValeriaMod {
    public static final String MOD_ID = "valeria";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ValeriaMod(IEventBus modEventBus, ModContainer modContainer) {
        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModEntities.ENTITY_TYPES.register(modEventBus);
        ModStructures.STRUCTURE_TYPES.register(modEventBus);
        ModStructures.PIECE_TYPES.register(modEventBus);
        ModCreativeModeTabs.CREATIVE_TABS.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Valeria loaded: Curios neck slot items are registered through data (data/curios).");
    }
}
