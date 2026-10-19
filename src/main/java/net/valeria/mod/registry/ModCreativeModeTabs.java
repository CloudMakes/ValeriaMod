package net.valeria.mod.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.valeria.mod.ValeriaMod;

public final class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ValeriaMod.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> VALERIA_TAB = CREATIVE_TABS.register("valeria",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.valeria"))
                    .icon(() -> new ItemStack(ModItems.CLOUDS_JEWELS.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.CELESTIAL_STONE.get());
                        output.accept(ModItems.SMOOTH_CELESTIAL_STONE.get());
                        output.accept(ModItems.VALERIAN_TURF.get());
                        output.accept(ModItems.WHISPER_WILLOW_LOG.get());
                        output.accept(ModItems.WHISPER_WILLOW_PLANKS.get());
                        output.accept(ModItems.LUMINESCENT_PETALS.get());
                        output.accept(ModItems.AETHERIUM_CRYSTAL_ORE.get());
                        output.accept(ModItems.AETHERIUM_CRYSTAL.get());
                        output.accept(ModItems.SHIMMERING_VELVET.get());
                        output.accept(ModItems.CLOUDS_JEWELS.get());
                        output.accept(ModItems.GLIMMER_STAG_SPAWN_EGG.get());
                        output.accept(ModItems.CLOUD_RAY_SPAWN_EGG.get());
                    })
                    .build());

    private ModCreativeModeTabs() {}
}
