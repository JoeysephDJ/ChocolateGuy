package com.idtech.entity;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.monster.Zombie;


public class ChocolateGuyRenderFactory implements EntityRendererProvider<Zombie> {

    public static ChocolateGuyRenderFactory INSTANCE = new ChocolateGuyRenderFactory();

    @Override
    public EntityRenderer<Zombie> create(Context manager) {
        return new ChocolateGuyRenderer(manager);
    }
}