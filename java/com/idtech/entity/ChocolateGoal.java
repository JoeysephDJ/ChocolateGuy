package com.idtech.entity;

import com.idtech.BaseMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ChocolateGoal extends Goal {

    private ChocolateGuyEntity tom;
    private TargetingConditions targeting;
    private Player player;
    private int soundTick;

    public ChocolateGoal(ChocolateGuyEntity cg) {
        tom = cg;
        targeting = TargetingConditions.forNonCombat().range(100);
    }

    @Override
    public boolean canUse() {
        this.player = tom.level.getNearestPlayer(targeting, tom);
        return this.player != null && this.holdingInteresting(this.player);
    }

    private boolean holdingInteresting(Player p) {
        for(InteractionHand hand : InteractionHand.values()) {
            ItemStack stack = p.getItemInHand(hand);
            if(stack.is(Items.COCOA_BEANS)) {
                return true;
            }
        }
        return false;
    }

    public boolean canContinueToUse() {
        return holdingInteresting(player);
    }

    public void tick() {
        float speed = 0f;
        SoundEvent sound;
        if(tom.distanceToSqr(player) > 10) {
            speed = 2f;
            sound = new SoundEvent(new ResourceLocation(BaseMod.MODID, "chocolate"));
        } else {
            speed = 1f;
            sound = new SoundEvent(new ResourceLocation(BaseMod.MODID, "buyall"));
        }
        if(--soundTick <= 0) {
            soundTick = 25;
            tom.playSound(sound, 0.5f, 1);
        }
        tom.getNavigation().moveTo(player, speed);
    }
}