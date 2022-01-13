package com.hugomage.monkemadness.client.renderer.layer;

import com.hugomage.monkemadness.client.model.SnubNosedMonkeyModel;
import com.hugomage.monkemadness.client.model.ZombieApeModel;
import com.hugomage.monkemadness.entities.SnubNosedMonkey;
import com.hugomage.monkemadness.entities.ZombieApe;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

public class ZombieApeHeldItemLayer extends RenderLayer<ZombieApe, ZombieApeModel<ZombieApe>> {
    public ZombieApeHeldItemLayer(RenderLayerParent<ZombieApe, ZombieApeModel<ZombieApe>> p_i50938_1_) {
        super(p_i50938_1_);
    }
    @Override
    public void render(PoseStack p_225628_1_, MultiBufferSource p_225628_2_, int p_225628_3_, ZombieApe p_225628_4_, float p_225628_5_, float p_225628_6_, float p_225628_7_, float p_225628_8_, float p_225628_9_, float p_225628_10_) {
        ItemStack itemstack = p_225628_4_.getItemBySlot(EquipmentSlot.MAINHAND);
        ModelPart modelpart = this.getParentModel().getZombieApeArm();
        modelpart.translateAndRotate(p_225628_1_);
        p_225628_1_.pushPose();
        p_225628_1_.translate(0.0F, 0.6, -0.3F);
        p_225628_1_.mulPose(Vector3f.XP.rotation(-0.2F));


        Minecraft.getInstance().getItemInHandRenderer().renderItem(p_225628_4_, itemstack, ItemTransforms.TransformType.FIRST_PERSON_LEFT_HAND, false, p_225628_1_, p_225628_2_, p_225628_3_);
        p_225628_1_.popPose();
    }
}
