package net.valeria.mod.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.valeria.mod.ValeriaMod;
import net.valeria.mod.worldgen.CelestialShrinePiece;
import net.valeria.mod.worldgen.CelestialShrineStructure;

public final class ModStructures {
    public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPES =
            DeferredRegister.create(Registries.STRUCTURE_TYPE, ValeriaMod.MOD_ID);
    public static final DeferredRegister<StructurePieceType> PIECE_TYPES =
            DeferredRegister.create(Registries.STRUCTURE_PIECE, ValeriaMod.MOD_ID);

    public static final DeferredHolder<StructureType<?>, StructureType<CelestialShrineStructure>> CELESTIAL_SHRINE =
            STRUCTURE_TYPES.register("celestial_shrine", ModStructures::shrineType);

    public static final DeferredHolder<StructurePieceType, StructurePieceType.ContextlessType> CELESTIAL_SHRINE_PIECE =
            PIECE_TYPES.register("celestial_shrine_piece", ModStructures::shrinePieceType);

    private static StructureType<CelestialShrineStructure> shrineType() {
        return () -> CelestialShrineStructure.CODEC;
    }

    private static StructurePieceType.ContextlessType shrinePieceType() {
        return CelestialShrinePiece::new;
    }

    private ModStructures() {}
}
