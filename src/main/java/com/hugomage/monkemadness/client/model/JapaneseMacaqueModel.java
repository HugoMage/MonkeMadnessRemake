package com.hugomage.monkemadness.client.model;

import com.google.common.collect.ImmutableList;
import com.hugomage.monkemadness.MonkeMadness;
import com.hugomage.monkemadness.entities.JapaneseMacaque;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import java.util.Collections;

public class JapaneseMacaqueModel extends AgeableListModel<JapaneseMacaque> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MonkeMadness.MOD_ID, "japanese_macaque"), "main");
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart leftleg;
    private final ModelPart rightleg;
    private final ModelPart leftarm;
    private final ModelPart rightarm;

    public JapaneseMacaqueModel(ModelPart root) {
            this.body = root.getChild("body");
            this.head = root.getChild("head");
            this.leftleg = root.getChild("leftleg");
            this.rightleg = root.getChild("rightleg");
            this.leftarm = root.getChild("leftarm");
            this.rightarm = root.getChild("rightarm");
        }


        public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -7.0F, 8.0F, 7.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 14.0F, 0.0F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 20).addBox(-3.5F, -5.0F, -5.0F, 7.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 13.0F, -4.0F));

        PartDefinition snout = head.addOrReplaceChild("snout", CubeListBuilder.create(), PartPose.offset(0.0F, 11.0F, 4.0F));

        PartDefinition cube_r1 = snout.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(12, 33).addBox(-3.5F, -1.5F, -0.25F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -10.5F, -10.0F, 0.1745F, 0.0F, 0.0F));

        PartDefinition leftleg = partdefinition.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(26, 20).addBox(-1.5F, -2.0F, -2.5F, 3.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 17.0F, 4.0F));

        PartDefinition rightleg = partdefinition.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(29, 0).addBox(-1.5F, -2.0F, -1.5F, 3.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 17.0F, 3.0F));

        PartDefinition leftarm = partdefinition.addOrReplaceChild("leftarm", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -2.0F, -2.0F, 3.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 17.0F, -4.0F));

        PartDefinition rightarm = partdefinition.addOrReplaceChild("rightarm", CubeListBuilder.create().texOffs(0, 33).addBox(-1.5F, -2.0F, -1.0F, 3.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 17.0F, -5.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(JapaneseMacaque entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.xRot = headPitch * ((float)Math.PI / 180F);
        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.rightleg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leftleg.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.rightarm.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.leftarm.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    }


    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        leftleg.render(matrixStack, buffer, packedLight, packedOverlay);
        rightleg.render(matrixStack, buffer, packedLight, packedOverlay);
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        leftarm.render(matrixStack, buffer, packedLight, packedOverlay);
        rightarm.render(matrixStack, buffer, packedLight, packedOverlay);
        head.render(matrixStack, buffer, packedLight, packedOverlay);
    }


    @Override
    protected Iterable<ModelPart> headParts() {
        return Collections.emptyList();
    }

    @Override
    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(body, leftleg, leftarm, rightleg, rightarm, head);
    }
}





