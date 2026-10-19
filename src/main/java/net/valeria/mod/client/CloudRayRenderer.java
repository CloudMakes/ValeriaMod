package net.valeria.mod.client;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.valeria.mod.ValeriaMod;
import net.valeria.mod.entity.CloudRayEntity;

public class CloudRayRenderer extends MobRenderer<CloudRayEntity, CloudRayModel> {
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(ValeriaMod.MOD_ID, "textures/entity/cloud_ray.png");

    public CloudRayRenderer(EntityRendererProvider.Context context) {
        super(context, new CloudRayModel(context.bakeLayer(CloudRayModel.LAYER)), 0.6F);
    }

    @Override
    public ResourceLocation getTextureLocation(CloudRayEntity entity) {
        return TEXTURE;
    }
}
