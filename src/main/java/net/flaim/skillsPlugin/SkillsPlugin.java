package net.flaim.skillsPlugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
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
    }

    @Override
    public void onDisable() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            SkillManager skillManager = new SkillManager();
            PlayerSkillData playerData = skillManager.getOrCreatePlayerData(player);

            skillManager.savePlayerData(player, playerData);
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        skillManager.getOrCreatePlayerData(event.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        PlayerSkillData playerData = skillManager.getOrCreatePlayerData(event.getPlayer());
        skillManager.savePlayerData(event.getPlayer(), playerData);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Material blockType = event.getBlock().getType();
        Player player = event.getPlayer();

        PlayerSkillData playerData = skillManager.getOrCreatePlayerData(player);

        for (TreeType treeType : TreeType.getAllTreeTypes()) {
            if (blockType == treeType.getLogType()) {
                playerData.woodcuttingSkill.addXp(10);

                skillManager.savePlayerData(player, playerData);

                player.sendMessage("xp: " + playerData.woodcuttingSkill.getXp() + " level: " + playerData.woodcuttingSkill.getLevel());
            }
        }
    }

}
