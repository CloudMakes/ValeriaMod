package net.valeria.mod.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.valeria.mod.ValeriaMod;
import net.valeria.mod.entity.CloudRayEntity;
import net.valeria.mod.entity.GlimmerStagEntity;

public final class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(Registries.ENTITY_TYPE, ValeriaMod.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<GlimmerStagEntity>> GLIMMER_STAG =
            ENTITY_TYPES.register("glimmer_stag", () -> EntityType.Builder
                    .of(GlimmerStagEntity::new, MobCategory.CREATURE)
                    .sized(0.9F, 1.7F).clientTrackingRange(10).build("glimmer_stag"));

    public static final DeferredHolder<EntityType<?>, EntityType<CloudRayEntity>> CLOUD_RAY =
            ENTITY_TYPES.register("cloud_ray", () -> EntityType.Builder
                    .of(CloudRayEntity::new, MobCategory.CREATURE)
                    .sized(1.8F, 0.5F).clientTrackingRange(10).build("cloud_ray"));

    private ModEntities() {}
}
