package com.hugomage.monkemadness.client.renderer;
import com.hugomage.monkemadness.MonkeMadness;
import com.hugomage.monkemadness.client.model.RhesusModel;
import com.hugomage.monkemadness.client.renderer.layer.RhesusHeldItemLayer;
import com.hugomage.monkemadness.entities.Rhesus;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RhesusRenderer extends MobRenderer<Rhesus, RhesusModel<Rhesus>> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(MonkeMadness.MOD_ID, "textures/entity/rhesus.png");
    protected static final ResourceLocation ALBINO = new ResourceLocation(MonkeMadness.MOD_ID, "textures/entity/rhesus_albino.png");


    public RhesusRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new RhesusModel<>(renderManagerIn.bakeLayer(RhesusModel.LAYER_LOCATION)), 0.2F);
        this.shadowRadius = 0.2F;
        this.addLayer(new RhesusHeldItemLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(Rhesus entity) {
        if(entity.getVariant() == 1){
            return ALBINO;
        }
        if(entity.getVariant() == 2) {
            return ALBINO;
        }
        return entity.isBaby() ? TEXTURE : TEXTURE;
    }
}
