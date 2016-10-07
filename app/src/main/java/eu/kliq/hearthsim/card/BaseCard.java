package eu.kliq.hearthsim.card;

import eu.kliq.hearthsim.Game;
import eu.kliq.hearthsim.Player;

public abstract class BaseCard {
    protected Game mGame;
    int mBaseCost;
    int mTurnCost;

    protected BaseCard(Game game) {
        mGame = game;
    }

    protected void setCost(int value) {
        mBaseCost = value;
        mTurnCost = value;
    }

    public int getBaseCost() {
        return mBaseCost;
    }

    public int getCost() {
        return mTurnCost;
    }

    public void onNextTurn() {
        mTurnCost = mBaseCost;
    }

    public void onPlay(Player player, int position) {
        mGame.getCurrentPlayer().spendMana(getCost());
    }
}
