package eu.kliq.hearthsim;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import eu.kliq.hearthsim.card.BaseCard;
import eu.kliq.hearthsim.card.minion.core.*;
import eu.kliq.hearthsim.card.spell.core.*;
import eu.kliq.hearthsim.hero.*;

import static org.junit.Assert.assertEquals;

public class GameTest {
    private static final String PLAYER_1_NAME = "Player1";
    private static final String PLAYER_2_NAME = "Player2";
    private Game mGame;

    @Test
    public void test1() {
        mGame = new Game();
        mGame.addPlayer(Game.PLAYER.ONE, PLAYER_1_NAME, new Mage());
        mGame.addPlayer(Game.PLAYER.TWO, PLAYER_2_NAME, new Warrior());

        List<Class<? extends BaseCard>> cards1 = new ArrayList<>();
        cards1.add(IronforgeRifleman.class);
        cards1.add(RecklessRocketeer.class);
        cards1.add(Wolfrider.class);
        cards1.add(MirrorImageSpell.class);

        mGame.setCards(Game.PLAYER.ONE, cards1);

        List<Class<? extends BaseCard>> cards2 = new ArrayList<>();
        cards2.add(HeroicStrike.class);
        cards2.add(Wolfrider.class);
        cards2.add(IronforgeRifleman.class);
        cards2.add(RecklessRocketeer.class);

        mGame.setCards(Game.PLAYER.TWO, cards2);

        mGame.startGame();

        assertEquals(0, mGame.getCurrentPlayer().getMinions().size());

        mGame.playCard(0);

        assertEquals(2, mGame.getCurrentPlayer().getMinions().size());
    }
}
