package com.idtech.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EntityMod {

    @SubscribeEvent
    public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event){
        event.getRegistry().register(SpongebobEntity.TYPE);
        event.getRegistry().register(ChocolateGuyEntity.TYPE);
    }
    @SubscribeEvent
    public static void registerEntityEggs(final RegistryEvent.Register<Item> event) {
        event.getRegistry().register(SpongebobEntity.EGG);
        event.getRegistry().register(ChocolateGuyEntity.EGG);
    }
    @SubscribeEvent
    public static void entityRenderers(final EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer(SpongebobEntity.TYPE, SpongebobRenderFactory.INSTANCE);
        event.registerEntityRenderer(ChocolateGuyEntity.TYPE, ChocolateGuyRenderFactory.INSTANCE);
    }

    // this is different than in 1.16 but everything else is the same
    // I do think this makes more sense than the other way but alas change is usually hard.
    @SubscribeEvent
    public static void onAttributeCreate(EntityAttributeCreationEvent event) {
        event.put(SpongebobEntity.TYPE, SpongebobEntity.createAttributes().build());
        event.put(ChocolateGuyEntity.TYPE, ChocolateGuyEntity.createAttributes().build());
    }

}
