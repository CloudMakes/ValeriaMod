package net.valeria.mod.event;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.valeria.mod.ValeriaMod;
import net.valeria.mod.registry.ModDimensions;

/** Gameplay rules for the Valeria dimension: peaceful, trample-proof farmland, starlight showers. */
@EventBusSubscriber(modid = ValeriaMod.MOD_ID)
public final class ValeriaGameEvents {
    private ValeriaGameEvents() {}

    /** No hostile mob may ever enter Valeria (natural spawns, spawners, spawn eggs, commands, teleports). */
    @SubscribeEvent
    public static void onEntityJoin(EntityJoinLevelEvent event) {
        Level level = event.getLevel();
        if (!level.isClientSide() && level.dimension() == ModDimensions.VALERIA && event.getEntity() instanceof Enemy) {
            event.setCanceled(true);
        }
    }

    /** Farmland never turns to dirt from being walked on in Valeria. */
    @SubscribeEvent
    public static void onFarmlandTrample(BlockEvent.FarmlandTrampleEvent event) {
        if (event.getLevel() instanceof Level level && level.dimension() == ModDimensions.VALERIA) {
            event.setCanceled(true);
        }
    }

    /** "Weather" in Valeria is only a gentle shower of sparkles around each player while it is raining elsewhere. */
    @SubscribeEvent
    public static void onLevelTick(LevelTickEvent.Post event) {
        if (!(event.getLevel() instanceof ServerLevel level) || level.dimension() != ModDimensions.VALERIA) {
            return;
        }
        if (level.getGameTime() % 4L != 0L || !level.isRaining()) {
            return;
        }
        for (ServerPlayer player : level.players()) {
            level.sendParticles(ParticleTypes.END_ROD, player.getX(), player.getY() + 6.0D, player.getZ(),
                    6, 6.0D, 2.0D, 6.0D, 0.02D);
        }
    }
}
