package com.koshinls.klsworkshop.mixin;

import com.koshinls.klsworkshop.item.CreativeWrenchItem;
import com.koshinls.klsworkshop.settings.SettingsState;
import com.koshinls.klsworkshop.wrenchmodes.WrenchMode;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.LightBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LightBlock.class)
public class LightBlockMixin {

    @Inject(
            method = "getShape",
            at = @At("HEAD"),
            cancellable = true
    )
    private void klsworkshop$lightModeShape(
            BlockState state,
            BlockGetter level,
            BlockPos pos,
            CollisionContext context,
            CallbackInfoReturnable<VoxelShape> cir
    ) {

        // Keep vanilla Light Block behavior.
        if (context.isHoldingItem(
                net.minecraft.world.item.Items.LIGHT
        )) {
            return;
        }

        if (!(context instanceof EntityCollisionContext entityContext)) {
            return;
        }

        Entity entity = entityContext.getEntity();

        if (!(entity instanceof Player player)) {
            return;
        }

        if (!SettingsState.isEnabled(WrenchMode.LIGHT)) {
            return;
        }

        if (!(player.getMainHandItem().getItem()
                instanceof CreativeWrenchItem)) {
            return;
        }

        if (CreativeWrenchItem.getMode(
                player.getMainHandItem()
        ) != WrenchMode.LIGHT) {
            return;
        }

        cir.setReturnValue(Shapes.block());
    }
}