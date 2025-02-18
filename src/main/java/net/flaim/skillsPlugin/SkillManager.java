package net.flaim.skillsPlugin;

import java.util.HashMap;
import java.util.Map;

public class SkillManager {
    private final Map<String, PlayerSkillData> playerSkills = new HashMap<>();

    public PlayerSkillData getOrCreatePlayerData(String playerName) {
        return playerSkills.computeIfAbsent(playerName, PlayerSkillData::new);
    }

    public Map<String, PlayerSkillData> getPlayerSkills() {
        return playerSkills;
    }

}
