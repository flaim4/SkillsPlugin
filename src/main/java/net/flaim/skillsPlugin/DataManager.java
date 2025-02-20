package net.flaim.skillsPlugin;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class DataManager {

    private File file;
    private FileConfiguration config;

    public DataManager(String fileName) {
        file = new File("plugins/SkillsPlugin/", fileName + ".yml");
        if (!file.exists()) {
            try {
                file.getParentFile().mkdir();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object get(String path) {
        return config.get(path);
    }

    public void set(String path, Object value) {
        config.set(path, value);
        save();
    }
}
