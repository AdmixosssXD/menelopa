package com.example.dupe;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;

public class DupeCommandMod implements ModInitializer {
    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            dispatcher.register(CommandManager.literal("dupe")
                .executes(DupeCommandMod::run));
        });
    }

    private static int run(CommandContext<ServerCommandSource> context) {
        ServerCommandSource source = context.getSource();
        if (source.getEntity() instanceof ServerPlayerEntity player) {
            ItemStack heldItem = player.getMainHandStack();
            if (!heldItem.isEmpty()) {
                ItemStack dupedItem = heldItem.copy();
                if (player.getInventory().insertStack(dupedItem)) {
                    player.sendMessage(new LiteralText("✅ Skopiowano przedmiot."), false);
                } else {
                    player.sendMessage(new LiteralText("❌ Brak miejsca w ekwipunku!"), false);
                }
            } else {
                player.sendMessage(new LiteralText("❌ Nie trzymasz żadnego przedmiotu."), false);
            }
        }
        return Command.SINGLE_SUCCESS;
    }
}