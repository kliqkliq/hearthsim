package eu.kliq.hearthsim.card.spell.core;

import eu.kliq.hearthsim.Game;
import eu.kliq.hearthsim.Player;
import eu.kliq.hearthsim.card.spell.BaseSpell;

public class CS2_076 extends BaseSpell {

    public CS2_076(Game game) {
        super(game);
    }

    @Override
    public void onPlay(Player player, int position) {
        player.destroyMinion(position);
    }
}