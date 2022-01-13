package com.hugomage.monkemadness.client.renderer;

import com.hugomage.monkemadness.client.model.ZombieApeModel;
import com.hugomage.monkemadness.client.renderer.layer.SnubNosedMonkeyHeldItemLayer;
import com.hugomage.monkemadness.client.renderer.layer.ZombieApeHeldItemLayer;
import com.hugomage.monkemadness.entities.ZombieApe;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ZombieApeRenderer extends MobRenderer<ZombieApe, ZombieApeModel<ZombieApe>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation("monkemadness:textures/entity/zombie_ape.png");

    public ZombieApeRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new ZombieApeModel<>(renderManagerIn.bakeLayer(ZombieApeModel.LAYER_LOCATION)), 0.2F);
        this.shadowRadius = 0.8F;
        this.addLayer(new ZombieApeHeldItemLayer(this));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull ZombieApe entity) {
        return TEXTURE;
    }
}

