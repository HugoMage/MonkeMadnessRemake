package com.hugomage.monkemadness.client.model;

import com.google.common.collect.ImmutableList;
import com.hugomage.monkemadness.MonkeMadness;
import com.hugomage.monkemadness.entities.Orangutan;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;


public class OrangutanModel<T extends Orangutan> extends AgeableListModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MonkeMadness.MOD_ID,"orangutan"), "main");
    private final ModelPart headbaby;
    private final ModelPart bodybaby;
    private final ModelPart rightlegbaby;
    private final ModelPart leftlegbaby;
    private final ModelPart leftarmbaby;
    private final ModelPart rightarmbaby;
    private final ModelPart head;
    private final ModelPart leftarm;
    private final ModelPart rightarm;
    private final ModelPart leftleg;
    private final ModelPart rightleg;
    private final ModelPart body;
    public OrangutanModel(ModelPart root) {
        this.headbaby = root.getChild("headbaby");
        this.bodybaby = root.getChild("bodybaby");
        this.rightlegbaby = root.getChild("rightlegbaby");
        this.leftlegbaby = root.getChild("leftlegbaby");
        this.leftarmbaby = root.getChild("leftarmbaby");
        this.rightarmbaby = root.getChild("rightarmbaby");
        this.head = root.getChild("head");
        this.leftarm = root.getChild("leftarm");
        this.rightarm = root.getChild("rightarm");
        this.leftleg = root.getChild("leftleg");
        this.rightleg = root.getChild("rightleg");
        this.body = root.getChild("body");
    }
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition headbaby = partdefinition.addOrReplaceChild("headbaby", CubeListBuilder.create().texOffs(80, 17).addBox(-3.5F, -7.0F, -3.5F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(104, 31).addBox(-5.5F, -9.0F, -0.5F, 11.0F, 9.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(101, 17).addBox(-1.5F, -3.0F, -4.5F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 16.0F, -5.5F));

        PartDefinition bodybaby = partdefinition.addOrReplaceChild("bodybaby", CubeListBuilder.create(), PartPose.offsetAndRotation(0.5F, 16.0F, -1.0F, 0.48F, 0.0F, 0.0F));

        PartDefinition cube_r1 = bodybaby.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(80, 31).addBox(-3.0F, -12.0F, 0.0F, 7.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 9.0F, 0.0F, 0.3491F, 0.0F, 0.0F));

        PartDefinition rightlegbaby = partdefinition.addOrReplaceChild("rightlegbaby", CubeListBuilder.create().texOffs(80, 44).mirror().addBox(-1.5F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.5F, 19.0F, 0.5F));

        PartDefinition leftlegbaby = partdefinition.addOrReplaceChild("leftlegbaby", CubeListBuilder.create().texOffs(80, 44).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, 19.0F, 0.5F));

        PartDefinition leftarmbaby = partdefinition.addOrReplaceChild("leftarmbaby", CubeListBuilder.create().texOffs(101, 41).addBox(0.0F, -2.0F, -2.0F, 2.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 16.0F, -2.0F));

        PartDefinition rightarmbaby = partdefinition.addOrReplaceChild("rightarmbaby", CubeListBuilder.create().texOffs(101, 41).mirror().addBox(-2.0F, -2.0F, -2.0F, 2.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.0F, 16.0F, -3.0F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-7.0F, -4.0F, -8.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 0).addBox(6.0F, -4.0F, -8.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(45, 0).addBox(-2.0F, -2.0F, -10.0F, 5.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 32).addBox(-5.0F, -7.0F, -8.0F, 11.0F, 10.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 5.0F, -7.0F));

        PartDefinition leftarm = partdefinition.addOrReplaceChild("leftarm", CubeListBuilder.create().texOffs(40, 32).addBox(0.0F, -2.0F, -3.0F, 6.0F, 20.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, 6.0F, -4.0F));

        PartDefinition rightarm = partdefinition.addOrReplaceChild("rightarm", CubeListBuilder.create().texOffs(40, 32).mirror().addBox(-6.0F, -3.0F, -3.0F, 6.0F, 20.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-7.0F, 7.0F, -4.0F));

        PartDefinition leftleg = partdefinition.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(0, 51).addBox(-4.0F, 0.0F, -4.0F, 7.0F, 12.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 12.0F, 6.0F));

        PartDefinition rightleg = partdefinition.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(0, 51).mirror().addBox(-3.0F, 0.0F, -4.0F, 7.0F, 12.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-5.0F, 12.0F, 6.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-7.5F, 3.0F, 20.0F, 15.0F, 18.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -25.0F, 0.9599F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }
    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.xRot = headPitch * ((float)Math.PI / 180F);
        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.rightleg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leftleg.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.rightarm.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * -1.4F * limbSwingAmount;
        this.leftarm.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        //baby
        this.headbaby.xRot = headPitch * ((float)Math.PI / 180F);
        this.headbaby.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.rightlegbaby.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leftlegbaby.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.rightarmbaby.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * -1.4F * limbSwingAmount;
        this.leftarmbaby.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        int i = entityIn.getAttackAnimationRemainingTicks();
        if (i > 0) {
            float p_212843_4_ = 0;
            this.rightarm.xRot = -2.0F + 1.5F * Mth.triangleWave((float) i - p_212843_4_, 10.0F);
            this.leftarm.xRot = -2.0F + 1.5F * Mth.triangleWave((float) i - p_212843_4_, 10.0F);
        }
    }
    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        body.render(matrixStack, buffer, packedLight, packedOverlay);
        head.render(matrixStack, buffer, packedLight, packedOverlay);
        leftarm.render(matrixStack, buffer, packedLight, packedOverlay);
        rightarm.render(matrixStack, buffer, packedLight, packedOverlay);
        leftleg.render(matrixStack, buffer, packedLight, packedOverlay);
        rightleg.render(matrixStack, buffer, packedLight, packedOverlay);
        bodybaby.render(matrixStack, buffer, packedLight, packedOverlay);
        headbaby.render(matrixStack, buffer, packedLight, packedOverlay);
        leftarmbaby.render(matrixStack, buffer, packedLight, packedOverlay);
        rightarmbaby.render(matrixStack, buffer, packedLight, packedOverlay);
        leftlegbaby.render(matrixStack, buffer, packedLight, packedOverlay);
        rightlegbaby.render(matrixStack, buffer, packedLight, packedOverlay);
    }
    @Override
    protected @NotNull Iterable<ModelPart> headParts() {
        return ImmutableList.of(this.head);
    }
    @Override
    protected @NotNull Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(this.body, this.rightarm, this.rightleg, this.leftarm, this.leftleg);
    }
}






