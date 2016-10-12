package eu.kliq.hearthsim;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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

        List<String> cards1 = new ArrayList<>();
        cards1.add("CS2_141"); // Ironforge Rifleman
        cards1.add("CS2_213"); // Reckless Rocketeer
        cards1.add("CS2_124"); // Wolfrider
        cards1.add("CS2_027"); // Mirror image

        mGame.setCards(Game.PLAYER.ONE, cards1);

        List<String> cards2 = new ArrayList<>();
        cards2.add("CS2_105"); // Heroic Strike
        cards2.add("CS2_124"); // Wolfrider
        cards2.add("CS2_141"); // Ironforge Rifleman
        cards2.add("CS2_213"); // Reckless Rocketeer

        mGame.setCards(Game.PLAYER.TWO, cards2);

        mGame.startGame();

        assertEquals(0, mGame.getCurrentPlayer().getMinions().size());

        mGame.playCard(0);

        assertEquals(2, mGame.getCurrentPlayer().getMinions().size());
    }
}
