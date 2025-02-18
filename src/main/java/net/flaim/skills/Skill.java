package net.flaim.skills;

public abstract class Skill {
    private final String name;
    private int level;
    private int xp;

    public Skill(String name) {
        this.name = name;
        this.level = 1;
        this.xp = 0;
    }

    public String getName() {
        return this.name;
    }

    public int getLevel() {
        return this.level;
    }

    public int getXp() {
        return this.xp;
    }

    public int getXpToLevelUp() {
        return level * 100;
    }

    public void addXp(int amount) {
        xp +=amount;
        if (xp >= getXpToLevelUp()) {
            xp -= getXpToLevelUp();
            level++;
        }
    }

}
