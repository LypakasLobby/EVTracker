package com.lypaka.evtracker;

public class ConfigGetters {

    public static String message;

    public static void load() {

        message = EVTracker.configManager.getConfigNode(0, "Message").getString();

    }

}
