package net.flaim.skillsPlugin;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class SkillsPlugin extends JavaPlugin implements Listener {

    private final SkillManager skillManager = new SkillManager();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        String playerName = event.getPlayer().getName();
        skillManager.getOrCreatePlayerData(playerName);
    }
}
