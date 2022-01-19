package com.hugomage.monkemadness.client.renderer;
import com.hugomage.monkemadness.MonkeMadness;
import com.hugomage.monkemadness.client.model.JapaneseMacaqueModel;
import com.hugomage.monkemadness.entities.Gigantopithecus;
import com.hugomage.monkemadness.entities.JapaneseMacaque;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
public class JapaneseMacaqueRenderer extends MobRenderer<JapaneseMacaque, JapaneseMacaqueModel>{
    protected static final ResourceLocation TEXTURE = new ResourceLocation(MonkeMadness.MOD_ID, "textures/entity/japan.png");
    public JapaneseMacaqueRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new JapaneseMacaqueModel(renderManagerIn.bakeLayer(JapaneseMacaqueModel.LAYER_LOCATION)), 0.2F);
        this.shadowRadius = 0.2F;
    }
    protected void scale(JapaneseMacaque entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        if(entitylivingbaseIn.isBaby()){
            matrixStackIn.scale(0.6F, 0.6F, 0.6F);
        }
    }
    @Override
    public ResourceLocation getTextureLocation(JapaneseMacaque entity) {
        return entity.isBaby() ? TEXTURE : TEXTURE;
    }
}
