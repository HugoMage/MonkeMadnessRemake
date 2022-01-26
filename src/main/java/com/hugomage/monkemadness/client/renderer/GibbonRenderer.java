package com.hugomage.monkemadness.client.renderer;

import com.hugomage.monkemadness.MonkeMadness;
import com.hugomage.monkemadness.client.model.GibbonModel;
import com.hugomage.monkemadness.entities.Gibbon;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class GibbonRenderer extends MobRenderer<Gibbon, GibbonModel<Gibbon>> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(MonkeMadness.MOD_ID, "textures/entity/gibbon.png");
    protected static final ResourceLocation TEXTURE_BLACK = new ResourceLocation(MonkeMadness.MOD_ID, "textures/entity/gibbonblack.png");
    protected static final ResourceLocation TEXTURE_CHEEK = new ResourceLocation(MonkeMadness.MOD_ID, "textures/entity/gibboncheek.png");
    public GibbonRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new GibbonModel<>(renderManagerIn.bakeLayer(GibbonModel.LAYER_LOCATION)), 0.2F);
        this.shadowRadius = 0.2F;
    }
    @Override
    public ResourceLocation getTextureLocation(Gibbon entity) {
        if(entity.getVariant() == 1){
            return TEXTURE_BLACK;
        }
        if(entity.getVariant() == 2) {
            return TEXTURE_CHEEK;
        }
        return TEXTURE;
    }
}
