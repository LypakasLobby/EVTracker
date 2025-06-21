package com.lypaka.evtracker;

import com.lypaka.evtracker.Commands.EVTrackerCommand;
import com.lypaka.evtracker.Utils.PacketHandler;
import com.lypaka.lypakautils.ConfigurationLoaders.BasicConfigManager;
import com.lypaka.lypakautils.ConfigurationLoaders.ConfigUtils;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.nio.file.Paths;

public class EVTracker implements ModInitializer {

    public static final String MOD_ID = "evtracker";
    public static final String MOD_NAME = "EVTracker";
    public static final Logger logger = LogManager.getLogger("EVTracker");
    public static BasicConfigManager configManager;

    @Override
    public void onInitialize() {

        Path dir = ConfigUtils.checkDir(Paths.get("./config/evtracker"));
        String[] files = new String[]{"evtracker.conf"};
        configManager = new BasicConfigManager(files, dir, EVTracker.class, MOD_NAME, MOD_ID, logger);
        configManager.init();
        ConfigGetters.load();
        PacketHandler.startTimer();
        EVTrackerCommand.register();

    }

}
