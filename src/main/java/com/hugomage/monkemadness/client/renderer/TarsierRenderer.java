package com.hugomage.monkemadness.client.renderer;

import com.hugomage.monkemadness.MonkeMadness;
import com.hugomage.monkemadness.client.model.TarsierModel;
import com.hugomage.monkemadness.client.renderer.layer.TarsierEyes;
import com.hugomage.monkemadness.entities.Tarsier;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class TarsierRenderer extends MobRenderer<Tarsier, TarsierModel<Tarsier>> {
    public static final ModelLayerLocation MODEL_LAYER = new ModelLayerLocation(new ResourceLocation(MonkeMadness.MOD_ID, "primate"), "main");
    private static final ResourceLocation TEXTURE = new ResourceLocation("monkemadness:textures/entity/tarsier.png");
    private static final ResourceLocation ALBINO = new ResourceLocation("monkemadness:textures/entity/albino_tarsier.png");


    public TarsierRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new TarsierModel<>(renderManagerIn.bakeLayer(TarsierModel.LAYER_LOCATION)), 0.2F);
        this.shadowRadius = 0.2F;
        this.addLayer(new TarsierEyes<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(Tarsier entity) {
        if(entity.getVariant() == 1){
            return ALBINO;
        }
        return entity.isBaby() ? TEXTURE : TEXTURE;
    }}
