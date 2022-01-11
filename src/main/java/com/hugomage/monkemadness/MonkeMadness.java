package com.hugomage.monkemadness;
import com.hugomage.monkemadness.client.model.OrangutanModel;
import com.hugomage.monkemadness.client.model.SnubNosedMonkeyModel;
import com.hugomage.monkemadness.client.model.TarsierModel;
import com.hugomage.monkemadness.client.renderer.GoldenSnubNosedMonkeyRenderer;
import com.hugomage.monkemadness.client.renderer.OrangutanRenderer;
import com.hugomage.monkemadness.client.renderer.TarsierRenderer;
import com.hugomage.monkemadness.entities.Orangutan;
import com.hugomage.monkemadness.entities.SnubNosedMonkey;
import com.hugomage.monkemadness.entities.Tarsier;
import com.hugomage.monkemadness.registry.MMEntitysRegistry;
import com.hugomage.monkemadness.registry.MMItemsRegistry;
import com.hugomage.monkemadness.registry.MMSoundsRegistry;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.stream.Collectors;

@Mod("monkemadness")
public class MonkeMadness
{
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "monkemadness";

    public MonkeMadness() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::registerCommon);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::registerEntityRenderer);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::registerLayerModel);
        MinecraftForge.EVENT_BUS.register(this);
        MMItemsRegistry.BLOCKS.register(bus);
        MMItemsRegistry.ITEMS.register(bus);
        MMEntitysRegistry.ENTITY_TYPES.register(bus);
        MMSoundsRegistry.SOUND_EVENTS.register(bus);

    }
    private void registerCommon(FMLCommonSetupEvent event) {
        SpawnPlacements.register(MMEntitysRegistry.ORANGUTAN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);

    }
    @SubscribeEvent
    public void registerEntityRenderer(EntityRenderersEvent.RegisterRenderers  event) {
        event.registerEntityRenderer(MMEntitysRegistry.ORANGUTAN.get(), OrangutanRenderer::new);
        event.registerEntityRenderer(MMEntitysRegistry.SNUBNOSEDMONKEY.get(), GoldenSnubNosedMonkeyRenderer::new);
        event.registerEntityRenderer(MMEntitysRegistry.TARSIER.get(), TarsierRenderer::new);
    }
    public void registerLayerModel(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(OrangutanModel.LAYER_LOCATION, OrangutanModel::createBodyLayer);
        event.registerLayerDefinition(SnubNosedMonkeyModel.LAYER_LOCATION, SnubNosedMonkeyModel::createBodyLayer);
        event.registerLayerDefinition(TarsierModel.LAYER_LOCATION, TarsierModel::createBodyLayer);
    }
    private void setup(final EntityAttributeCreationEvent event)
    {
        event.put(MMEntitysRegistry.ORANGUTAN.get(), Orangutan.setCustomAttributes().build());
        event.put(MMEntitysRegistry.SNUBNOSEDMONKEY.get(), SnubNosedMonkey.setCustomAttributes().build());
        event.put(MMEntitysRegistry.TARSIER.get(), Tarsier.setCustomAttributes().build());
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("data", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.messageSupplier().get()).
                collect(Collectors.toList()));
    }

}
