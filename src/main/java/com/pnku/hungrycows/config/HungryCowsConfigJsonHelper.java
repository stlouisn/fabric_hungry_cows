package com.pnku.hungrycows.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class HungryCowsConfigJsonHelper {
    private static File folder = new File("config");
    private static File hungrycowsConfig;
    public static Gson configGson = new GsonBuilder().setPrettyPrinting().create();
    public static HungryCowsConfig INSTANCE;

    public static void init() {
        loadDefaults();
        createConfig();
        readFromConfig();
        writeToConfig();
    }

    private static void loadDefaults() {
        INSTANCE = new HungryCowsConfig();
    }

    public static void createConfig() {
        if (!folder.exists()) {
            folder.mkdir();
        }
        if (folder.isDirectory()) {
        hungrycowsConfig = new File(folder,"hungrycows.json");
        if (!hungrycowsConfig.exists()) {
            try {
                 hungrycowsConfig.createNewFile();
                 loadDefaults();
                 String json = configGson.toJson(INSTANCE);
                 FileWriter writer = new FileWriter(hungrycowsConfig);
                 writer.write(json);
                 writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
                }
            }
        }
    }

    public static void readFromConfig() {
        try {
            HungryCowsConfig config = configGson.fromJson(new FileReader(hungrycowsConfig), HungryCowsConfig.class);
            INSTANCE.updateConfigs(config);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public static void writeToConfig () {
        try {
                String json = configGson.toJson(INSTANCE);
                FileWriter writer = new FileWriter(hungrycowsConfig, false);
                writer.write(json);
                writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
