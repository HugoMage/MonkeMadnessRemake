package com.hugomage.monkemadness.client.renderer;

import com.hugomage.monkemadness.MonkeMadness;
import com.hugomage.monkemadness.client.model.SnubNosedMonkeyModel;
import com.hugomage.monkemadness.client.renderer.layer.SnubNosedMonkeyHeldItemLayer;
import com.hugomage.monkemadness.entities.SnubNosedMonkey;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class GoldenSnubNosedMonkeyRenderer extends MobRenderer<SnubNosedMonkey, SnubNosedMonkeyModel<SnubNosedMonkey>> {
    public static final ModelLayerLocation MODEL_LAYER = new ModelLayerLocation(new ResourceLocation(MonkeMadness.MOD_ID, "monkemadness"), "main");
    private static final ResourceLocation TEXTURE = new ResourceLocation("monkemadness:textures/entity/golden_snub_nosed_monkey.png");
    private static final ResourceLocation SILVER = new ResourceLocation("monkemadness:textures/entity/snub_nosed_monkey.png");
    public GoldenSnubNosedMonkeyRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new SnubNosedMonkeyModel<>(renderManagerIn.bakeLayer(SnubNosedMonkeyModel.LAYER_LOCATION)), 0.2F);
        this.shadowRadius = 0.5F;
        this.addLayer(new SnubNosedMonkeyHeldItemLayer(this));
    }


    @Override
    public ResourceLocation getTextureLocation(SnubNosedMonkey entity) {
        if(entity.getVariant() == 1){
            return SILVER;
        }
        return entity.isBaby() ? TEXTURE : TEXTURE;
    }
}
