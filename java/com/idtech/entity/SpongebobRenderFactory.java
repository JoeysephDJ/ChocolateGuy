package com.idtech.entity;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.monster.AbstractSkeleton;


public class SpongebobRenderFactory implements EntityRendererProvider<AbstractSkeleton> {

    public static SpongebobRenderFactory INSTANCE = new SpongebobRenderFactory();

    @Override
    public EntityRenderer<AbstractSkeleton> create(Context manager) {
        return new SpongebobRenderer(manager);
    }
}