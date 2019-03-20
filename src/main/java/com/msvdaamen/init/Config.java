package com.msvdaamen.init;

import com.msvdaamen.Drawbridges;
import com.msvdaamen.proxy.CommonProxy;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Level;

public class Config {

    private static final String CATEGORY_GENERAL = "general";

    public static boolean useVanillaRecipe = false;
    public static boolean useOwnOres = true;
    public static int drawbridgeRange = 16;
    public static int extendedDrawbridgeRange = 64;

    public static void readConfig() {
        Configuration cfg = CommonProxy.config;
        try {
            cfg.load();
            initGeneralConfig(cfg);
        } catch (Exception e1) {
            Drawbridges.logger.log(Level.ERROR, "Problem loading config file!", e1);
        } finally {
            if (cfg.hasChanged()) {
                cfg.save();
            }
        }
    }

    private static void initGeneralConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_GENERAL, "General configuration");
        useVanillaRecipe = cfg.getBoolean("useVanillaRecipe", CATEGORY_GENERAL, useVanillaRecipe, "Set to true if you want vanilla reipes");
        useOwnOres = cfg.getBoolean("useOwnOres", CATEGORY_GENERAL, useOwnOres, "Set to false if you don't want the mod to use own ores and ingots. be careful if vanilla recipes is set to false be sure there are other mods with custom ores and ingots, plates");
        drawbridgeRange = cfg.getInt("Drawbridge Range", CATEGORY_GENERAL, drawbridgeRange, 1, 64, "The range for the drawbridge");
        extendedDrawbridgeRange = cfg.getInt("Extended Drawbridge", CATEGORY_GENERAL, extendedDrawbridgeRange, 1, 64, "The range for the Extended Drawbridge");
    }

}