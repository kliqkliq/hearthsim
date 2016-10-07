package eu.kliq.hearthsim.card.minion.core;

import eu.kliq.hearthsim.Game;
import eu.kliq.hearthsim.Player;
import eu.kliq.hearthsim.card.minion.BaseMinion;

public class MirrorImageMinion extends BaseMinion {
    public static final int COST = 0;

    public MirrorImageMinion(Game game) {
        super(game);
        setCost(COST);
        setTaunt(true);
    }

    @Override
    public void doBattlecry(Player player, int position) {
    }
}
