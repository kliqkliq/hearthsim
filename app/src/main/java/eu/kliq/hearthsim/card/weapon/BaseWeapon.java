package eu.kliq.hearthsim.card.weapon;

import eu.kliq.hearthsim.Game;
import eu.kliq.hearthsim.Player;
import eu.kliq.hearthsim.card.BaseCard;

public class BaseWeapon extends BaseCard {
    private int mBaseAttack;
    private int mCurrentAttack;
    private int mBaseDurability;
    private int mCurrentDurability;

    public BaseWeapon(Game game) {
        super(game);
    }

    public int getBaseAttack() {
        return mBaseAttack;
    }

    public void setAttack(int value) {
        mBaseAttack = value;
        mCurrentAttack = value;
    }

    public int getAttack() {
        return mCurrentAttack;
    }

    public int getBaseDurability() {
        return mBaseDurability;
    }

    public void setDurability(int value) {
        mBaseDurability = value;
        mCurrentDurability = value;
    }

    public int getDurability() {
        return mCurrentDurability;
    }

    @Override
    public void onPlay(Player player, int position) {
        super.onPlay(player, position);
        mGame.getCurrentPlayer().getHero().addWeapon(this);
    }

    public void onAttack() {
        mCurrentDurability--;
    }

    public void onDestroy() {
    }
}
