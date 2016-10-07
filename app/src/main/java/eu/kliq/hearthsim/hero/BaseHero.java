package eu.kliq.hearthsim.hero;

import eu.kliq.hearthsim.Player;

public abstract class BaseHero {
    public static final int DEFAULT_ATTACK = 0;
    public static final int DEFAULT_SKILL_DAMAGE = 0;
    public static final int DEFAULT_HEALTH = 30;

    private int baseAttack;
    private int turnAttack;
    private int baseHealth;
    private int currentHealth;
    private int baseSkillDamage;
    private int currentSkillDamage;
    private int armor;

    BaseHero() {
        setAttack(DEFAULT_ATTACK);
        setHealth(DEFAULT_HEALTH);
        setSkillDamage(DEFAULT_SKILL_DAMAGE);
    }

    public abstract void onHeroPower(Player player, int position);
    public void addTurnAttack(int value) {
        turnAttack += value;
    }
    public void onNextTurn() {
        turnAttack = baseAttack;
    }

    public int getAttack() {
        return turnAttack;
    }

    protected void setAttack(int value) {
        baseAttack = value;
        turnAttack = value;
    }

    public int getHealth() {
        return currentHealth;
    }

    protected void setHealth(int value) {
        baseHealth = value;
        currentHealth = value;
    }

    public void onAttack(int attack) {
        int armorLeft = armor - attack;
        if (armorLeft > 0) {
            armor = armorLeft;
        } else {
            currentHealth += armorLeft;
            armor = 0;
        }
    }

    public int getSkillDamage() {
        return currentSkillDamage;
    }

    protected void setSkillDamage(int value) {
        baseSkillDamage = value;
        currentSkillDamage = value;
    }

    public int getArmor() {
        return armor;
    }

    public void addArmor(int value) {
        armor += value;
    }
}
