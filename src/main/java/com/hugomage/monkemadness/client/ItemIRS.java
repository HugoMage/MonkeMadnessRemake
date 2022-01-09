package com.hugomage.monkemadness.client;

import com.hugomage.monkemadness.MonkeMadness;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.item.ItemStack;

public class ItemIRS extends BlockEntityWithoutLevelRenderer {

    public ItemIRS(BlockEntityRenderDispatcher p_172550_, EntityModelSet p_172551_) {
        super(p_172550_, p_172551_);
    }

    public ItemIRS() {
        super(null,null);
    }

    @Override
    public void renderByItem(ItemStack stack, ItemTransforms.TransformType transformType, PoseStack matrixStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay)
    {
        matrixStack.pushPose();
        BakedModel ibakedmodel = Minecraft.getInstance().getItemRenderer().getItemModelShaper().getModelManager().getModel(
                new ModelResourceLocation(MonkeMadness.MOD_ID + ":" + stack.getItem().getRegistryName().getPath() + "_in_hand","inventory")
        );
        boolean flag = transformType == ItemTransforms.TransformType.FIRST_PERSON_LEFT_HAND || transformType == ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND;

        boolean handFlag =
                transformType == ItemTransforms.TransformType.FIRST_PERSON_LEFT_HAND ||
                        transformType == ItemTransforms.TransformType.FIRST_PERSON_RIGHT_HAND ||
                        transformType == ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND ||
                        transformType == ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND;

        matrixStack.translate(0.5F,0.5F,0.5F);

        if (!handFlag) {
            ibakedmodel = Minecraft.getInstance().getItemRenderer().getItemModelShaper().getModelManager().getModel(
                    new ModelResourceLocation(MonkeMadness.MOD_ID + ":" + stack.getItem().getRegistryName().getPath() + "_gui", "inventory")
            );

            if (transformType == ItemTransforms.TransformType.GUI) {
                renderItemModelIntoGUI(stack, combinedLight, ibakedmodel);
            } else {
                Minecraft.getInstance().getItemRenderer().render(stack, transformType, flag, matrixStack, buffer, combinedLight, combinedOverlay, ibakedmodel);
            }
        } else {
            Minecraft.getInstance().getItemRenderer().render(stack, transformType, flag, matrixStack, buffer, combinedLight, combinedOverlay, ibakedmodel);
        }
        matrixStack.popPose();
    }

    public static void renderItemModelIntoGUI(ItemStack stack, int combinedLight, BakedModel model) {
        RenderSystem.getModelViewStack().pushPose();
        RenderSystem.setShaderTexture(0, TextureAtlas.LOCATION_BLOCKS);
        Minecraft.getInstance().textureManager.getTexture(TextureAtlas.LOCATION_BLOCKS).setFilter(false, false);

        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        PoseStack matrixstack = new PoseStack();
        MultiBufferSource.BufferSource irendertypebuffer$impl = Minecraft.getInstance().renderBuffers().bufferSource();

        Lighting.setupForFlatItems();

        Minecraft.getInstance().getItemRenderer().render(stack, ItemTransforms.TransformType.GUI, false, matrixstack, irendertypebuffer$impl, combinedLight, OverlayTexture.NO_OVERLAY, model);
        irendertypebuffer$impl.endBatch();
        RenderSystem.enableDepthTest();
        Lighting.setupFor3DItems();


        RenderSystem.getModelViewStack().popPose();
    }
}