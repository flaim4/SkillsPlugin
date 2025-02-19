package net.flaim.skillsPlugin;

import net.flaim.skillsPlugin.skills.CombatSkill;
import net.flaim.skillsPlugin.skills.MiningSkill;
import net.flaim.skillsPlugin.skills.WoodcuttingSkill;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerSkillData {

    private final UUID playerUUID;

    public final CombatSkill Combat = new CombatSkill();
    public final WoodcuttingSkill woodcuttingSkill = new WoodcuttingSkill();
    public final MiningSkill miningSkill = new MiningSkill();

    public PlayerSkillData(UUID playerUUID) {
        this.playerUUID = playerUUID;
    }

}
