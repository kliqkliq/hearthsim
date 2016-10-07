package eu.kliq.hearthsim.card.spell.core;

import eu.kliq.hearthsim.Game;
import eu.kliq.hearthsim.Player;
import eu.kliq.hearthsim.card.spell.BaseSpell;

public class Assassinate extends BaseSpell {
    public static final int COST = 5;

    public Assassinate(Game game) {
        super(game);
        setCost(COST);
    }

    @Override
    public void onPlay(Player player, int position) {
        player.destroyMinion(position);
    }
}