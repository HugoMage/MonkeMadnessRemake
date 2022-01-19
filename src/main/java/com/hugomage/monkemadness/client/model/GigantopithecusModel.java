package com.hugomage.monkemadness.client.model;

import com.hugomage.monkemadness.MonkeMadness;
import com.hugomage.monkemadness.entities.Gigantopithecus;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class GigantopithecusModel<T extends Gigantopithecus> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MonkeMadness.MOD_ID, "gigantopithecus"), "main");

    private final ModelPart body;
    private final ModelPart leftarm;
    private final ModelPart rightarm;
    private final ModelPart leftleg;
    private final ModelPart rightleg;
    private final ModelPart head;

    public GigantopithecusModel(ModelPart root) {
        this.body = root.getChild("body");
        this.leftarm = root.getChild("leftarm");
        this.rightarm = root.getChild("rightarm");
        this.leftleg = root.getChild("leftleg");
        this.rightleg = root.getChild("rightleg");
        this.head = root.getChild("head");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, -8.0622F));

        PartDefinition torso_r1 = body.addOrReplaceChild("torso_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-11.0F, -13.5F, -7.0F, 22.0F, 27.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

        PartDefinition leftarm = partdefinition.addOrReplaceChild("leftarm", CubeListBuilder.create().texOffs(0, 41).addBox(-7.0F, -4.0F, -4.0F, 7.0F, 31.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-11.0F, -3.0F, -15.0F));

        PartDefinition rightarm = partdefinition.addOrReplaceChild("rightarm", CubeListBuilder.create().texOffs(0, 41).mirror().addBox(0.0F, -4.0F, -4.0F, 7.0F, 31.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(11.0F, -3.0F, -15.0F));

        PartDefinition leftleg = partdefinition.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(72, 0).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 15.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, 9.0F, 0.0F));

        PartDefinition rightleg = partdefinition.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(72, 0).addBox(-4.0F, -1.0F, -4.0F, 8.0F, 15.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, 10.0F, 0.0F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(72, 23).addBox(-5.0F, 0.1F, -11.3F, 10.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 80).addBox(-5.0F, -5.9F, -11.3F, 10.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(62, 41).addBox(-6.0F, -14.9F, -8.3F, 12.0F, 15.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(58, 0).mirror().addBox(6.0F, -8.9F, -8.3F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(58, 0).addBox(-8.0F, -8.9F, -8.3F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.1F, -16.7F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.xRot = headPitch * ((float)Math.PI / 180F);
        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.leftleg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.rightleg.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.rightarm.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * -1.4F * limbSwingAmount;
        this.leftarm.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        int i = entityIn.getAttackAnimationRemainingTicks();
        if (i > 0) {
            float p_212843_4_ = 0;
            this.rightarm.xRot = -2.0F + 1.5F * Mth.triangleWave((float) i - p_212843_4_, 10.0F);
            this.leftarm.xRot = -2.0F + 1.5F * Mth.triangleWave((float) i - p_212843_4_, 10.0F);
        }

    }




    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        head.render(matrixStack, buffer, packedLight, packedOverlay);
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        leftleg.render(matrixStack, buffer, packedLight, packedOverlay);
        rightleg.render(matrixStack, buffer, packedLight, packedOverlay);
        rightarm.render(matrixStack, buffer, packedLight, packedOverlay);
        leftarm.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    @Override
    public ModelPart root() {
     return null;
    }



}






