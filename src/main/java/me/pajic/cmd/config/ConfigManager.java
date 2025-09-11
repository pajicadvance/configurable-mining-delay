package me.pajic.cmd.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;

public class ConfigManager {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path FILE_PATH = FabricLoader.getInstance().getConfigDir().resolve("miningdelay.json");
    private static final Logger LOGGER = LoggerFactory.getLogger("Configurable Mining Delay");
    public static ModConfig CONFIG;

    public static void loadConfig() {
        readConfig();
        saveConfig();
    }

    public static void saveConfig() {
        try (FileWriter writer = new FileWriter(FILE_PATH.toFile())) {
            GSON.toJson(CONFIG, writer);
        } catch (IOException e) {
            LOGGER.error("Failed to save mod config", e);
        }
    }

    private static void readConfig() {
        try (FileReader reader = new FileReader(FILE_PATH.toFile())) {
            CONFIG = GSON.fromJson(reader, ModConfig.class);
            CONFIG.validate();
        } catch (FileNotFoundException | JsonSyntaxException e) {
            LOGGER.warn("Config doesn't exist or is malformed, initializing new mod config...");
            initializeConfig();
        } catch (IOException e) {
            LOGGER.error("Failed to read mod config", e);
        }
    }

    private static void initializeConfig() {
        try (FileWriter writer = new FileWriter(FILE_PATH.toFile())) {
            CONFIG = new ModConfig();
            GSON.toJson(CONFIG, writer);
        } catch (IOException e) {
            LOGGER.error("Failed to initialize mod config", e);
        }
    }
}
