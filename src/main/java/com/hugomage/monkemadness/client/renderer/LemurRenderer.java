package com.hugomage.monkemadness.client.renderer;

import com.hugomage.monkemadness.MonkeMadness;
import com.hugomage.monkemadness.client.model.LemurModel;
import com.hugomage.monkemadness.entities.Lemur;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class LemurRenderer extends MobRenderer<Lemur, LemurModel<Lemur>> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(MonkeMadness.MOD_ID, "textures/entity/ring_lemur.png");
    protected static final ResourceLocation JULIEN = new ResourceLocation(MonkeMadness.MOD_ID, "textures/entity/julien.png");
    public LemurRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new LemurModel<>(renderManagerIn.bakeLayer(LemurModel.LAYER_LOCATION)), 0.2F);
        this.shadowRadius = 0.5F;
    }
    @Override
    public ResourceLocation getTextureLocation(Lemur entity) {
        return entity.isJulien() ? JULIEN : TEXTURE;
    }
}
