package com.idtech.entity;

import com.idtech.BaseMod;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;


public class SpongebobEntity extends Skeleton {
    public static EntityType<SpongebobEntity> TYPE = (EntityType<SpongebobEntity>)
            EntityType.Builder.of(SpongebobEntity::new, MobCategory.CREATURE)
                    .build("spongebob")
                    .setRegistryName(BaseMod.MODID, "spongebob");
    public static Item EGG = EntityUtils.buildEntitySpawnEgg(TYPE, 0xfff464, 0x909104);

    public SpongebobEntity(EntityType<? extends Skeleton> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return AmbientCreature.createMobAttributes()
                .add(Attributes.ATTACK_DAMAGE, 0)
                .add(Attributes.MOVEMENT_SPEED, 0.5D)
                .add(Attributes.FOLLOW_RANGE, 35.0D);
    }

    @Override
    public void setItemSlot(EquipmentSlot p_32138_, ItemStack p_32139_) {
    }

    @Override
    public void registerGoals() {
        this.targetSelector.addGoal(0, new FollowVillagerGoal(this));
    }

    @Override
    protected boolean shouldDespawnInPeaceful() {
        return false;
    }

    @Override
    protected boolean isSunBurnTick() {
        return false;
    }
}
