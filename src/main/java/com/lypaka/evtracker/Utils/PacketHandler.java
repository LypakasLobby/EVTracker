package com.lypaka.evtracker.Utils;

import com.cobblemon.mod.common.api.pokemon.stats.Stats;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.lypaka.evtracker.Commands.SlotCommand;
import com.lypaka.evtracker.ConfigGetters;
import com.lypaka.lypakautils.Handlers.FancyTextHandler;
import com.lypaka.lypakautils.LypakaUtils;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import net.minecraft.network.packet.s2c.play.SubtitleS2CPacket;
import net.minecraft.network.packet.s2c.play.TitleS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;

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

                    sendPacket(LypakaUtils.playerMap.get(entry.getKey()), entry.getValue());

                }

            }

        }, 0, 1000L);

    }

    public static void sendPacket (ServerPlayerEntity player, Pokemon pokemon) {

        int hp = pokemon.getEvs().get(Stats.HP);
        int atk = pokemon.getEvs().get(Stats.ATTACK);
        int def = pokemon.getEvs().get(Stats.DEFENCE);
        int satk = pokemon.getEvs().get(Stats.SPECIAL_ATTACK);
        int sdef = pokemon.getEvs().get(Stats.SPECIAL_DEFENCE);
        int spd = pokemon.getEvs().get(Stats.SPEED);

        String message = ConfigGetters.message
                .replace("%hp%", String.valueOf(hp))
                .replace("%atk%", String.valueOf(atk))
                .replace("%def%", String.valueOf(def))
                .replace("%spatk%", String.valueOf(satk))
                .replace("%spdef%", String.valueOf(sdef))
                .replace("%spd%", String.valueOf(spd));
        player.networkHandler.sendPacket(new GameMessageS2CPacket(FancyTextHandler.getFormattedText(message), true));

    }

}
