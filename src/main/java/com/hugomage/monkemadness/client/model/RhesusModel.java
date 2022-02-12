package com.hugomage.monkemadness.client.model;

import com.hugomage.monkemadness.MonkeMadness;
import com.hugomage.monkemadness.entities.Rhesus;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;

public class RhesusModel<T extends Rhesus> extends EntityModel<T> implements ArmedModel {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MonkeMadness.MOD_ID,"rhesus_macaque"), "main");

    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart leftleg;
    private final ModelPart rightleg;
    private final ModelPart leftarm;
    private final ModelPart rightarm;
    private final ModelPart bodybaby;
    private final ModelPart headbaby;
    private final ModelPart leftarmbaby;
    private final ModelPart rightarmbaby;
    private final ModelPart leftlegbaby;
    private final ModelPart rightlegbaby;

    public RhesusModel(ModelPart root) {
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.leftleg = root.getChild("leftleg");
        this.rightleg = root.getChild("rightleg");
        this.leftarm = root.getChild("leftarm");
        this.rightarm = root.getChild("rightarm");
        this.bodybaby = root.getChild("bodybaby");
        this.headbaby = root.getChild("headbaby");
        this.leftarmbaby = root.getChild("leftarmbaby");
        this.rightarmbaby = root.getChild("rightarmbaby");
        this.leftlegbaby = root.getChild("leftlegbaby");
        this.rightlegbaby = root.getChild("rightlegbaby");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(17, 0).addBox(-2.0F, -3.0F, -3.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(6, 0).addBox(2.0F, -2.0F, -1.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-3.0F, -2.0F, -1.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(12, 22).addBox(-1.0F, -0.5F, -4.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 18.0F, -5.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -2.0F, -5.0F, 4.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 19.0F, 0.0F));

        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 13).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -1.0F, 4.0F, 0.7418F, 0.0F, 0.0F));

        PartDefinition leftleg = partdefinition.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(24, 11).addBox(-0.5F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 20.0F, 3.0F));

        PartDefinition rightleg = partdefinition.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(26, 26).addBox(-1.5F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 20.0F, 3.0F));

        PartDefinition leftarm = partdefinition.addOrReplaceChild("leftarm", CubeListBuilder.create().texOffs(0, 13).addBox(-0.5F, -1.0F, -0.5F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 19.0F, -4.0F));

        PartDefinition rightarm = partdefinition.addOrReplaceChild("rightarm", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -1.0F, -0.5F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 19.0F, -4.0F));

        PartDefinition bodybaby = partdefinition.addOrReplaceChild("bodybaby", CubeListBuilder.create().texOffs(12, 13).addBox(-1.5F, -1.0F, -3.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 20.0F, -1.0F));

        PartDefinition babytail = bodybaby.addOrReplaceChild("babytail", CubeListBuilder.create().texOffs(14, 21).addBox(-0.5F, -0.1874F, -0.8452F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 3.0F, 0.4363F, 0.0F, 0.0F));

        PartDefinition headbaby = partdefinition.addOrReplaceChild("headbaby", CubeListBuilder.create().texOffs(0, 22).addBox(-1.5F, -2.0F, -3.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(12, 13).addBox(-0.5F, -0.5F, -3.5F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(2, 8).addBox(-2.5F, -1.0F, -1.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(0, 8).addBox(1.5F, -1.0F, -1.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 19.0F, -3.0F));

        PartDefinition leftarmbaby = partdefinition.addOrReplaceChild("leftarmbaby", CubeListBuilder.create().texOffs(0, 28).addBox(1.0F, -1.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, -3.0F));

        PartDefinition rightarmbaby = partdefinition.addOrReplaceChild("rightarmbaby", CubeListBuilder.create().texOffs(11, 27).addBox(-2.0F, -1.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 21.0F, -3.0F));

        PartDefinition leftlegbaby = partdefinition.addOrReplaceChild("leftlegbaby", CubeListBuilder.create().texOffs(22, 21).addBox(0.0F, -1.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 21.0F, 2.0F));

        PartDefinition rightlegbaby = partdefinition.addOrReplaceChild("rightlegbaby", CubeListBuilder.create().texOffs(26, 21).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, 2.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }
    @Override
    public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        head.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        leftleg.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        rightleg.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        leftarm.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        rightarm.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);

        bodybaby.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        headbaby.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        leftlegbaby.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        rightlegbaby.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        leftarmbaby.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        rightarmbaby.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }


    public void translateToHand(HumanoidArm sideIn, PoseStack matrixStackIn) {
        this.getArm(sideIn).translateAndRotate(matrixStackIn);
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.xRot = headPitch * ((float)Math.PI / 180F);
        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.rightleg.xRot = Mth.cos(limbSwing * 0.6662F) * -1.4F * limbSwingAmount;
        this.leftleg.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.rightarm.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * -1.4F * limbSwingAmount;
        this.leftarm.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

    }
    public ModelPart getArm(HumanoidArm p_1912161)
    {
        return p_1912161 == HumanoidArm.LEFT ? this.leftarm : this.rightarm;
    }
    public ModelPart getRhesusArm() {
        return this.leftarm;
    }
}
