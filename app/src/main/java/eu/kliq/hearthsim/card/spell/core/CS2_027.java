package eu.kliq.hearthsim.card.spell.core;

import eu.kliq.hearthsim.Game;
import eu.kliq.hearthsim.Player;
import eu.kliq.hearthsim.card.spell.BaseSpell;

public class CS2_027 extends BaseSpell {

    public CS2_027(Game game) {
        super(game);
    }

    @Override
    public void onPlay(Player player, int position) {
        super.onPlay(player, position);
        mGame.getCurrentPlayer().addMinion("CS2_mirror");
        mGame.getCurrentPlayer().addMinion("CS2_mirror");
    }
}
