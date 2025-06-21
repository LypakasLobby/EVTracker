package com.lypaka.evtracker.Commands;

import com.lypaka.lypakautils.Handlers.FancyTextHandler;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

public class ToggleCommand {

    public ToggleCommand (CommandDispatcher<ServerCommandSource> dispatcher) {

        for (String a : EVTrackerCommand.ALIASES) {

            dispatcher.register(
                    CommandManager.literal(a)
                            .then(
                                    CommandManager.literal("toggle")
                                            .executes(c -> {

                                                if (c.getSource().getEntity() instanceof ServerPlayerEntity) {

                                                    ServerPlayerEntity player = (ServerPlayerEntity) c.getSource().getEntity();
                                                    SlotCommand.toggledPlayers.entrySet().removeIf(e -> {

                                                        if (e.getKey().toString().equalsIgnoreCase(player.getUuid().toString())) {

                                                            player.sendMessage(FancyTextHandler.getFormattedText("&aHiding EV tracking..."));
                                                            return true;

                                                        }

                                                        return false;

                                                    });

                                                }

                                                return 0;

                                            })

                            )

            );

        }

    }

}
