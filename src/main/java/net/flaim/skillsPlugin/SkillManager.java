package net.flaim.skillsPlugin;

import org.bukkit.entity.Player;
import java.util.UUID;

public class SkillManager {
    private DataManager data = new DataManager("playerData");

    public PlayerSkillData getOrCreatePlayerData(Player player) {
        UUID playerUUID = player.getUniqueId();

        if (data.get("player_" + playerUUID) != null) {
            return loadPlayerData(playerUUID);
        } else {
            return createNewPlayerData(player);
        }
    }

    private PlayerSkillData loadPlayerData(UUID playerUUID) {
        PlayerSkillData playerData = new PlayerSkillData(playerUUID);

        playerData.Combat.setLevel((int) data.get("player_" + playerUUID + ".Combat.level"));
        playerData.Combat.setXp((int) data.get("player_" + playerUUID + ".Combat.xp"));

        playerData.woodcuttingSkill.setLevel((int) data.get("player_" + playerUUID + ".Woodcutting.level"));
        playerData.woodcuttingSkill.setXp((int) data.get("player_" + playerUUID + ".Woodcutting.xp"));

        playerData.miningSkill.setLevel((int) data.get("player_" + playerUUID + ".Mining.level"));
        playerData.miningSkill.setXp((int) data.get("player_" + playerUUID + ".Mining.xp"));

        return playerData;
    }

    private PlayerSkillData createNewPlayerData(Player player) {
        PlayerSkillData newData = new PlayerSkillData(player.getUniqueId());
        savePlayerData(player, newData);
        return newData;
    }

    public void savePlayerData(Player player, PlayerSkillData playerSkillData) {
        UUID playerUUID = player.getUniqueId();

        data.set("player_" + playerUUID + ".Combat.level", playerSkillData.Combat.getLevel());
        data.set("player_" + playerUUID + ".Combat.xp", playerSkillData.Combat.getXp());

        data.set("player_" + playerUUID + ".Woodcutting.level", playerSkillData.woodcuttingSkill.getLevel());
        data.set("player_" + playerUUID + ".Woodcutting.xp", playerSkillData.woodcuttingSkill.getXp());

        data.set("player_" + playerUUID + ".Mining.level", playerSkillData.miningSkill.getLevel());
        data.set("player_" + playerUUID + ".Mining.xp", playerSkillData.miningSkill.getXp());

        data.save();
    }
}

