package net.valeria.mod.entity;

import java.util.EnumSet;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.Goal;

/** Simple owner-following goal that works for flying tamed mobs (no pathfinding-type assumptions). */
public class FollowOwnerFlyingGoal extends Goal {
    private final TamableAnimal mob;
    private final double speed;
    private final double startDistSqr;
    private final double stopDistSqr;
    private LivingEntity owner;
    private int recalc;

    public FollowOwnerFlyingGoal(TamableAnimal mob, double speed, float startDistance, float stopDistance) {
        this.mob = mob;
        this.speed = speed;
        this.startDistSqr = startDistance * startDistance;
        this.stopDistSqr = stopDistance * stopDistance;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        LivingEntity o = this.mob.getOwner();
        if (o == null || o.isSpectator() || this.mob.isVehicle() || this.mob.level() != o.level()) {
            return false;
        }
        if (this.mob.distanceToSqr(o) < this.startDistSqr) {
            return false;
        }
        this.owner = o;
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        return this.owner != null && this.owner.isAlive() && !this.mob.isVehicle()
                && this.mob.distanceToSqr(this.owner) > this.stopDistSqr;
    }

    @Override
    public void start() {
        this.recalc = 0;
    }

    @Override
    public void stop() {
        this.owner = null;
        this.mob.getNavigation().stop();
    }

    @Override
    public void tick() {
        this.mob.getLookControl().setLookAt(this.owner, 10.0F, this.mob.getMaxHeadXRot());
        if (--this.recalc <= 0) {
            this.recalc = this.adjustedTickDelay(10);
            if (this.mob.distanceToSqr(this.owner) >= 24.0D * 24.0D) {
                this.mob.teleportTo(this.owner.getX() + 1.5D, this.owner.getY() + 1.0D, this.owner.getZ() + 1.5D);
                this.mob.getNavigation().stop();
            } else {
                this.mob.getNavigation().moveTo(this.owner, this.speed);
            }
        }
    }
}
