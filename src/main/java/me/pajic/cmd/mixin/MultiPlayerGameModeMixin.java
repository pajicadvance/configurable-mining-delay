package me.pajic.cmd.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.pajic.cmd.config.ConfigManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MultiPlayerGameMode.class)
public class MultiPlayerGameModeMixin {

    @Shadow @Final private Minecraft minecraft;

    @ModifyExpressionValue(
            method = "continueDestroyBlock",
            at = @At(
                    value = "CONSTANT",
                    args = "intValue=5"
            )
    )
    private int modifyDestroyDelay(int original) {
        return ConfigManager.CONFIG.isEnabledOnServers() || minecraft.hasSingleplayerServer() ?
                ConfigManager.CONFIG.getMiningDelay() : original;
    }
}