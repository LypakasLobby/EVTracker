package com.lypaka.evtracker.Commands;

import com.lypaka.evtracker.ConfigGetters;
import com.lypaka.evtracker.EVTracker;
import com.lypaka.lypakautils.Handlers.FancyTextHandler;
import com.lypaka.lypakautils.Handlers.PermissionHandler;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

public class ReloadCommand {

    public ReloadCommand (CommandDispatcher<ServerCommandSource> dispatcher) {

        for (String a : EVTrackerCommand.ALIASES) {

            dispatcher.register(
                    CommandManager.literal(a)
                            .then(
                                    CommandManager.literal("reload")
                                            .executes(c -> {

                                                if (c.getSource().getEntity() instanceof ServerPlayerEntity) {

                                                    ServerPlayerEntity player = (ServerPlayerEntity) c.getSource().getEntity();
                                                    if (!PermissionHandler.hasPermission(player, "evtracker.command.admin")) {

                                                        player.sendMessage(FancyTextHandler.getFormattedText("&cYou don't have permission to use this command!"));
                                                        return 0;

                                                    }

                                                }

                                                EVTracker.configManager.load();
                                                ConfigGetters.load();
                                                c.getSource().sendMessage(FancyTextHandler.getFormattedText("&aSuccessfully reloaded EVTracker configuration!"));
                                                return 0;

                                            })

                            )

            );

        }

    }

}
