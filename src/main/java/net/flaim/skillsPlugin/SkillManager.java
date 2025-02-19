package net.flaim.skillsPlugin;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class SkillManager {
    public static final Gson GSON = new GsonBuilder().create();
    public static final Cache<UUID, PlayerSkillData> META;
    static {
        Path path = Paths.get("").toAbsolutePath().resolve("playermeta");
        if (!Files.exists(path)) {
            path.toFile().mkdirs();
        }

        META = new SimpleCacheBuilder<UUID, PlayerSkillData>()
                .setExpirationTime(1)
                .setUnit(TimeUnit.MINUTES)
                .setLoader((uuid) -> {
                    File file = new File(path.toFile(), uuid.toString() + ".meta");
                    System.out.println("Load file: " + file.getAbsolutePath());
                    if (file.exists()) {
                        try (FileReader reader = new FileReader(file)) {
                            return GSON.fromJson(reader, PlayerSkillData.class);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    return new PlayerSkillData((UUID) uuid);
                }).setSaver((uuid, meta) -> {
                    File file = new File(path.toFile(), uuid.toString() + ".meta");
                    System.out.println("Save file: " + file.getAbsolutePath());
                    try (FileWriter writer = new FileWriter(file)) {
                        GSON.toJson(meta, writer);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).build();
    }

    public PlayerSkillData getOrCreatePlayerData(Player player) {
        return META.get(player.getUniqueId());
    }


}
