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
import net.valeria.mod.entity.GlimmerStagEntity;

/** UV layout (64x64) must match tools used to paint glimmer_stag.png / glimmer_stag_glow.png. */
public class GlimmerStagModel extends EntityModel<GlimmerStagEntity> {
    public static final ModelLayerLocation LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(ValeriaMod.MOD_ID, "glimmer_stag"), "main");

    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart legFrontLeft;
    private final ModelPart legFrontRight;
    private final ModelPart legBackLeft;
    private final ModelPart legBackRight;

    public GlimmerStagModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild("head");
        this.legFrontLeft = root.getChild("leg_fl");
        this.legFrontRight = root.getChild("leg_fr");
        this.legBackLeft = root.getChild("leg_bl");
        this.legBackRight = root.getChild("leg_br");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition rootPart = mesh.getRoot();

        rootPart.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0)
                .addBox(-4.0F, -4.0F, -7.0F, 8.0F, 8.0F, 14.0F), PartPose.offset(0.0F, 11.0F, 0.0F));

        PartDefinition headPart = rootPart.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 24)
                .addBox(-3.0F, -3.0F, -6.0F, 6.0F, 6.0F, 6.0F), PartPose.offset(0.0F, 6.0F, -7.0F));

        headPart.addOrReplaceChild("antlers", CubeListBuilder.create()
                .texOffs(48, 24).addBox(1.5F, -11.0F, -3.0F, 1.0F, 8.0F, 1.0F)
                .texOffs(48, 40).addBox(2.5F, -9.0F, -3.0F, 3.0F, 1.0F, 1.0F)
                .texOffs(48, 24).addBox(-2.5F, -11.0F, -3.0F, 1.0F, 8.0F, 1.0F)
                .texOffs(48, 40).addBox(-5.5F, -9.0F, -3.0F, 3.0F, 1.0F, 1.0F), PartPose.ZERO);

        CubeListBuilder leg = CubeListBuilder.create().texOffs(0, 38).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 9.0F, 3.0F);
        rootPart.addOrReplaceChild("leg_fl", leg, PartPose.offset(2.5F, 15.0F, -5.0F));
        rootPart.addOrReplaceChild("leg_fr", leg, PartPose.offset(-2.5F, 15.0F, -5.0F));
        rootPart.addOrReplaceChild("leg_bl", leg, PartPose.offset(2.5F, 15.0F, 5.0F));
        rootPart.addOrReplaceChild("leg_br", leg, PartPose.offset(-2.5F, 15.0F, 5.0F));

        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void setupAnim(GlimmerStagEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.yRot = netHeadYaw * Mth.DEG_TO_RAD;
        this.head.xRot = headPitch * Mth.DEG_TO_RAD;
        this.legFrontLeft.xRot = Mth.cos(limbSwing * 0.6662F) * 1.2F * limbSwingAmount;
        this.legBackRight.xRot = this.legFrontLeft.xRot;
        this.legFrontRight.xRot = Mth.cos(limbSwing * 0.6662F + Mth.PI) * 1.2F * limbSwingAmount;
        this.legBackLeft.xRot = this.legFrontRight.xRot;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
        this.root.render(poseStack, buffer, packedLight, packedOverlay, color);
    }
}
