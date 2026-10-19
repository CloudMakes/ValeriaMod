package net.valeria.mod.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.valeria.mod.ValeriaMod;

public final class ModDimensions {
    public static final ResourceKey<Level> VALERIA = ResourceKey.create(Registries.DIMENSION,
            ResourceLocation.fromNamespaceAndPath(ValeriaMod.MOD_ID, "valeria"));

    private ModDimensions() {}
}
