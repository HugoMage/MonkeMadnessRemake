package com.hugomage.monkemadness.client;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ForgeModelBakery;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "monkemadness", value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)

public class PanFluteRenderProperties implements IItemRenderProperties {
    @Override
    public BlockEntityWithoutLevelRenderer getItemStackRenderer()
    {
        return new ItemIRS();
    }
    @SubscribeEvent
    public static void hookMethod(ModelRegistryEvent event)
    {
        ForgeModelBakery.addSpecialModel(new ModelResourceLocation("monkemadness:pan_flute_gui", "inventory"));
        ForgeModelBakery.addSpecialModel(new ModelResourceLocation("monkemadness:pan_flute_in_hand", "inventory"));

    }
    public static final PanFluteRenderProperties RENDER_PROPERTIES = new PanFluteRenderProperties();
}
