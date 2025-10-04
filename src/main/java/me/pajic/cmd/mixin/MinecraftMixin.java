package me.pajic.cmd.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.pajic.cmd.config.ConfigManager;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {

    @Shadow
    public abstract boolean hasSingleplayerServer();

    @ModifyExpressionValue(
            method = "startUseItem",
            at = @At(
                    value = "CONSTANT",
                    args = "intValue=4"
            )
    )
    private int modifyPlacementDelay(int original) {
        return ConfigManager.CONFIG.isEnabledOnServers() || hasSingleplayerServer() ?
                ConfigManager.CONFIG.getPlacementDelay() : original;
    }
}
