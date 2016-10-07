package eu.kliq.hearthsim.card.minion;

import eu.kliq.hearthsim.Game;
import eu.kliq.hearthsim.Player;
import eu.kliq.hearthsim.card.BaseCard;

public abstract class BaseMinion extends BaseCard {
    private int mBaseHealth;
    private int mBaseAttack;
    private int mCurrentHealth;
    private int mTurnAttack;
    private boolean mIsTaunt;
    private boolean mIsCharge;

    protected BaseMinion(Game game) {
        super(game);
    }

    public int getBaseHealth() {
        return mBaseHealth;
    }

    protected void setHealth(int value) {
        mBaseHealth = value;
        mCurrentHealth = value;
    }

    public int getHealth() {
        return mCurrentHealth;
    }

    public int getBaseAttack() {
        return mBaseAttack;
    }

    protected void setAttack(int value) {
        mBaseAttack = value;
        mTurnAttack = value;
    }

    public int getAttack() {
        return mTurnAttack;
    }

    public boolean isTaunt() {
        return mIsTaunt;
    }

    protected void setTaunt(boolean isTaunt) {
        mIsTaunt = isTaunt;
    }

    public boolean isCharge() {
        return mIsCharge;
    }

    protected void setCharge(boolean isCharge) {
        mIsCharge = isCharge;
    }

    protected abstract void doBattlecry(Player player, int position);

    public void onDamage(int damage) {
        mCurrentHealth -= damage;
    }

    @Override
    public void onNextTurn() {
        super.onNextTurn();
        mTurnAttack = mBaseAttack;
    }

    public void onAttack(int attack) {
        onDamage(attack);
    }
}
