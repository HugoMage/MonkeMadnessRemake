package com.hugomage.monkemadness.client.renderer;
import com.hugomage.monkemadness.client.model.ProboscisModel;
import com.hugomage.monkemadness.entities.ProboscisMonkey;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ProboscisRenderer extends MobRenderer<ProboscisMonkey, ProboscisModel<ProboscisMonkey>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("monkemadness:textures/entity/proboscis.png");
    public ProboscisRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new ProboscisModel<>(renderManagerIn.bakeLayer(ProboscisModel.LAYER_LOCATION)), 0.2F);
        this.shadowRadius = 0.2F;
    }
    @Override
    public ResourceLocation getTextureLocation(ProboscisMonkey entity) {
        return TEXTURE;
    }
}
