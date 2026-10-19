package net.valeria.mod.registry;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.valeria.mod.ValeriaMod;

public final class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ValeriaMod.MOD_ID);

    public static final DeferredBlock<Block> CELESTIAL_STONE = BLOCKS.registerSimpleBlock("celestial_stone",
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(1.5F, 6.0F)
                    .requiresCorrectToolForDrops().sound(SoundType.STONE));

    public static final DeferredBlock<Block> SMOOTH_CELESTIAL_STONE = BLOCKS.registerSimpleBlock("smooth_celestial_stone",
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(1.5F, 6.0F)
                    .requiresCorrectToolForDrops().sound(SoundType.STONE));

    public static final DeferredBlock<Block> VALERIAN_TURF = BLOCKS.registerSimpleBlock("valerian_turf",
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).strength(0.6F).sound(SoundType.GRASS));

    public static final DeferredBlock<RotatedPillarBlock> WHISPER_WILLOW_LOG = BLOCKS.register("whisper_willow_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK)
                    .strength(2.0F).sound(SoundType.CHERRY_WOOD).ignitedByLava()));

    public static final DeferredBlock<Block> WHISPER_WILLOW_PLANKS = BLOCKS.registerSimpleBlock("whisper_willow_planks",
            BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).strength(2.0F, 3.0F)
                    .sound(SoundType.CHERRY_WOOD).ignitedByLava());

    public static final DeferredBlock<LeavesBlock> LUMINESCENT_PETALS = BLOCKS.register("luminescent_petals",
            () -> new LeavesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PINK).strength(0.2F)
                    .randomTicks().sound(SoundType.CHERRY_LEAVES).noOcclusion()
                    .isValidSpawn((state, level, pos, type) -> false)
                    .isSuffocating((state, level, pos) -> false)
                    .isViewBlocking((state, level, pos) -> false)
                    .lightLevel(state -> 12)
                    .ignitedByLava().pushReaction(PushReaction.DESTROY)));

    public static final DeferredBlock<DropExperienceBlock> AETHERIUM_CRYSTAL_ORE = BLOCKS.register("aetherium_crystal_ore",
            () -> new DropExperienceBlock(UniformInt.of(1, 4), BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_PURPLE).strength(3.0F, 3.0F).requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST).lightLevel(state -> 4)));

    private ModBlocks() {}
}
