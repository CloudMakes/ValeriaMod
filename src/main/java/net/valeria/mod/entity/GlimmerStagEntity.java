package net.valeria.mod.entity;

import javax.annotation.Nullable;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.valeria.mod.registry.ModEntities;

/** Luminous deer with glowing antlers that leaves a short-lived trail of sparkles. Drops Shimmering Velvet (loot table). */
public class GlimmerStagEntity extends Animal {

    public GlimmerStagEntity(EntityType<? extends GlimmerStagEntity> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 22.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.3D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.1D, Ingredient.of(Items.SWEET_BERRIES), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(Items.SWEET_BERRIES);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob partner) {
        return ModEntities.GLIMMER_STAG.get().create(level);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (this.level().isClientSide()) {
            boolean moving = this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-4D;
            if (moving && this.random.nextInt(2) == 0) {
                // sparkling trail behind the hooves
                this.level().addParticle(ParticleTypes.END_ROD, this.getRandomX(0.6D), this.getY() + 0.15D + this.random.nextDouble() * 0.4D,
                        this.getRandomZ(0.6D), 0.0D, 0.01D, 0.0D);
            }
            if (this.random.nextInt(10) == 0) {
                // glow motes drifting off the antlers
                this.level().addParticle(ParticleTypes.END_ROD, this.getRandomX(0.4D), this.getY() + 1.6D + this.random.nextDouble() * 0.4D,
                        this.getRandomZ(0.4D), 0.0D, 0.015D, 0.0D);
            }
        }
    }
}
