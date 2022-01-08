package com.hugomage.monkemadness.client.renderer;

import com.hugomage.monkemadness.MonkeMadness;
import com.hugomage.monkemadness.client.model.OrangutanModel;
import com.hugomage.monkemadness.entities.Orangutan;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;


public class OrangutanRenderer extends MobRenderer<Orangutan, OrangutanModel<Orangutan>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("monkemadness:textures/entity/orangutan.png");
    private static final ResourceLocation FEM = new ResourceLocation("monkemadness:textures/entity/fem_orangutan.png");
    private static final ResourceLocation FEM_ALBINO = new ResourceLocation("monkemadness:textures/entity/albino_fem_orangutan.png");
    private static final ResourceLocation TEXTURE_ALBN = new ResourceLocation("monkemadness:textures/entity/albino_orangutan.png");

    public OrangutanRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new OrangutanModel<>(renderManagerIn.bakeLayer(OrangutanModel.LAYER_LOCATION)), 0.2F);
        this.shadowRadius = 1.0F;
    }
    protected void scale(Orangutan entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        if(entitylivingbaseIn.isBaby()){
            matrixStackIn.scale(0.7F, 0.7F, 0.7F);
        }
        else{
            matrixStackIn.scale(1.0F, 1.0F, 1.0F);
        }
    }
    @Override
    public @NotNull ResourceLocation getTextureLocation(Orangutan entity) {
        if(entity.getVariant() == 1){
            return TEXTURE;
        }
        if(entity.getVariant() == 2) {
            return entity.isBaby() ? TEXTURE_ALBN : FEM_ALBINO;
        }
        if(entity.getVariant() == 3){
            return entity.isBaby() ? FEM_ALBINO : FEM_ALBINO;
        }
        return entity.isBaby() ? FEM : FEM;
    }

}
