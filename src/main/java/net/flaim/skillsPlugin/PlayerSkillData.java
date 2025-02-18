package net.flaim.skillsPlugin;

import net.flaim.skillsPlugin.skills.CombatSkill;
import net.flaim.skillsPlugin.skills.MiningSkill;
import net.flaim.skillsPlugin.skills.WoodcuttingSkill;

import java.util.HashMap;
import java.util.Map;

public class PlayerSkillData {

    private final String playerName;
    private final Map<String, Skill> skills = new HashMap<>();

    public PlayerSkillData(String playerName) {
        this.playerName = playerName;
        skills.put("Combat", new CombatSkill());
        skills.put("Mining", new WoodcuttingSkill());
        skills.put("Woodcutting", new MiningSkill());
    }

    public Skill getSkill(String name) {
        return skills.get(name);
    }

    public Map<String, Skill> getSkills() {
        return skills;
    }

}
