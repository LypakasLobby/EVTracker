package com.lypaka.evtracker.Commands;

import com.lypaka.lypakautils.FancyText;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;

public class ToggleCommand {

    public ToggleCommand (CommandDispatcher<CommandSource> dispatcher) {

        for (String a : EVTrackerCommand.ALIASES) {

            dispatcher.register(
                    Commands.literal(a)
                            .then(
                                    Commands.literal("toggle")
                                            .executes(c -> {

                                                if (c.getSource().getEntity() instanceof ServerPlayerEntity) {

                                                    ServerPlayerEntity player = (ServerPlayerEntity) c.getSource().getEntity();
                                                    SlotCommand.toggledPlayers.entrySet().removeIf(e -> {

                                                        if (e.getKey().toString().equalsIgnoreCase(player.getUUID().toString())) {

                                                            player.sendMessage(FancyText.getFormattedText("&aHiding EV tracking..."), player.getUUID());
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
