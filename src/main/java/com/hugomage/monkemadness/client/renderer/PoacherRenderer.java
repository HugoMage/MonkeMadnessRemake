package com.hugomage.monkemadness.client.renderer;

import com.hugomage.monkemadness.MonkeMadness;
import com.hugomage.monkemadness.client.model.PoacherModel;
import com.hugomage.monkemadness.entities.Poacher;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;

public class PoacherRenderer<T extends Poacher> extends MobRenderer<T, PoacherModel<T>> {
   protected static final ResourceLocation TEXTURE = new ResourceLocation(MonkeMadness.MOD_ID, "textures/entity/poacher.png");
    public PoacherRenderer(EntityRendererProvider.Context rendererManagerIn){
        super(rendererManagerIn, new PoacherModel<>(rendererManagerIn.bakeLayer(PoacherModel.LAYER_LOCATION)), 0.2F);
        this.shadowRadius = 0.2F;
        this.addLayer(new ItemInHandLayer<>(this));
    }
    @Override
    public ResourceLocation getTextureLocation(Poacher entity) {
        return TEXTURE;
    }
}
