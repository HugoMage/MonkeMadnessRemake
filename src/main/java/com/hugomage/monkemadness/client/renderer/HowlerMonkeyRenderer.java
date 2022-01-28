package com.hugomage.monkemadness.client.renderer;

import com.hugomage.monkemadness.MonkeMadness;
import com.hugomage.monkemadness.client.model.HowlerMonkeyModel;
import com.hugomage.monkemadness.entities.HowlerMonkey;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class HowlerMonkeyRenderer extends MobRenderer<HowlerMonkey, HowlerMonkeyModel<HowlerMonkey>> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(MonkeMadness.MOD_ID, "textures/entity/howler.png");
    protected static final ResourceLocation POG = new ResourceLocation(MonkeMadness.MOD_ID, "textures/entity/howler_pog.png");

    public HowlerMonkeyRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new HowlerMonkeyModel<>(renderManagerIn.bakeLayer(HowlerMonkeyModel.LAYER_LOCATION)), 0.2F);
        this.shadowRadius = 0.2F;
    }
    @Override
    public ResourceLocation getTextureLocation(HowlerMonkey entity) {
        return entity.IsOpenMouthTexture() ? POG : TEXTURE;
    }
}
