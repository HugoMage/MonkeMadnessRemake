package com.hugomage.monkemadness.client.renderer.layer;

import com.hugomage.monkemadness.client.model.TarsierModel;
import com.hugomage.monkemadness.client.renderer.TarsierRenderer;
import com.hugomage.monkemadness.entities.Tarsier;
import com.hugomage.monkemadness.registry.MMEntitysRegistry;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;

public class TarsierOnShoulderLayer<T extends Player> extends RenderLayer<T, PlayerModel<T>> {
    private final TarsierModel<Tarsier> model = new TarsierModel<>(TarsierModel.createBodyLayer().bakeRoot());
    public TarsierOnShoulderLayer(RenderLayerParent<T, PlayerModel<T>> p_i50929_1_) {
        super(p_i50929_1_);
    }
    public void render(PoseStack p_117307_, MultiBufferSource p_117308_, int p_117309_, T p_117310_, float p_117311_, float p_117312_, float p_117313_, float p_117314_, float p_117315_, float p_117316_) {
        this.render(p_117307_, p_117308_, p_117309_, p_117310_, p_117311_, p_117312_, p_117315_, p_117316_, true);
        this.render(p_117307_, p_117308_, p_117309_, p_117310_, p_117311_, p_117312_, p_117315_, p_117316_, false);
    }
    private void render(PoseStack p_229136_1_, MultiBufferSource p_229136_2_, int p_229136_3_, T p_229136_4_, float p_229136_5_, float p_229136_6_, float p_229136_7_, float p_229136_8_, boolean p_229136_9_) {
        CompoundTag compoundnbt = p_229136_9_ ? p_229136_4_.getShoulderEntityLeft() : p_229136_4_.getShoulderEntityRight();
        if (compoundnbt.getString("id").equals(MMEntitysRegistry.TARSIER.get().getRegistryName().toString())) {
            p_229136_1_.pushPose();
            p_229136_1_.translate(p_229136_9_ ? (double)0.4F : (double)-0.4F, p_229136_4_.isCrouching() ? (double)-1.3F : -1.5D, 0.0D);
            VertexConsumer ivertexbuilder = p_229136_2_.getBuffer(this.model.renderType(TarsierRenderer.TEXTURE));
            this.model.renderOnShoulder(p_229136_1_, ivertexbuilder, p_229136_3_, OverlayTexture.NO_OVERLAY, p_229136_5_, p_229136_6_, p_229136_7_, p_229136_8_, p_229136_4_.tickCount);
            p_229136_1_.popPose();
        }
    }
}
