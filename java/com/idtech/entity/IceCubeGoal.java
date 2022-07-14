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

public class IceCubeGoal extends Goal {

    private ChocolateGuyEntity tom;
    private TargetingConditions targeting;
    private Player player;
    private int soundTick;

    public IceCubeGoal(ChocolateGuyEntity cg) {
        tom = cg;
        targeting = TargetingConditions.forNonCombat().range(100);
    }

    @Override
    public boolean canUse() {
        this.player = tom.level.getNearestPlayer(targeting, tom);
        return this.player != null && this.holdingInteresting(this.player) && !tom.hasEnoughIce();
    }

    private boolean holdingInteresting(Player p) {
        for(InteractionHand hand : InteractionHand.values()) {
            ItemStack stack = p.getItemInHand(hand);
            if(stack.is(Items.ICE)) {
                return true;
            }
        }
        return false;
    }

    public boolean canContinueToUse() {
        return this.holdingInteresting(this.player) && !tom.hasEnoughIce();
    }

    public void tick() {
        if(--soundTick <= 0 && tom.distanceToSqr(player) < 10) {
            soundTick = 40;
            tom.playSound(new SoundEvent(new ResourceLocation(BaseMod.MODID, "ice_cubes")), 0.5f, 1);
        }
        tom.getNavigation().moveTo(player, 1f);
    }


}