package eu.kliq.hearthsim.hero;

import eu.kliq.hearthsim.Player;
import eu.kliq.hearthsim.card.weapon.BaseWeapon;

public abstract class BaseHero {
    public static final int DEFAULT_ATTACK = 0;
    public static final int DEFAULT_SKILL_DAMAGE = 0;
    public static final int DEFAULT_HEALTH = 30;

    private int mBaseAttack;
    private int mTurnAttack;
    private int mBaseHealth;
    private int mCurrentHealth;
    private int mBaseSkillDamage;
    private int mCurrentSkillDamage;
    private int mArmor;

    private BaseWeapon mWeapon;

    BaseHero() {
        setAttack(DEFAULT_ATTACK);
        setHealth(DEFAULT_HEALTH);
        setSkillDamage(DEFAULT_SKILL_DAMAGE);
    }

    public abstract void onHeroPower(Player player, int position);
    public void addTurnAttack(int value) {
        mTurnAttack += value;
    }
    public void onNextTurn() {
        mTurnAttack = mBaseAttack;
    }

    public int getAttack() {
        int finalAttack = mTurnAttack;
        if (mWeapon != null) {
            finalAttack += mWeapon.getAttack();
            mWeapon.onAttack();
            if (mWeapon.getDurability() == 0) {
                mWeapon.onDestroy();
                mWeapon = null;
            }
        }
        return finalAttack;
    }

    protected void setAttack(int value) {
        mBaseAttack = value;
        mTurnAttack = value;
    }

    public int getHealth() {
        return mCurrentHealth;
    }

    protected void setHealth(int value) {
        mBaseHealth = value;
        mCurrentHealth = value;
    }

    public void onAttack(int attack) {
        int armorLeft = mArmor - attack;
        if (armorLeft > 0) {
            mArmor = armorLeft;
        } else {
            mCurrentHealth += armorLeft;
            mArmor = 0;
        }
    }

    public int getSkillDamage() {
        return mCurrentSkillDamage;
    }

    protected void setSkillDamage(int value) {
        mBaseSkillDamage = value;
        mCurrentSkillDamage = value;
    }

    public int getArmor() {
        return mArmor;
    }

    public void addArmor(int value) {
        mArmor += value;
    }

    public void addWeapon(BaseWeapon weapon) {
        mWeapon = weapon;
    }
}
