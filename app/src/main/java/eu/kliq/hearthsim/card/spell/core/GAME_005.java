package eu.kliq.hearthsim.card.spell.core;

import eu.kliq.hearthsim.Game;
import eu.kliq.hearthsim.Player;
import eu.kliq.hearthsim.card.spell.BaseSpell;

public class GAME_005 extends BaseSpell {

    public GAME_005(Game game) {
        super(game);
    }

    @Override
    public void onPlay(Player player, int position) {
        super.onPlay(player, position);
        mGame.getCurrentPlayer().addTurnMana(1);
    }
}
