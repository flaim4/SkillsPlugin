package net.flaim.skillsPlugin;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public final class SkillsPlugin extends JavaPlugin implements Listener {

    private final SkillManager skillManager = new SkillManager();

    private static SkillsPlugin skillsPlugin;

    public static SkillsPlugin getInstance() {
        return skillsPlugin;
    }

    @Override
    public void onLoad() {
        skillsPlugin = this;
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        System.out.println("Plugin working");
    }

    @Override
    public void onDisable() {
        {
            SimpleCache<UUID, PlayerSkillData> CHAGE = ((SimpleCache<UUID, PlayerSkillData>) SkillManager.META);
            for (Map.Entry<UUID, SimpleCache.CacheEntry<PlayerSkillData>> entry : CHAGE.cache.entrySet()) {
                CHAGE.saver.accept(entry.getKey(), entry.getValue().value);
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        skillManager.getOrCreatePlayerData(player);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Material blockType = event.getBlock().getType();
        Player player = event.getPlayer();

        for (TreeType treeType : TreeType.getAllTreeTypes()) {
            if (blockType == treeType.getLogType()) {
                skillManager.getOrCreatePlayerData(player).woodcuttingSkill.addXp(10);
                player.sendMessage("xp: " + skillManager.getOrCreatePlayerData(player).woodcuttingSkill.getXp() + " level: " + skillManager.getOrCreatePlayerData(player).woodcuttingSkill.getLevel());
            }
        }
    }
}
