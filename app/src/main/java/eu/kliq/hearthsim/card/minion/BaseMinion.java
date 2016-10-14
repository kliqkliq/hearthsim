package eu.kliq.hearthsim.card.minion;

import eu.kliq.hearthsim.Game;
import eu.kliq.hearthsim.Player;
import eu.kliq.hearthsim.card.BaseCard;

public class BaseMinion extends BaseCard {
    private int mBaseHealth;
    private int mBaseAttack;
    private int mCurrentHealth;
    private int mTurnAttack;
    private boolean mIsTaunt;
    private boolean mIsCharge;

    public BaseMinion(Game game) {
        super(game);
    }

    public int getBaseHealth() {
        return mBaseHealth;
    }

    public void setHealth(int value) {
        mBaseHealth = value;
        mCurrentHealth = value;
    }

    public int getHealth() {
        return mCurrentHealth;
    }

    public int getBaseAttack() {
        return mBaseAttack;
    }

    public void setAttack(int value) {
        mBaseAttack = value;
        mTurnAttack = value;
    }

    public int getAttack() {
        return mTurnAttack;
    }

    public boolean isTaunt() {
        return mIsTaunt;
    }

    public void setTaunt(boolean isTaunt) {
        mIsTaunt = isTaunt;
    }

    public boolean isCharge() {
        return mIsCharge;
    }

    public void setCharge(boolean isCharge) {
        mIsCharge = isCharge;
    }

    public void doBattlecry(Player player, int position) {
    }

    public void onDamage(int damage) {
        System.out.println(getName() + ": " + damage + " damage received");
        mCurrentHealth -= damage;
        if (mCurrentHealth <= 0 ) {
            onDeathRattle();
        }
    }

    @Override
    public void onNextTurn() {
        super.onNextTurn();
        mTurnAttack = mBaseAttack;
    }

    public void onAttack(int attack) {
        onDamage(attack);
    }

    public void onDeathRattle() {
        System.out.println(getName() + ": activating death rattle");
        mGame.onDeathRattle(this);
    }
}
