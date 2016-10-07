package eu.kliq.hearthsim.card.minion.core;

import eu.kliq.hearthsim.Game;
import eu.kliq.hearthsim.Player;
import eu.kliq.hearthsim.card.minion.BaseMinion;

public class Wolfrider extends BaseMinion {
    public static final int COST = 3;
    public static final int ATTACK = 3;
    public static final int HEALTH = 1;

    public Wolfrider(Game game) {
        super(game);
        setCost(COST);
        setHealth(HEALTH);
        setAttack(ATTACK);
        setCharge(true);
    }

    @Override
    public void doBattlecry(Player player, int position) {
    }
}