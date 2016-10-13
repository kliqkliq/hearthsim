package eu.kliq.hearthsim.card;

import eu.kliq.hearthsim.Game;
import eu.kliq.hearthsim.Player;

public abstract class BaseCard {
    protected Game mGame;
    private int mBaseCost;
    private int mTurnCost;
    private String mName;

    public BaseCard(Game game) {
        mGame = game;
    }

    public void setCost(int value) {
        mBaseCost = value;
        mTurnCost = value;
    }

    public int getBaseCost() {
        return mBaseCost;
    }

    public int getCost() {
        return mTurnCost;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public void onNextTurn() {
        mTurnCost = mBaseCost;
    }

    public void onPlay(Player player, int position) {
        System.out.println("Playing (" + getCost() + ")" +  getName());
        mGame.getCurrentPlayer().spendMana(getCost());
    }
}
