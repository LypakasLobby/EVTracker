package com.lypaka.evtracker.Commands;

import com.lypaka.evtracker.ConfigGetters;
import com.lypaka.evtracker.EVTracker;
import com.lypaka.lypakautils.FancyText;
import com.lypaka.lypakautils.MiscHandlers.PermissionHandler;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;

public class ReloadCommand {

    public ReloadCommand (CommandDispatcher<CommandSource> dispatcher) {

        for (String a : EVTrackerCommand.ALIASES) {

            dispatcher.register(
                    Commands.literal(a)
                            .then(
                                    Commands.literal("reload")
                                            .executes(c -> {

                                                if (c.getSource().getEntity() instanceof ServerPlayerEntity) {

                                                    ServerPlayerEntity player = (ServerPlayerEntity) c.getSource().getEntity();
                                                    if (!PermissionHandler.hasPermission(player, "evtracker.command.admin")) {

                                                        player.sendMessage(FancyText.getFormattedText("&cYou don't have permission to use this command!"), player.getUUID());
                                                        return 0;

                                                    }

                                                }

                                                EVTracker.configManager.load();
                                                ConfigGetters.load();
                                                c.getSource().sendSuccess(FancyText.getFormattedText("&aSuccessfully reloaded EVTracker configuration!"), true);
                                                return 0;

                                            })

                            )

            );

        }

    }

}
