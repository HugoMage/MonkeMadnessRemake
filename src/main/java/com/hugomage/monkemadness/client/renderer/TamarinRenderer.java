package com.hugomage.monkemadness.client.renderer;

import com.google.common.collect.Maps;
import com.hugomage.monkemadness.MonkeMadness;
import com.hugomage.monkemadness.client.model.TamarinModel;
import com.hugomage.monkemadness.entities.Tamarin;
import com.hugomage.monkemadness.entities.Tarsier;
import net.minecraft.Util;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class TamarinRenderer extends MobRenderer<Tamarin, TamarinModel<Tamarin>> {
    public static final ResourceLocation TEXTURE = new ResourceLocation("monkemadness:textures/entity/emperor.png");
    public static final ResourceLocation ALBINO = new ResourceLocation("monkemadness:textures/entity/golden.png");

    public TamarinRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new TamarinModel<>(renderManagerIn.bakeLayer(TamarinModel.LAYER_LOCATION)), 0.2F);
        this.shadowRadius = 0.2F;
    }
    @Override
    public ResourceLocation getTextureLocation(Tamarin entity) {
        if(entity.getVariant() == 1){
            return ALBINO;
        }
        return entity.isBaby() ? TEXTURE : TEXTURE;
    }
}
