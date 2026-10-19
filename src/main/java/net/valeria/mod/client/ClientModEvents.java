package net.valeria.mod.client;

import net.minecraft.client.renderer.BiomeColors;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.valeria.mod.ValeriaMod;
import net.valeria.mod.registry.ModBlocks;
import net.valeria.mod.registry.ModEntities;

@EventBusSubscriber(modid = ValeriaMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientModEvents {
    private static final int DEFAULT_TURF_COLOR = 0xFFB7D5;

    private ClientModEvents() {}

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(GlimmerStagModel.LAYER, GlimmerStagModel::createBodyLayer);
        event.registerLayerDefinition(CloudRayModel.LAYER, CloudRayModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.GLIMMER_STAG.get(), GlimmerStagRenderer::new);
        event.registerEntityRenderer(ModEntities.CLOUD_RAY.get(), CloudRayRenderer::new);
    }

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        event.register((state, level, pos, tintIndex) ->
                        level != null && pos != null ? BiomeColors.getAverageGrassColor(level, pos) : DEFAULT_TURF_COLOR,
                ModBlocks.VALERIAN_TURF.get());
    }

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        event.register((stack, tintIndex) -> 0xFF000000 | DEFAULT_TURF_COLOR, ModBlocks.VALERIAN_TURF.get());
    }
}
