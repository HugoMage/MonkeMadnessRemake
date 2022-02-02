package com.hugomage.monkemadness.client.renderer;
import com.hugomage.monkemadness.MonkeMadness;
import com.hugomage.monkemadness.client.model.BushBabyModel;
import com.hugomage.monkemadness.entities.BushBaby;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
public class BushBabyRenderer extends MobRenderer<BushBaby, BushBabyModel<BushBaby>> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(MonkeMadness.MOD_ID, "textures/entity/baby_bush.png");
    public BushBabyRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new BushBabyModel<>(renderManagerIn.bakeLayer(BushBabyModel.LAYER_LOCATION)), 0.2F);
        this.shadowRadius = 0.2F;
    }
    @Override
    public ResourceLocation getTextureLocation(BushBaby entity) {
        return TEXTURE;
    }
}
