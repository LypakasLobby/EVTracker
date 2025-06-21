package com.lypaka.evtracker.Commands;

import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.api.storage.party.PlayerPartyStore;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.lypaka.evtracker.Utils.PacketHandler;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SlotCommand {

    public static Map<UUID, Pokemon> toggledPlayers = new HashMap<>();

    public SlotCommand (CommandDispatcher<ServerCommandSource> dispatcher) {

        for (String a : EVTrackerCommand.ALIASES) {

            dispatcher.register(
                    CommandManager.literal(a)
                            .then(
                                    CommandManager.literal("view")
                                            .then(
                                                    CommandManager.argument("slot", IntegerArgumentType.integer(1, 6))
                                                            .executes(c -> {

                                                                if (c.getSource().getEntity() instanceof ServerPlayerEntity) {

                                                                    ServerPlayerEntity player = (ServerPlayerEntity) c.getSource().getEntity();
                                                                    PlayerPartyStore storage = Cobblemon.INSTANCE.getStorage().getParty(player);
                                                                    int slot = IntegerArgumentType.getInteger(c, "slot") - 1;
                                                                    Pokemon pokemon = storage.get(slot);
                                                                    PacketHandler.sendPacket(player, pokemon);
                                                                    toggledPlayers.put(player.getUuid(), pokemon);

                                                                }

                                                                return 0;

                                                            })

                                            )

                            )

            );

        }

    }

}
