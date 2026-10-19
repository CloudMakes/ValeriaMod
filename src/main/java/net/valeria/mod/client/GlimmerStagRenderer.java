package net.valeria.mod.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.valeria.mod.ValeriaMod;
import net.valeria.mod.entity.GlimmerStagEntity;

public class GlimmerStagRenderer extends MobRenderer<GlimmerStagEntity, GlimmerStagModel> {
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(ValeriaMod.MOD_ID, "textures/entity/glimmer_stag.png");
    private static final ResourceLocation GLOW =
            ResourceLocation.fromNamespaceAndPath(ValeriaMod.MOD_ID, "textures/entity/glimmer_stag_glow.png");

    public GlimmerStagRenderer(EntityRendererProvider.Context context) {
        super(context, new GlimmerStagModel(context.bakeLayer(GlimmerStagModel.LAYER)), 0.5F);
        this.addLayer(new GlowLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(GlimmerStagEntity entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(GlimmerStagEntity entity, PoseStack poseStack, float partialTick) {
        if (entity.isBaby()) {
            poseStack.scale(0.55F, 0.55F, 0.55F);
        }
    }

    /** Full-bright overlay so the antlers glow in the dark. */
    private static final class GlowLayer extends EyesLayer<GlimmerStagEntity, GlimmerStagModel> {
        GlowLayer(RenderLayerParent<GlimmerStagEntity, GlimmerStagModel> parent) {
            super(parent);
        }

        @Override
        public RenderType renderType() {
            return RenderType.eyes(GLOW);
        }
    }
}
