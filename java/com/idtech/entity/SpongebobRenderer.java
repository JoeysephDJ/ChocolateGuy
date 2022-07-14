package com.idtech.entity;

import com.idtech.BaseMod;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SkeletonRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.AbstractSkeleton;

public class SpongebobRenderer extends SkeletonRenderer {
    public SpongebobRenderer(EntityRendererProvider.Context provider) {
        super(provider);
    }

    @Override
    public ResourceLocation getTextureLocation(AbstractSkeleton p_115941_) {
        return new ResourceLocation(BaseMod.MODID, "textures/entity/spongebob.png");
    }
}
