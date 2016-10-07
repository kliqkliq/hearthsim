package eu.kliq.hearthsim.card.minion.core;

import eu.kliq.hearthsim.Game;
import eu.kliq.hearthsim.Player;
import eu.kliq.hearthsim.card.minion.BaseMinion;

public class IronforgeRifleman extends BaseMinion {
    public static final int COST = 3;
    public static final int HEALTH = 2;
    public static final int ATTACK = 2;

    public IronforgeRifleman(Game game) {
        super(game);
        setCost(COST);
        setAttack(ATTACK);
        setHealth(HEALTH);
    }

    @Override
    public void doBattlecry(Player player, int position) {
        player.doDamage(position, 1);
    }
}
