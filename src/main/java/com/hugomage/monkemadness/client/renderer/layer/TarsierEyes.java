package com.hugomage.monkemadness.client.renderer.layer;

import com.hugomage.monkemadness.MonkeMadness;
import com.hugomage.monkemadness.entities.Tarsier;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;

public class TarsierEyes<T extends Tarsier, M extends EntityModel<T>> extends EyesLayer<T, M> {
    private static final RenderType TARSIER_EYES = RenderType.eyes(new ResourceLocation(MonkeMadness.MOD_ID, "textures/entity/tarsier_eyes.png"));
    public TarsierEyes(RenderLayerParent<T, M> rendererIn) {
        super(rendererIn);
    }
    public RenderType renderType() {
        return TARSIER_EYES;
    }
}
