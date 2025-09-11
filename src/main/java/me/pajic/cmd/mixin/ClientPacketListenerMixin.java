package me.pajic.cmd.mixin;

import me.pajic.cmd.config.ConfigManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPacketListener.class)
public class ClientPacketListenerMixin {

    @Inject(
            method = "handleLogin",
            at = @At("TAIL")
    )
    private void afterLogin(CallbackInfo ci) {
        Minecraft mc = Minecraft.getInstance();
        if (!mc.hasSingleplayerServer() && ConfigManager.CONFIG.isEnabledOnServers() && mc.player != null) {
            mc.player.sendSystemMessage(Component.translatable("cmd.warnOnLogin"));
        }
    }
}
