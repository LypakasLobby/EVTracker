package com.lypaka.evtracker.Commands;

import com.lypaka.evtracker.Utils.PacketHandler;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.storage.PlayerPartyStorage;
import com.pixelmonmod.pixelmon.api.storage.StorageProxy;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SlotCommand {

    public static Map<UUID, Pokemon> toggledPlayers = new HashMap<>();

    public SlotCommand (CommandDispatcher<CommandSource> dispatcher) {

        for (String a : EVTrackerCommand.ALIASES) {

            dispatcher.register(
                    Commands.literal(a)
                            .then(
                                    Commands.literal("view")
                                            .then(
                                                    Commands.argument("slot", IntegerArgumentType.integer(1, 6))
                                                            .executes(c -> {

                                                                if (c.getSource().getEntity() instanceof ServerPlayerEntity) {

                                                                    ServerPlayerEntity player = (ServerPlayerEntity) c.getSource().getEntity();
                                                                    PlayerPartyStorage storage = StorageProxy.getParty(player);
                                                                    int slot = IntegerArgumentType.getInteger(c, "slot") - 1;
                                                                    Pokemon pokemon = storage.get(slot);
                                                                    PacketHandler.sendPacket(player, pokemon);
                                                                    toggledPlayers.put(player.getUUID(), pokemon);

                                                                }

                                                                return 0;

                                                            })

                                            )

                            )

            );

        }

    }

}
