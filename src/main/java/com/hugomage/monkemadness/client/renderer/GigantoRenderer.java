package com.hugomage.monkemadness.client.renderer;

import com.hugomage.monkemadness.MonkeMadness;
import com.hugomage.monkemadness.client.model.GigantopithecusModel;
import com.hugomage.monkemadness.entities.Gigantopithecus;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class GigantoRenderer extends MobRenderer<Gigantopithecus, GigantopithecusModel<Gigantopithecus>> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(MonkeMadness.MOD_ID, "textures/entity/gigantopithecus.png");
     public GigantoRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new GigantopithecusModel<>(renderManagerIn.bakeLayer(GigantopithecusModel.LAYER_LOCATION)), 0.2F);
        this.shadowRadius = 0.2F;
    }
    protected void scale(Gigantopithecus entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
       {
            matrixStackIn.scale(1.4F, 1.4F, 1.4F);
        }
    }
    @Override
    public ResourceLocation getTextureLocation(Gigantopithecus entity) {
        return entity.isBaby() ? TEXTURE : TEXTURE;
    }
    protected void setupRotations(Gigantopithecus entityLiving, PoseStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.setupRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        if (!((double)entityLiving.animationSpeedOld < 0.01D)) {
            float f = 13.0F;
            float f1 = entityLiving.animationPosition - entityLiving.animationSpeedOld * (1.0F - partialTicks) + 6.0F;
            float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
            matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(6.5F * f2));
        }
    }
}
