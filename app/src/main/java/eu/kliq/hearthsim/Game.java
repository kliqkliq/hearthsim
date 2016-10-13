package eu.kliq.hearthsim;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.kliq.hearthsim.card.BaseCard;
import eu.kliq.hearthsim.hero.BaseHero;

public class Game {
    private Map<PLAYER, Player> mPlayersMap;
    private PLAYER mCurrentPlayerId;
    private PLAYER mNextPlayerId;
    private int mTurnNumber;
    private CardDatabase mCardDatabase;

    public enum PLAYER {
        ONE, TWO
    }

    public Game() {
        mPlayersMap = new HashMap<>();
        mCardDatabase = new CardDatabase(this);
    }

    public Player getCurrentPlayer() {
        return mPlayersMap.get(mCurrentPlayerId);
    }

    public Player getNextPlayer() {
        return mPlayersMap.get(mNextPlayerId);
    }

    /**
     * Hero attack minion
     * @param defenderPosition
     */
    public void attackMinion(int defenderPosition) {
        getCurrentPlayer().attackMinion(getNextPlayer(), defenderPosition);
    }

    /**
     * Minion attack minion
     * @param attackerPosition
     * @param defenderPosition
     */
    public void attackMinion(int attackerPosition, int defenderPosition) {
        getCurrentPlayer().attackMinion(attackerPosition, getNextPlayer(), defenderPosition);
    }

    /**
     * Hero attack hero
     */
    public void attackHero() {
        getCurrentPlayer().attackHero(getNextPlayer());
    }

    /**
     * Minion attack hero
     * @param attackerPosition
     */
    public void attackHero(int attackerPosition) {
        getCurrentPlayer().attackHero(attackerPosition, getNextPlayer());
    }

    public void nextTurn() {
        PLAYER tempPlayerId = mCurrentPlayerId;
        mCurrentPlayerId = mNextPlayerId;
        mNextPlayerId = tempPlayerId;

        if (mCurrentPlayerId == PLAYER.ONE) {
            mTurnNumber++;
        }
        System.out.println("Starting " + getCurrentPlayer().getName() + " turn (" + mTurnNumber + ")");

        getCurrentPlayer().onNextTurn(mTurnNumber);
        getNextPlayer().onNextTurn(mTurnNumber);
    }

    public void addPlayer(PLAYER id, String name, BaseHero hero) {
        Player player =  new Player(this, name, hero);
        mPlayersMap.put(id, player);
    }

    public void setCards(PLAYER id, List<String> cardIds) {
        for (String cardId : cardIds) {
            final BaseCard card = mCardDatabase.getCard(cardId);
            mPlayersMap.get(id).addCardToDeck(card);
        }
    }

    public void startGame() {
        System.out.println("Starting game");
        mPlayersMap.get(PLAYER.ONE).drawCards(3);
        mPlayersMap.get(PLAYER.TWO).drawCards(3);
        mCurrentPlayerId = PLAYER.ONE;
        mNextPlayerId = PLAYER.TWO;
        getNextPlayer().addCardToHand(mCardDatabase.getCard(Cards.THE_COIN));
        mTurnNumber = 1;
    }

    public void playCard(int cardPosition) {
        getCurrentPlayer().playCard(cardPosition);
    }

    public BaseCard getCard(String id) {
        return mCardDatabase.getCard(id);
    }
}
