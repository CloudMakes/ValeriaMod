package net.valeria.mod.event;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.valeria.mod.ValeriaMod;
import net.valeria.mod.entity.CloudRayEntity;
import net.valeria.mod.entity.GlimmerStagEntity;
import net.valeria.mod.registry.ModEntities;

@EventBusSubscriber(modid = ValeriaMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public final class CommonModEvents {
    private CommonModEvents() {}

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.GLIMMER_STAG.get(), GlimmerStagEntity.createAttributes().build());
        event.put(ModEntities.CLOUD_RAY.get(), CloudRayEntity.createAttributes().build());
    }
}
