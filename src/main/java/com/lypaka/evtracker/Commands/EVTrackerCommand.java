package com.lypaka.evtracker.Commands;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

import java.util.Arrays;
import java.util.List;

public class EVTrackerCommand {

    public static final List<String> ALIASES = Arrays.asList("evtracker", "etrack", "etracker");

    public static void register() {

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {

            new ReloadCommand(dispatcher);
            new SlotCommand(dispatcher);
            new ToggleCommand(dispatcher);

        });

    }

}
