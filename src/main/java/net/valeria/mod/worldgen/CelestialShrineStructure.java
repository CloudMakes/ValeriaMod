package net.valeria.mod.worldgen;

import com.mojang.serialization.MapCodec;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.valeria.mod.registry.ModStructures;

/** Small floating ruin of celestial stone pillars and lanterns with a hidden vault under the floor. */
public class CelestialShrineStructure extends Structure {
    public static final MapCodec<CelestialShrineStructure> CODEC = simpleCodec(CelestialShrineStructure::new);

    public CelestialShrineStructure(Structure.StructureSettings settings) {
        super(settings);
    }

    @Override
    protected Optional<Structure.GenerationStub> findGenerationPoint(Structure.GenerationContext context) {
        ChunkPos chunkPos = context.chunkPos();
        int x = chunkPos.getMinBlockX() + 8;
        int z = chunkPos.getMinBlockZ() + 8;
        // first free block above the highest solid block of the island under the chunk centre
        int firstFree = context.chunkGenerator().getFirstOccupiedHeight(x, z, Heightmap.Types.WORLD_SURFACE_WG,
                context.heightAccessor(), context.randomState());
        // no island here (column is void) -> skip
        if (firstFree <= context.chunkGenerator().getMinY() + 12) {
            return Optional.empty();
        }
        final BlockPos surface = new BlockPos(x, firstFree - 1, z);
        return Optional.of(new Structure.GenerationStub(surface, builder -> builder.addPiece(new CelestialShrinePiece(surface))));
    }

    @Override
    public StructureType<?> type() {
        return ModStructures.CELESTIAL_SHRINE.get();
    }
}
