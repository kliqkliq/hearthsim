package eu.kliq.hearthsim;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import eu.kliq.hearthsim.card.BaseCard;
import eu.kliq.hearthsim.card.minion.BaseMinion;
import eu.kliq.hearthsim.hero.BaseHero;

public class Player {
    private int mMana;
    private int mMaxMana;
    private int mManaModifier;
    private Game mGame;
    private List<BaseMinion> mMinions;
    private Stack<BaseCard> mDeckCards;
    private List<BaseCard> mHandCards;
    private BaseHero mHero;
    private String mName;

    Player(Game game, String name, BaseHero hero) {
        mGame = game;
        mName = name;
        mHero = hero;
        mMinions = new ArrayList<>();
        mDeckCards = new Stack();
        mHandCards = new ArrayList<>();
    }

    public void onNextTurn(int turnNumber) {
        mMana = turnNumber + mManaModifier;
        for (BaseMinion minion : mMinions) {
            minion.onNextTurn();
        }
        mHero.onNextTurn();
    }

    public void addCard(BaseCard card) {
        mDeckCards.push(card);
    }

    public void addMinion(String id) {
        mMinions.add((BaseMinion) mGame.getCard(id));
    }

    public void drawCards(int number) {
        for (int i = 0; i < number; i++) {
            mHandCards.add(mDeckCards.pop());
        }
    }

    public void playCard(int cardPosition) {
        mHandCards.get(cardPosition).onPlay(null, 0);
    }

    public List<BaseMinion> getMinions() {
        return mMinions;
    }

    public BaseHero getHero() {
        return mHero;
    }

    public void setHero(BaseHero hero) {
        mHero = hero;
    }

    public void spendMana(int cost) {
        mMana -= cost;
    }

    public void destroyMinion(int position) {
        mMinions.remove(position);
    }

    public void doDamage(int position, int damage) {
        mMinions.get(position).onDamage(damage);
    }

    public void doSpellDamage(int position, int damage) {
        int finalDamage = damage; // LOOP mMinions for spell damage
        mMinions.get(position).onDamage(finalDamage);
    }

    /**
     * This player's hero will attack opponent's minion
     * @param opponentPlayer
     * @param defenderPosition
     */
    public void attackMinion(Player opponentPlayer, int defenderPosition) {
        opponentPlayer.onMinionAttack(mHero, defenderPosition);
    }

    /**
     * When opponent's hero attacks this player minion
     * @param attackerHero
     * @param defenderPosition
     */
    public void onMinionAttack(BaseHero attackerHero, int defenderPosition) {
        BaseMinion defenderMinion = mMinions.get(defenderPosition);
        defenderMinion.onAttack(attackerHero.getAttack());
        attackerHero.onAttack(defenderMinion.getAttack());
    }

    /**
     * This player's minion will attack opponent's minion
     * @param attackerPosition
     * @param opponentPlayer
     * @param defenderPosition
     */
    public void attackMinion(int attackerPosition, Player opponentPlayer, int defenderPosition) {
        opponentPlayer.onMinionAttack(mMinions.get(attackerPosition), defenderPosition);
    }

    /**
     * When opponent's minion attacks this player minion
     * @param attackerMinion
     * @param defenderPosition
     */
    public void onMinionAttack(BaseMinion attackerMinion, int defenderPosition) {
        BaseMinion defenderMinion = mMinions.get(defenderPosition);
        defenderMinion.onAttack(attackerMinion.getAttack());
        attackerMinion.onAttack(defenderMinion.getAttack());
    }

    /**
     * This player's hero will attack opponent's hero
     * @param opponentPlayer
     */
    public void attackHero(Player opponentPlayer) {
        opponentPlayer.onHeroAttack(mHero);
    }

    /**
     * When opponent's hero attacks this player hero
     * @param opponentHero
     */
    private void onHeroAttack(BaseHero opponentHero) {
        mHero.onAttack(opponentHero.getAttack());
    }

    /**
     * This player's minion will attack opponent's hero
     * @param attackerPosition
     * @param opponentPlayer
     */
    public void attackHero(int attackerPosition, Player opponentPlayer) {
        opponentPlayer.onHeroAttack(mMinions.get(attackerPosition));
    }

    /**
     * When opponent's minion attacks this player hero
     * @param minion
     */
    private void onHeroAttack(BaseMinion minion) {
        mHero.onAttack(minion.getAttack());
    }
}
