package com.hugomage.monkemadness.client.renderer;

import com.hugomage.monkemadness.MonkeMadness;
import com.hugomage.monkemadness.client.model.BaleMonkeyModel;
import com.hugomage.monkemadness.entities.BaleMonkey;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;


public class BaleMonkeyRenderer extends MobRenderer<BaleMonkey, BaleMonkeyModel<BaleMonkey>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("monkemadness:textures/entity/bale_monkey.png");
    private static final ResourceLocation SHEARED = new ResourceLocation("monkemadness:textures/entity/no_must_bale_monkey.png");
    public BaleMonkeyRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new BaleMonkeyModel<>(renderManagerIn.bakeLayer(BaleMonkeyModel.LAYER_LOCATION)), 0.2F);
        this.shadowRadius = 0.6F;
    }
    @Override
    public ResourceLocation getTextureLocation(BaleMonkey entity) {
        return entity.isSheared() ? SHEARED : TEXTURE;
    }
}
