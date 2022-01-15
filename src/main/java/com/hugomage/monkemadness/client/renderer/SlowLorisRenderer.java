package com.hugomage.monkemadness.client.renderer;
import com.hugomage.monkemadness.MonkeMadness;
import com.hugomage.monkemadness.client.model.SlowLorisModel;
import com.hugomage.monkemadness.entities.SlowLoris;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SlowLorisRenderer extends MobRenderer<SlowLoris, SlowLorisModel<SlowLoris>> {
     protected static final ResourceLocation TEXTURE = new ResourceLocation("monkemadness:textures/entity/slow_loris.png");
    public SlowLorisRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new SlowLorisModel<>(renderManagerIn.bakeLayer(SlowLorisModel.LAYER_LOCATION)), 0.2F);
        this.shadowRadius = 0.6F;
    }
    @Override
    public ResourceLocation getTextureLocation(SlowLoris entity) {
        return TEXTURE;
    }
}
