package com.hugomage.monkemadness.client.model;

import com.hugomage.monkemadness.MonkeMadness;
import com.hugomage.monkemadness.entities.BaleMonkey;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class BaleMonkeyModel<T extends BaleMonkey> extends EntityModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MonkeMadness.MOD_ID, "bale_monkey"), "main");
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart leg1;
    private final ModelPart leg2;
    private final ModelPart leg3;
    private final ModelPart leg4;
    private final ModelPart tail;


    public BaleMonkeyModel(ModelPart root) {
        this.body = root.getChild("body");
        this.head = root.getChild("head");
        this.leg1 = root.getChild("leg1");
        this.leg2 = root.getChild("leg2");
        this.leg3 = root.getChild("leg3");
        this.leg4 = root.getChild("leg4");
        this.tail = root.getChild("tail");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -5.0F, -7.0F, 6.0F, 5.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(27, 0).addBox(-2.0F, -4.0F, -4.0F, 4.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(5, 20).addBox(2.0F, -3.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 20).addBox(-3.0F, -3.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 13.0F, -6.5F, 0.1309F, 0.0F, 0.0F));

        PartDefinition mustaches = head.addOrReplaceChild("mustaches", CubeListBuilder.create().texOffs(41, 0).addBox(-2.0F, 0.5F, 0.1F, 4.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, -4.0F, -0.1309F, 0.0F, 0.0F));

        PartDefinition bone = head.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -0.25F, -4.75F, -0.3054F, 0.0F, 0.0F));

        PartDefinition leg1 = partdefinition.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(22, 36).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 14.0F, -4.5F));

        PartDefinition leg2 = partdefinition.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(11, 36).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 14.0F, -4.5F));

        PartDefinition leg3 = partdefinition.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(0, 33).addBox(-1.0F, 2.0F, -2.0F, 2.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(2.75F, 14.0F, 5.5F));

        PartDefinition leg4 = partdefinition.addOrReplaceChild("leg4", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 2.0F, -2.0F, 2.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.75F, 14.0F, 5.5F));

        PartDefinition tail = partdefinition.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(15, 23).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 14.0F, 7.0F, -0.7417F, 0.0F, 0.0F));

        PartDefinition tail2 = tail.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(0, 20).addBox(-1.0F, -1.5981F, -0.5F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.6451F, 8.5045F, 0.6545F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }


    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.xRot = headPitch * ((float)Math.PI / 180F);
        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.leg4.xRot = Mth.cos(limbSwing * 0.6662F) * -1.4F * limbSwingAmount;
        this.leg3.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.leg2.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * -1.4F * limbSwingAmount;
        this.leg1.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.tail.yRot = Mth.cos(limbSwing * 0.6662F) * 0.3F * limbSwingAmount;
    }


    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        head.render(matrixStack, buffer, packedLight, packedOverlay);
        leg1.render(matrixStack, buffer, packedLight, packedOverlay);
        leg2.render(matrixStack, buffer, packedLight, packedOverlay);
        leg3.render(matrixStack, buffer, packedLight, packedOverlay);
        leg4.render(matrixStack, buffer, packedLight, packedOverlay);
        tail.render(matrixStack, buffer, packedLight, packedOverlay);
    }

}