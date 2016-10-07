package eu.kliq.hearthsim.card.spell.core;

import eu.kliq.hearthsim.Game;
import eu.kliq.hearthsim.Player;
import eu.kliq.hearthsim.card.spell.BaseSpell;

public class HeroicStrike extends BaseSpell {
    public static final int COST = 2;

    public HeroicStrike(Game game) {
        super(game);
        setCost(COST);
    }

    @Override
    public void onPlay(Player player, int position) {
        super.onPlay(player,position);
        mGame.getCurrentPlayer().getHero().addTurnAttack(4);
    }
}
