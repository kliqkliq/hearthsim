package eu.kliq.hearthsim;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import eu.kliq.hearthsim.card.minion.BaseMinion;
import eu.kliq.hearthsim.hero.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameTest {
    private static final String PLAYER_1_NAME = "Mage";
    private static final String PLAYER_2_NAME = "Warrior";
    private Game mGame;
    private Player player;

    @Test
    public void test1() {
        mGame = new Game();
        mGame.addPlayer(Game.PLAYER.ONE, PLAYER_1_NAME, new Mage());
        mGame.addPlayer(Game.PLAYER.TWO, PLAYER_2_NAME, new Warrior());

        List<String> cards1 = new ArrayList<>();
        cards1.add(Cards.IRONFORGE_RIFLEMAN);
        cards1.add(Cards.RECKLESS_ROCKETEER); // 2
        cards1.add(Cards.WOLFRIDER); // 1
        cards1.add(Cards.MIRROR_IMAGE_SPELL); // 0

        mGame.setCards(Game.PLAYER.ONE, cards1);

        List<String> cards2 = new ArrayList<>();
        cards2.add(Cards.HEROIC_STRIKE);
        cards2.add(Cards.WOLFRIDER);
        cards2.add(Cards.IRONFORGE_RIFLEMAN);// 2
        cards2.add(Cards.FIERY_WAR_AXE); // 1
        cards2.add(Cards.RECKLESS_ROCKETEER); // 0

        mGame.setCards(Game.PLAYER.TWO, cards2);

        mGame.startGame();

        /**
         *  Mage turn 1
         */
        player = mGame.getCurrentPlayer();
        assertEquals(0, player.getMinions().size());
        assertEquals(1, player.getMana());
        assertEquals(3, player.getHandCards().size());
        // Cast Mirror Image spell
        mGame.playCard(0);
        assertEquals(2, player.getMinions().size());
        assertEquals(0, player.getMana());
        assertEquals(2, player.getHandCards().size());
        BaseMinion minion = player.getMinions().get(0);
        assertEquals(0, minion.getAttack());
        assertEquals(2, minion.getHealth());
        assertTrue(minion.isTaunt());

        /**
         *  Warrior turn 1
         */
        mGame.nextTurn();
        player = mGame.getCurrentPlayer();
        assertEquals(0, player.getMinions().size());
        assertEquals(1, player.getMana());
        assertEquals(4, player.getHandCards().size());
        // Cast The Coin spell
        mGame.playCard(3);
        assertEquals(2, player.getMana());
        assertEquals(3, player.getHandCards().size());
        // Play Fiery War Axe
        mGame.playCard(1);
        assertEquals(0, player.getMana());
        assertEquals(2, player.getHandCards().size());
        // Attack Mirror image minion
        mGame.attackMinion(1);
        assertEquals(1, player.getHero().getWeapon().getDurability());

        /**
         *  Mage turn 2
         */
        mGame.nextTurn();
        player = mGame.getCurrentPlayer();
        assertEquals(1, player.getMinions().size());
        assertEquals(2, player.getMana());
        assertEquals(30, mGame.getNextPlayer().getHero().getHealth());
        // Use hero power on opponent hero
        mGame.useHeroPower(mGame.getNextPlayer());
        assertEquals(0, player.getMana());
        assertEquals(29, mGame.getNextPlayer().getHero().getHealth());

        /**
         *  Warrior turn 2
         */
        mGame.nextTurn();
        player = mGame.getCurrentPlayer();
        assertEquals(2, player.getMana());
    }
}
