package com.hugomage.monkemadness;
import com.hugomage.monkemadness.client.model.*;
import com.hugomage.monkemadness.client.renderer.*;
import com.hugomage.monkemadness.client.renderer.layer.TarsierOnShoulderLayer;
import com.hugomage.monkemadness.entities.*;
import com.hugomage.monkemadness.registry.MMEntitysRegistry;
import com.hugomage.monkemadness.registry.MMItemsRegistry;
import com.hugomage.monkemadness.registry.MMSoundsRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Pillager;
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
        SpawnPlacements.register(MMEntitysRegistry.SNUBNOSEDMONKEY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(MMEntitysRegistry.TARSIER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(MMEntitysRegistry.ZOMBIEAPE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnPlacements.register(MMEntitysRegistry.BALE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(MMEntitysRegistry.PROBOSCIS.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(MMEntitysRegistry.SLOW_LORIS.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(MMEntitysRegistry.MANDRILL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(MMEntitysRegistry.GIGANTOPITHECUS.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(MMEntitysRegistry.JAPANESEMACAQUE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(MMEntitysRegistry.POACHER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Pillager::checkMobSpawnRules);
        SpawnPlacements.register(MMEntitysRegistry.CRESTEDMACAQUE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(MMEntitysRegistry.BONOBO.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(MMEntitysRegistry.GIBBON.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(MMEntitysRegistry.GELADA.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(MMEntitysRegistry.HOWLER_MONKEY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(MMEntitysRegistry.TAMARIN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(MMEntitysRegistry.LEMUR.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(MMEntitysRegistry.BUSHBABY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        SpawnPlacements.register(MMEntitysRegistry.UAKARI.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
    }
    @SubscribeEvent
    public void registerEntityRenderer(EntityRenderersEvent.RegisterRenderers  event) {
        event.registerEntityRenderer(MMEntitysRegistry.ORANGUTAN.get(), OrangutanRenderer::new);
        event.registerEntityRenderer(MMEntitysRegistry.SNUBNOSEDMONKEY.get(), GoldenSnubNosedMonkeyRenderer::new);
        event.registerEntityRenderer(MMEntitysRegistry.TARSIER.get(), TarsierRenderer::new);
        event.registerEntityRenderer(MMEntitysRegistry.ZOMBIEAPE.get(), ZombieApeRenderer::new);
        event.registerEntityRenderer(MMEntitysRegistry.BALE.get(), BaleMonkeyRenderer::new);
        event.registerEntityRenderer(MMEntitysRegistry.PROBOSCIS.get(), ProboscisRenderer::new);
        event.registerEntityRenderer(MMEntitysRegistry.SLOW_LORIS.get(), SlowLorisRenderer::new);
        event.registerEntityRenderer(MMEntitysRegistry.MANDRILL.get(), MandrillRenderer::new);
        event.registerEntityRenderer(MMEntitysRegistry.GIGANTOPITHECUS.get(), GigantoRenderer::new);
        event.registerEntityRenderer(MMEntitysRegistry.JAPANESEMACAQUE.get(), JapaneseMacaqueRenderer::new);
        event.registerEntityRenderer(MMEntitysRegistry.POACHER.get(), PoacherRenderer::new);
        event.registerEntityRenderer(MMEntitysRegistry.CRESTEDMACAQUE.get(), CrestedRenderer::new);
        event.registerEntityRenderer(MMEntitysRegistry.BONOBO.get(), BonoboRenderer::new);
        event.registerEntityRenderer(MMEntitysRegistry.GELADA.get(), GeladaRenderer::new);
        event.registerEntityRenderer(MMEntitysRegistry.GIBBON.get(), GibbonRenderer::new);
        event.registerEntityRenderer(MMEntitysRegistry.HOWLER_MONKEY.get(), HowlerMonkeyRenderer::new);
        event.registerEntityRenderer(MMEntitysRegistry.TAMARIN.get(), TamarinRenderer::new);
        event.registerEntityRenderer(MMEntitysRegistry.LEMUR.get(), LemurRenderer::new);
        event.registerEntityRenderer(MMEntitysRegistry.BUSHBABY.get(), BushBabyRenderer::new);
        event.registerEntityRenderer(MMEntitysRegistry.UAKARI.get(), UakariRenderer::new);
        event.registerEntityRenderer(MMEntitysRegistry.POOP.get(), ThrownItemRenderer::new);
    }
    public void registerLayerModel(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(OrangutanModel.LAYER_LOCATION, OrangutanModel::createBodyLayer);
        event.registerLayerDefinition(SnubNosedMonkeyModel.LAYER_LOCATION, SnubNosedMonkeyModel::createBodyLayer);
        event.registerLayerDefinition(TarsierModel.LAYER_LOCATION, TarsierModel::createBodyLayer);
        event.registerLayerDefinition(ZombieApeModel.LAYER_LOCATION, ZombieApeModel::createBodyLayer);
        event.registerLayerDefinition(BaleMonkeyModel.LAYER_LOCATION, BaleMonkeyModel::createBodyLayer);
        event.registerLayerDefinition(ProboscisModel.LAYER_LOCATION, ProboscisModel::createBodyLayer);
        event.registerLayerDefinition(SlowLorisModel.LAYER_LOCATION, SlowLorisModel::createBodyLayer);
        event.registerLayerDefinition(MandrillModel.LAYER_LOCATION, MandrillModel::createBodyLayer);
        event.registerLayerDefinition(GigantopithecusModel.LAYER_LOCATION, GigantopithecusModel::createBodyLayer);
        event.registerLayerDefinition(JapaneseMacaqueModel.LAYER_LOCATION, JapaneseMacaqueModel::createBodyLayer);
        event.registerLayerDefinition(PoacherModel.LAYER_LOCATION, PoacherModel::createBodyLayer);
        event.registerLayerDefinition(CrestedModel.LAYER_LOCATION, CrestedModel::createBodyLayer);
        event.registerLayerDefinition(BonoboModel.LAYER_LOCATION, BonoboModel::createBodyLayer);
        event.registerLayerDefinition(GeladaModel.LAYER_LOCATION, GeladaModel::createBodyLayer);
        event.registerLayerDefinition(GibbonModel.LAYER_LOCATION, GibbonModel::createBodyLayer);
        event.registerLayerDefinition(TamarinModel.LAYER_LOCATION, TamarinModel::createBodyLayer);
        event.registerLayerDefinition(BushBabyModel.LAYER_LOCATION, BushBabyModel::createBodyLayer);
        event.registerLayerDefinition(HowlerMonkeyModel.LAYER_LOCATION, HowlerMonkeyModel::createBodyLayer);
        event.registerLayerDefinition(LemurModel.LAYER_LOCATION, LemurModel::createBodyLayer);
        event.registerLayerDefinition(UakariModel.LAYER_LOCATION, UakariModel::createBodyLayer);
    }
    private void setup(final EntityAttributeCreationEvent event)
    {
        event.put(MMEntitysRegistry.ORANGUTAN.get(), Orangutan.setCustomAttributes().build());
        event.put(MMEntitysRegistry.SLOW_LORIS.get(), SlowLoris.setCustomAttributes().build());
        event.put(MMEntitysRegistry.SNUBNOSEDMONKEY.get(), SnubNosedMonkey.setCustomAttributes().build());
        event.put(MMEntitysRegistry.TARSIER.get(), Tarsier.setCustomAttributes().build());
        event.put(MMEntitysRegistry.ZOMBIEAPE.get(), ZombieApe.setCustomAttributes().build());
        event.put(MMEntitysRegistry.BALE.get(), BaleMonkey.setCustomAttributes().build());
        event.put(MMEntitysRegistry.PROBOSCIS.get(), ProboscisMonkey.setCustomAttributes().build());
        event.put(MMEntitysRegistry.MANDRILL.get(), Mandrill.setCustomAttributes().build());
        event.put(MMEntitysRegistry.GIGANTOPITHECUS.get(), Gigantopithecus.setCustomAttributes().build());
        event.put(MMEntitysRegistry.JAPANESEMACAQUE.get(), JapaneseMacaque.setCustomAttributes().build());
        event.put(MMEntitysRegistry.POACHER.get(), Poacher.setCustomAttributes().build());
        event.put(MMEntitysRegistry.CRESTEDMACAQUE.get(), CrestedMacaque.setCustomAttributes().build());
        event.put(MMEntitysRegistry.BONOBO.get(), Bonobo.setCustomAttributes().build());
        event.put(MMEntitysRegistry.GELADA.get(), Gelada.setCustomAttributes().build());
        event.put(MMEntitysRegistry.GIBBON.get(), Gibbon.setCustomAttributes().build());
        event.put(MMEntitysRegistry.HOWLER_MONKEY.get(), HowlerMonkey.setCustomAttributes().build());
        event.put(MMEntitysRegistry.TAMARIN.get(), Tamarin.setCustomAttributes().build());
        event.put(MMEntitysRegistry.LEMUR.get(), Lemur.setCustomAttributes().build());
        event.put(MMEntitysRegistry.BUSHBABY.get(), BushBaby.setCustomAttributes().build());
        event.put(MMEntitysRegistry.UAKARI.get(), Uakari.setCustomAttributes().build());
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code  to dispatch IMC to another mod
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
