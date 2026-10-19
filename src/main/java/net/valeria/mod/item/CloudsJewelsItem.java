package net.valeria.mod.item;

import java.util.List;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

/**
 * Cloud's Jewels: a Curios "neck" slot item that grants Resistance IV with no HUD icon and no particles.
 * (Curios 9.x for 1.21.1 has no CurioItem base class; items implement ICurioItem instead.)
 */
public class CloudsJewelsItem extends Item implements ICurioItem {
    /** Amplifier 3 == Resistance IV. */
    private static final int AMPLIFIER = 3;
    private static final int DURATION_TICKS = 40;
    private static final int REFRESH_BELOW_TICKS = 30;

    public CloudsJewelsItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity wearer = slotContext.entity();
        if (wearer.level().isClientSide() || wearer.tickCount % 10 != 0) {
            return;
        }
        MobEffectInstance current = wearer.getEffect(MobEffects.DAMAGE_RESISTANCE);
        if (current == null || current.getAmplifier() < AMPLIFIER || current.getDuration() < REFRESH_BELOW_TICKS) {
            // ambient = true, visible (particles) = false, showIcon = false  -> completely silent
            wearer.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, DURATION_TICKS, AMPLIFIER, true, false, false));
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("item.valeria.clouds_jewels.tooltip"));
        tooltip.add(Component.translatable("item.valeria.clouds_jewels.tooltip2"));
        super.appendHoverText(stack, context, tooltip, flag);
    }
}
