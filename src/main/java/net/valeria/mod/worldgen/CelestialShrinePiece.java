package net.valeria.mod.worldgen;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.valeria.mod.ValeriaMod;
import net.valeria.mod.registry.ModBlocks;
import net.valeria.mod.registry.ModStructures;

/**
 * Procedurally built shrine (no NBT template needed). The piece's bounding box encodes its position:
 * centre x/z = middle of the box, surface y = minY + 5.
 */
public class CelestialShrinePiece extends StructurePiece {
    private static final int RADIUS = 5;
    private static final int BELOW = 5;
    private static final int ABOVE = 8;
    public static final ResourceKey<LootTable> LOOT = ResourceKey.create(Registries.LOOT_TABLE,
            ResourceLocation.fromNamespaceAndPath(ValeriaMod.MOD_ID, "chests/celestial_shrine"));

    public CelestialShrinePiece(BlockPos surface) {
        super(ModStructures.CELESTIAL_SHRINE_PIECE.get(), 0, new BoundingBox(
                surface.getX() - RADIUS, surface.getY() - BELOW, surface.getZ() - RADIUS,
                surface.getX() + RADIUS, surface.getY() + ABOVE, surface.getZ() + RADIUS));
    }

    public CelestialShrinePiece(CompoundTag tag) {
        super(ModStructures.CELESTIAL_SHRINE_PIECE.get(), tag);
    }

    @Override
    protected void addAdditionalSaveData(StructurePieceSerializationContext context, CompoundTag tag) {
        // nothing extra: position is derived from the bounding box
    }

    private void put(WorldGenLevel level, BoundingBox chunkBox, BlockState state, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        if (chunkBox.isInside(pos)) {
            level.setBlock(pos, state, 2);
        }
    }

    @Override
    public void postProcess(WorldGenLevel level, StructureManager structureManager, ChunkGenerator generator,
                            RandomSource random, BoundingBox chunkBox, ChunkPos chunkPos, BlockPos pivot) {
        final int cx = (this.boundingBox.minX() + this.boundingBox.maxX()) / 2;
        final int cz = (this.boundingBox.minZ() + this.boundingBox.maxZ()) / 2;
        final int top = this.boundingBox.minY() + BELOW;

        final BlockState smooth = ModBlocks.SMOOTH_CELESTIAL_STONE.get().defaultBlockState();
        final BlockState stone = ModBlocks.CELESTIAL_STONE.get().defaultBlockState();
        final BlockState air = Blocks.AIR.defaultBlockState();
        final BlockState lantern = Blocks.LANTERN.defaultBlockState();
        final double[] radii = {6.0D, 5.0D, 4.0D, 3.0D, 1.8D};

        // inverted-cone floating platform
        for (int k = 0; k < radii.length; k++) {
            for (int dx = -RADIUS; dx <= RADIUS; dx++) {
                for (int dz = -RADIUS; dz <= RADIUS; dz++) {
                    if (Math.sqrt(dx * dx + dz * dz) <= radii[k]) {
                        this.put(level, chunkBox, k == 0 ? smooth : stone, cx + dx, top - k, cz + dz);
                    }
                }
            }
        }
        // hidden vault: 3x3x2 chamber sealed under the floor
        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                this.put(level, chunkBox, air, cx + dx, top - 1, cz + dz);
                this.put(level, chunkBox, air, cx + dx, top - 2, cz + dz);
            }
        }
        this.put(level, chunkBox, lantern, cx + 1, top - 2, cz + 1);
        this.put(level, chunkBox, lantern, cx - 1, top - 2, cz - 1);
        BlockPos chestPos = new BlockPos(cx, top - 2, cz);
        this.createChest(level, chunkBox, random, chestPos, LOOT,
                Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.NORTH));

        // four weathered pillars with lanterns
        for (int sx = -1; sx <= 1; sx += 2) {
            for (int sz = -1; sz <= 1; sz += 2) {
                int px = cx + sx * 4;
                int pz = cz + sz * 4;
                int height = 4 + random.nextInt(3);
                for (int y = 1; y <= height; y++) {
                    this.put(level, chunkBox, (y == height || random.nextInt(6) != 0) ? stone : smooth, px, top + y, pz);
                }
                this.put(level, chunkBox, lantern, px, top + height + 1, pz);
            }
        }
        // central pedestal + altar lantern
        this.put(level, chunkBox, smooth, cx, top + 1, cz);
        this.put(level, chunkBox, lantern, cx, top + 2, cz);
    }
}
