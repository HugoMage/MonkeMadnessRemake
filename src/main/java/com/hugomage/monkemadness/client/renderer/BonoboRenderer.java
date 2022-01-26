package com.hugomage.monkemadness.client.renderer;
import com.hugomage.monkemadness.MonkeMadness;
import com.hugomage.monkemadness.client.model.BonoboModel;
import com.hugomage.monkemadness.entities.Bonobo;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BonoboRenderer extends MobRenderer<Bonobo, BonoboModel<Bonobo>> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(MonkeMadness.MOD_ID, "textures/entity/bonobo.png");
    protected static final ResourceLocation BEBE = new ResourceLocation(MonkeMadness.MOD_ID, "textures/entity/bebe_bonobo.png");
    public BonoboRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new BonoboModel<>(renderManagerIn.bakeLayer(BonoboModel.LAYER_LOCATION)), 0.2F);
        this.shadowRadius = 0.6F;
    }
    @Override
    public ResourceLocation getTextureLocation(Bonobo entity) {
        return entity.isBaby() ? BEBE : TEXTURE;
    }
}

