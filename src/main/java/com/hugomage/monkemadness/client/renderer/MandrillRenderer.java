package com.hugomage.monkemadness.client.renderer;
import com.hugomage.monkemadness.client.model.MandrillModel;
import com.hugomage.monkemadness.client.model.SlowLorisModel;
import com.hugomage.monkemadness.entities.Mandrill;
import com.hugomage.monkemadness.entities.SlowLoris;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MandrillRenderer extends MobRenderer<Mandrill, MandrillModel<Mandrill>> {
     protected static final ResourceLocation TEXTURE = new ResourceLocation("monkemadness:textures/entity/mandrill.png");
    public MandrillRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new MandrillModel<>(renderManagerIn.bakeLayer(MandrillModel.LAYER_LOCATION)), 0.2F);
        this.shadowRadius = 0.6F;
    }
    @Override
    public ResourceLocation getTextureLocation(Mandrill entity) {
        return TEXTURE;
    }
}
