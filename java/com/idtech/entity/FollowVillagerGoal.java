package com.idtech.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.npc.AbstractVillager;

import java.util.List;

public class FollowVillagerGoal extends Goal {
    private SpongebobEntity spongebob;
    private AbstractVillager villagerToFollow;
    private int soundTick;

    public FollowVillagerGoal(SpongebobEntity s) {
        spongebob = s;
        soundTick = 0;
    }

    @Override
    public boolean canUse() {
        List<? extends AbstractVillager> list =
                this.spongebob.level
                        .getEntitiesOfClass(AbstractVillager.class,
                                this.spongebob.getBoundingBox()
                                        .inflate(35, 10, 35));

        double d0 = Double.MAX_VALUE;

        for(AbstractVillager villager : list) {
            double d1 = this.spongebob.distanceToSqr(villager);
            if (!(d1 > d0)) {
                d0 = d1;
                villagerToFollow = villager;
            }
        }

        return villagerToFollow != null;
    }

    public void tick() {
        if(--soundTick <= 0) {
            soundTick = 10;
            spongebob.playSound(new SoundEvent(
                    new ResourceLocation(
                            "examplemod", "hey_squidward")),
                    0.45f, 1);
        }
        this.spongebob.getNavigation().moveTo(this.villagerToFollow, 0.5D);
    }

    public boolean canContinueToUse() {
        return villagerToFollow.isAlive();
    }
}
