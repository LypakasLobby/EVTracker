package com.lypaka.evtracker.Utils;

import com.lypaka.evtracker.Commands.SlotCommand;
import com.lypaka.evtracker.ConfigGetters;
import com.lypaka.lypakautils.FancyText;
import com.lypaka.lypakautils.Listeners.JoinListener;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.STitlePacket;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public class PacketHandler {

    public static void startTimer() {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {

                for (Map.Entry<UUID, Pokemon> entry : SlotCommand.toggledPlayers.entrySet()) {

                    sendPacket(JoinListener.playerMap.get(entry.getKey()), entry.getValue());

                }

            }

        }, 0, 1000L);

    }

    public static void sendPacket (ServerPlayerEntity player, Pokemon pokemon) {

        int hp = pokemon.getEVs().getArray()[0];
        int atk = pokemon.getEVs().getArray()[1];
        int def = pokemon.getEVs().getArray()[2];
        int satk = pokemon.getEVs().getArray()[3];
        int sdef = pokemon.getEVs().getArray()[4];
        int spd = pokemon.getEVs().getArray()[5];

        String message = ConfigGetters.message
                .replace("%hp%", String.valueOf(hp))
                .replace("%atk%", String.valueOf(atk))
                .replace("%def%", String.valueOf(def))
                .replace("%spatk%", String.valueOf(satk))
                .replace("%spdef%", String.valueOf(sdef))
                .replace("%spd%", String.valueOf(spd));

        STitlePacket title = new STitlePacket(STitlePacket.Type.ACTIONBAR, FancyText.getFormattedText(message), 2, 2, 2);
        player.connection.send(title);

    }



}
