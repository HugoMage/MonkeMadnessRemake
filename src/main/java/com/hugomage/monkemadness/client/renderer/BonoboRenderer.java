package com.hugomage.monkemadness.client.renderer;

import com.hugomage.monkemadness.MonkeMadness;
import com.hugomage.monkemadness.client.model.BonoboModel;
import com.hugomage.monkemadness.entities.Bonobo;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import javax.swing.text.html.parser.Entity;

public class BonoboRenderer extends MobRenderer<Bonobo, BonoboModel<Bonobo>> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(MonkeMadness.MOD_ID, "textures/entity/bonobo.png");


    public BonoboRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new BonoboModel<>(renderManagerIn.bakeLayer(BonoboModel.LAYER_LOCATION)), 0.2F);
        this.shadowRadius = 0.8F;
    }

    @Override
    public ResourceLocation getTextureLocation(Bonobo entity) {
        return TEXTURE;
    }
}

