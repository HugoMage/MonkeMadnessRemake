package com.hugomage.monkemadness.client.model;

import com.hugomage.monkemadness.MonkeMadness;
import com.hugomage.monkemadness.entities.ZombieApe;
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

public class ZombieApeModel<T extends ZombieApe> extends EntityModel<T> implements ArmedModel {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MonkeMadness.MOD_ID,"zombie_ape"), "main");

    private final ModelPart head;
    private final ModelPart leftleg;
    private final ModelPart leftarm;
    private final ModelPart rightleg;
    private final ModelPart rightarm;
    private final ModelPart body;


    public ZombieApeModel(ModelPart root) {
        this.leftleg = root.getChild("leftleg");
        this.rightleg = root.getChild("rightleg");
        this.body = root.getChild("body");
        this.rightarm = root.getChild("rightarm");
        this.leftarm = root.getChild("leftarm");
        this.head = root.getChild("head");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition leftleg = partdefinition.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(0, 35).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.25F, 15.2F, 1.5F));

        PartDefinition rightleg = partdefinition.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(40, 21).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.25F, 15.2F, 1.5F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -5.4508F, -3.5355F, 8.0F, 12.0F, 7.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 11.0F, -2.0F, 0.7854F, 0.0F, 0.0F));

        PartDefinition rightarm = partdefinition.addOrReplaceChild("rightarm", CubeListBuilder.create().texOffs(28, 31).addBox(-4.0F, -1.7F, -2.0F, 4.0F, 17.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 8.7F, -4.0F));

        PartDefinition leftarm = partdefinition.addOrReplaceChild("leftarm", CubeListBuilder.create().texOffs(30, 0).addBox(0.0F, -1.7F, -2.0F, 4.0F, 17.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 8.7F, -4.0F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 19).addBox(-4.0F, -8.0F, -6.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.7F, -6.0F));

        PartDefinition ear = head.addOrReplaceChild("ear", CubeListBuilder.create().texOffs(24, 21).addBox(-3.0F, -2.0F, 0.0F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -4.0F, -2.0F));

        PartDefinition ear2 = head.addOrReplaceChild("ear2", CubeListBuilder.create().texOffs(0, 19).addBox(0.0F, -2.0F, 0.0F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -4.0F, -2.0F));

        PartDefinition mouth = head.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(44, 34).addBox(-2.0F, -2.0F, -3.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -6.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public ModelPart getArm(HumanoidArm p_1912161)
    {
        return p_1912161 == HumanoidArm.LEFT ? this.leftarm : this.rightarm;
    }

    public void translateToHand(HumanoidArm sideIn, PoseStack matrixStackIn) {
        this.getArm(sideIn).translateAndRotate(matrixStackIn);
    }
    public ModelPart getZombieApeArm() {
        return this.leftarm;
    }
    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.xRot = headPitch * ((float)Math.PI / 180F);
        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.rightleg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leftleg.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.rightarm.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.leftarm.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        int i = entityIn.getAttackAnimationRemainingTicks();
        if (i > 0) {
            float p_212843_4_ = 0;
            this.rightarm.xRot = -2.0F + 1.5F * Mth.triangleWave((float) i - p_212843_4_, 10.0F);
            this.leftarm.xRot = -2.0F + 1.5F * Mth.triangleWave((float) i - p_212843_4_, 10.0F);
        }
    }
    @Override
    public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        head.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        leftleg.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        rightleg.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        leftarm.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        rightarm.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }

}