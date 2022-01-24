package com.hugomage.monkemadness.client.renderer;

import com.hugomage.monkemadness.MonkeMadness;
import com.hugomage.monkemadness.client.model.CrestedModel;
import com.hugomage.monkemadness.entities.CrestedMacaque;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CrestedRenderer extends MobRenderer<CrestedMacaque, CrestedModel<CrestedMacaque>> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(MonkeMadness.MOD_ID, "textures/entity/crested.png");
    public CrestedRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new CrestedModel<>(renderManagerIn.bakeLayer(CrestedModel.LAYER_LOCATION)), 0.2F);
        this.shadowRadius = 0.2F;
    }
    protected void scale(CrestedMacaque entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        if(entitylivingbaseIn.isBaby()){
            matrixStackIn.scale(0.7F, 0.7F, 0.7F);
        }
        else{
            matrixStackIn.scale(1.3F, 1.3F, 1.3F);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(CrestedMacaque entity) {
        return entity.isBaby() ? TEXTURE : TEXTURE;
    }
}
