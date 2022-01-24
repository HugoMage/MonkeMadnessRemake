package com.hugomage.monkemadness.client.model;

import com.hugomage.monkemadness.MonkeMadness;
import com.hugomage.monkemadness.entities.CrestedMacaque;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;


public class CrestedModel<T extends CrestedMacaque> extends EntityModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MonkeMadness.MOD_ID, "crested_macaque"), "main");
    private final ModelPart leftarm;
    private final ModelPart rightarm;
    private final ModelPart leftleg;
    private final ModelPart rightleg;
    private final ModelPart head;
    private final ModelPart body;
    public CrestedModel(ModelPart root) {
        this.leftarm = root.getChild("leftarm");
        this.rightarm = root.getChild("rightarm");
        this.leftleg = root.getChild("leftleg");
        this.rightleg = root.getChild("rightleg");
        this.head = root.getChild("head");
        this.body = root.getChild("body");
    }



    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition leftarm = partdefinition.addOrReplaceChild("leftarm", CubeListBuilder.create().texOffs(25, 0).addBox(-1.0F, -1.0F, -1.5F, 2.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(2.25F, 15.0F, -4.5F));

        PartDefinition rightarm = partdefinition.addOrReplaceChild("rightarm", CubeListBuilder.create().texOffs(28, 19).addBox(-1.0F, -1.0F, -1.5F, 2.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.25F, 15.0F, -4.5F));

        PartDefinition leftleg = partdefinition.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(18, 19).addBox(-1.0F, -1.0F, -1.5F, 2.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(2.25F, 15.0F, 4.4F));

        PartDefinition rightleg = partdefinition.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -1.5F, 2.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.25F, 15.0F, 4.4F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 13.0F, -7.0F));

        PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 29).addBox(-1.5F, -1.5F, -0.75F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.7004F, 0.0116F, -1.1781F, 0.0F, 0.0F));

        PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(32, 0).addBox(-1.5F, 0.9591F, 0.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.3091F, -0.3192F, -1.1781F, 0.0F, 0.0F));

        PartDefinition cube_r3 = head.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(10, 30).addBox(-1.5F, -2.25F, 0.2F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, -2.25F, -0.1309F, 0.0F, 0.0F));

        PartDefinition cube_r4 = head.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 19).addBox(-2.5F, -3.0F, -2.0F, 5.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.25F, 0.0873F, 0.0F, 0.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 14.0F, -1.0F));

        PartDefinition cube_r5 = body.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -3.0F, -6.5F, 6.0F, 6.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.0019F, 0.4564F, -0.0436F, 0.0F, 0.0F));

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
}

