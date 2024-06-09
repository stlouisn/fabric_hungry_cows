package com.pnku.hungrycows.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class HungryCowsConfig {
    private static File folder = new File("config");
    private static File hungrycowsConfig;
    public static Gson config = new GsonBuilder().setPrettyPrinting().create();
    public static HungryCowsConfigDefaults INSTANCE;

    public static void init() {
        loadDefaults();
        createConfig();
        readFromConfig();
        writeToConfig();
    }

    private static void loadDefaults() {
        INSTANCE = new HungryCowsConfigDefaults();
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
                 String json = config.toJson(INSTANCE);
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
             INSTANCE = config.fromJson(new FileReader(hungrycowsConfig), HungryCowsConfigDefaults.class);
             if (INSTANCE == null) {
                 throw new IllegalStateException("Default configuration does not exist.");
             }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeToConfig () {
        try {
                String json = config.toJson(INSTANCE);
                FileWriter writer = new FileWriter(hungrycowsConfig, false);
                writer.write(json);
                writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
