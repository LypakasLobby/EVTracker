package com.lypaka.evtracker.Commands;

import com.lypaka.evtracker.EVTracker;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Arrays;
import java.util.List;

@Mod.EventBusSubscriber(modid = EVTracker.MOD_ID)
public class EVTrackerCommand {

    public static final List<String> ALIASES = Arrays.asList("evtracker", "etrack", "etracker");

    @SubscribeEvent
    public static void onCommandRegistration (RegisterCommandsEvent event) {

        new ReloadCommand(event.getDispatcher());
        new SlotCommand(event.getDispatcher());
        new ToggleCommand(event.getDispatcher());

    }

}
