package eu.kliq.hearthsim.card.spell.core;

import eu.kliq.hearthsim.Game;
import eu.kliq.hearthsim.Player;
import eu.kliq.hearthsim.card.minion.core.MirrorImageMinion;
import eu.kliq.hearthsim.card.spell.BaseSpell;

public class MirrorImageSpell extends BaseSpell {
    public static final int COST = 1;

    public MirrorImageSpell(Game game) {
        super(game);
        setCost(COST);
    }

    @Override
    public void onPlay(Player player, int position) {
        super.onPlay(player, position);
        mGame.getCurrentPlayer().addMinion(MirrorImageMinion.class);
        mGame.getCurrentPlayer().addMinion(MirrorImageMinion.class);
    }
}
