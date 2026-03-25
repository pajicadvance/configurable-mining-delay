package me.pajic.cmd;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import me.pajic.cmd.config.ConfigManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.ClientCommands;
import net.minecraft.network.chat.Component;

public class ClientMain implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ConfigManager.loadConfig();
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, _) -> dispatcher.register(ClientCommands.literal("miningdelay")
                .then(ClientCommands.literal("delay")
                        .then(ClientCommands.argument("value", IntegerArgumentType.integer(0, 20)).executes(context -> {
                            int value = IntegerArgumentType.getInteger(context, "value");
                            ConfigManager.CONFIG.setMiningDelay(value);
                            ConfigManager.saveConfig();
                            context.getSource().sendFeedback(Component.translatable("cmd.setMiningDelay", value));
                            return Command.SINGLE_SUCCESS;
                        }))
                        .then(ClientCommands.literal("reset").executes(context -> {
                            ConfigManager.CONFIG.setMiningDelay(5);
                            ConfigManager.saveConfig();
                            context.getSource().sendFeedback(Component.translatable("cmd.setMiningDelay", 5));
                            return Command.SINGLE_SUCCESS;
                        }))
                        .executes(context -> {
                            context.getSource().sendFeedback(Component.translatable("cmd.getMiningDelay", ConfigManager.CONFIG.getMiningDelay()));
                            return Command.SINGLE_SUCCESS;
                        })
                )
                .then(ClientCommands.literal("placement_delay")
                        .then(ClientCommands.argument("value", IntegerArgumentType.integer(0, 20)).executes(context -> {
                            int value = IntegerArgumentType.getInteger(context, "value");
                            ConfigManager.CONFIG.setPlacementDelay(value);
                            ConfigManager.saveConfig();
                            context.getSource().sendFeedback(Component.translatable("cmd.setPlacementDelay", value));
                            return Command.SINGLE_SUCCESS;
                        }))
                        .then(ClientCommands.literal("reset").executes(context -> {
                            ConfigManager.CONFIG.setPlacementDelay(4);
                            ConfigManager.saveConfig();
                            context.getSource().sendFeedback(Component.translatable("cmd.setPlacementDelay", 4));
                            return Command.SINGLE_SUCCESS;
                        }))
                        .executes(context -> {
                            context.getSource().sendFeedback(Component.translatable("cmd.getPlacementDelay", ConfigManager.CONFIG.getPlacementDelay()));
                            return Command.SINGLE_SUCCESS;
                        })
                )
                .then(ClientCommands.literal("enabledOnServers")
                        .then(ClientCommands.argument("value", BoolArgumentType.bool()).executes(context -> {
                            boolean value = BoolArgumentType.getBool(context, "value");
                            ConfigManager.CONFIG.setEnabledOnServers(value);
                            ConfigManager.saveConfig();
                            context.getSource().sendFeedback(Component.translatable(value ? "cmd.setEnabledOnServers" : "cmd.setDisabledOnServers"));
                            return Command.SINGLE_SUCCESS;
                        }))
                        .executes(context -> {
                            context.getSource().sendFeedback(Component.translatable(ConfigManager.CONFIG.isEnabledOnServers() ? "cmd.getEnabledOnServers" : "cmd.getDisabledOnServers"));
                            return Command.SINGLE_SUCCESS;
                        })
                )
        ));
    }
}