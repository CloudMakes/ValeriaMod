package net.valeria.mod.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.valeria.mod.ValeriaMod;
import net.valeria.mod.entity.CloudRayEntity;

public class CloudRayModel extends EntityModel<CloudRayEntity> {
    public static final ModelLayerLocation LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(ValeriaMod.MOD_ID, "cloud_ray"), "main");

    private final ModelPart root;
    private final ModelPart wingLeft;
    private final ModelPart wingRight;
    private final ModelPart tail;

    public CloudRayModel(ModelPart root) {
        this.root = root;
        this.wingLeft = root.getChild("wing_l");
        this.wingRight = root.getChild("wing_r");
        this.tail = root.getChild("tail");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition rootPart = mesh.getRoot();
        rootPart.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0)
                .addBox(-5.0F, -1.0F, -7.0F, 10.0F, 2.0F, 14.0F), PartPose.offset(0.0F, 20.0F, 0.0F));
        rootPart.addOrReplaceChild("wing_l", CubeListBuilder.create().texOffs(0, 18)
                .addBox(0.0F, -0.5F, -6.0F, 12.0F, 1.0F, 12.0F), PartPose.offset(5.0F, 20.0F, 0.0F));
        rootPart.addOrReplaceChild("wing_r", CubeListBuilder.create().texOffs(0, 18)
                .addBox(-12.0F, -0.5F, -6.0F, 12.0F, 1.0F, 12.0F), PartPose.offset(-5.0F, 20.0F, 0.0F));
        rootPart.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 46)
                .addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 12.0F), PartPose.offset(0.0F, 20.0F, 7.0F));
        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void setupAnim(CloudRayEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float flap = Mth.sin(ageInTicks * 0.15F) * 0.35F;
        this.wingLeft.zRot = -flap;
        this.wingRight.zRot = flap;
        this.tail.yRot = Mth.sin(ageInTicks * 0.1F) * 0.25F;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
        this.root.render(poseStack, buffer, packedLight, packedOverlay, color);
    }
}
