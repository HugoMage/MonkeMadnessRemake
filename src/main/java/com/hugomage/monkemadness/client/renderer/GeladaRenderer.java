package com.hugomage.monkemadness.client.renderer;
import com.hugomage.monkemadness.MonkeMadness;
import com.hugomage.monkemadness.client.model.GeladaModel;
import com.hugomage.monkemadness.entities.Gelada;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class GeladaRenderer extends MobRenderer<Gelada, EntityModel<Gelada>> {
   protected static final ResourceLocation TEXTURE = new ResourceLocation(MonkeMadness.MOD_ID, "textures/entity/gelada.png");
    public GeladaRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new GeladaModel<>(renderManagerIn.bakeLayer(GeladaModel.LAYER_LOCATION)), 0.2F);
        this.shadowRadius = 0.2F;
    }
    @Override
    public ResourceLocation getTextureLocation(Gelada entity) {
        return entity.isBaby() ? TEXTURE : TEXTURE;
    }
}
