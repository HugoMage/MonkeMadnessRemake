package com.hugomage.monkemadness.client.renderer;

import com.hugomage.monkemadness.MonkeMadness;
import com.hugomage.monkemadness.client.model.UakariModel;
import com.hugomage.monkemadness.entities.Uakari;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class UakariRenderer extends MobRenderer<Uakari, UakariModel<Uakari>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(MonkeMadness.MOD_ID, "textures/entity/uakari.png");
    protected static final ResourceLocation TEXTURE_ALBINO = new ResourceLocation(MonkeMadness.MOD_ID, "textures/entity/albinouakari.png");
   public UakariRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new UakariModel<>(renderManagerIn.bakeLayer(UakariModel.LAYER_LOCATION)), 0.2F);
        this.shadowRadius = 0.2F;
    }
    protected void scale(Uakari entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        if(entitylivingbaseIn.isBaby()){
            matrixStackIn.scale(0.6F, 0.6F, 0.6F);
        }
        else{
        }
    }

    @Override
    public ResourceLocation getTextureLocation(Uakari entity) {
        if(entity.getVariant() == 1){
            return TEXTURE_ALBINO;
        }
        if(entity.getVariant() == 2) {
            return TEXTURE_ALBINO;
        }
        return entity.isBaby() ? TEXTURE : TEXTURE;
    }
}
