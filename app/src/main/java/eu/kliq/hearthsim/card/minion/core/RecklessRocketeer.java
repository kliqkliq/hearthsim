package eu.kliq.hearthsim.card.minion.core;

import eu.kliq.hearthsim.Game;
import eu.kliq.hearthsim.Player;
import eu.kliq.hearthsim.card.minion.BaseMinion;

public class RecklessRocketeer extends BaseMinion {
    public static final int COST = 6;
    public static final int HEALTH = 2;
    public static final int ATTACK = 5;

    public RecklessRocketeer(Game game) {
        super(game);
        setCost(COST);
        setHealth(HEALTH);
        setAttack(ATTACK);
        setCharge(true);
    }

    @Override
    protected void doBattlecry(Player player, int position) {

    }

}
