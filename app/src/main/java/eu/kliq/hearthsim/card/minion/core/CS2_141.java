package eu.kliq.hearthsim.card.minion.core;

import eu.kliq.hearthsim.Game;
import eu.kliq.hearthsim.Player;
import eu.kliq.hearthsim.card.minion.BaseMinion;

public class CS2_141 extends BaseMinion {

    public CS2_141(Game game) {
        super(game);
    }

    @Override
    public void doBattlecry(Player player, int position) {
        player.doDamage(position, 1);
    }
}
