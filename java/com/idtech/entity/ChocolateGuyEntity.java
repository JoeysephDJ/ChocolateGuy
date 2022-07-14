package com.idtech.entity;

import com.idtech.BaseMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class ChocolateGuyEntity extends Zombie {
    public static EntityType<ChocolateGuyEntity> TYPE = (EntityType<ChocolateGuyEntity>)
            EntityType.Builder.of(ChocolateGuyEntity::new, MobCategory.CREATURE)
                    .build("chocolateguy")
                    .setRegistryName(BaseMod.MODID, "chocolateguy");
    public static Item EGG = EntityUtils.buildEntitySpawnEgg(TYPE, 0xae9940, 0x8c84a9);
    private int iceCubes;

    public ChocolateGuyEntity(EntityType<? extends Zombie> type, Level level) {
        super(type, level);
        iceCubes = 0;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return AmbientCreature.createMobAttributes()
                .add(Attributes.FOLLOW_RANGE, 35.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.23F)
                .add(Attributes.ATTACK_DAMAGE, 0)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
    }

    @Override
    public void registerGoals() {
        this.targetSelector.addGoal(0, new LookAtPlayerGoal(this, Player.class, 6.0f));
        this.targetSelector.addGoal(1, new ChocolateGoal(this));
        this.targetSelector.addGoal(2, new IceCubeGoal(this));
    }

    @Override
    protected boolean shouldDespawnInPeaceful() {
        return false;
    }

    @Override
    protected boolean isSunBurnTick() {
        return false;
    }

    @Override
    protected SoundEvent getStepSound() {
        return null;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if(stack.is(Items.COCOA_BEANS)) {
            stack.shrink(1);
            this.spawnAtLocation(Items.DIAMOND);
            playSound(new SoundEvent(new ResourceLocation(BaseMod.MODID, "thanks")), 0.5f, 1);
            return InteractionResult.SUCCESS;
        } else if(stack.is(Items.ICE)) {
            switch(++this.iceCubes) {
                case 1:
                    playSound(new SoundEvent(new ResourceLocation(BaseMod.MODID, "what")), 0.5f, 1);
                    break;
                case 2:
                    playSound(new SoundEvent(new ResourceLocation(BaseMod.MODID, "thanks")), 0.5f, 1);
                    this.spawnAtLocation(Items.EMERALD);
                    break;
                default:
                    break;
            }
            stack.shrink(1);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    public boolean hasEnoughIce() {
        return iceCubes >= 2;
    }
}
