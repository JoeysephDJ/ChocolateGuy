package com.idtech.entity;

import com.idtech.BaseMod;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;

public class ChocolateGuyRenderer extends ZombieRenderer {
    public ChocolateGuyRenderer(EntityRendererProvider.Context provider) {
        super(provider);
    }

    @Override
    public ResourceLocation getTextureLocation(Zombie p_115941_) {
        return new ResourceLocation(BaseMod.MODID, "textures/entity/chocolateguy.png");
    }
}
