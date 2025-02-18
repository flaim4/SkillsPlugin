package net.flaim.skillsPlugin;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class SkillsPlugin extends JavaPlugin implements Listener {

    private final SkillManager skillManager = new SkillManager();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        System.out.println("Plugin working");
    }

    @Override
    public void onDisable() {
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        String playerName = event.getPlayer().getName();
        skillManager.getOrCreatePlayerData(playerName);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Material blockType = event.getBlock().getType();
        Player player = event.getPlayer();
        String playerName = player.getName();

        for (TreeType treeType : TreeType.getAllTreeTypes()) {
            if (blockType == treeType.getLogType()) {
                PlayerSkillData skillData = skillManager.getOrCreatePlayerData(playerName);
                skillData.getSkill("Woodcutting").addXp(10);
            }
        }
    }
}
