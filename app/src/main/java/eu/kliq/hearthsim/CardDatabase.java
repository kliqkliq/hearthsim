package eu.kliq.hearthsim;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.kliq.hearthsim.card.BaseCard;
import eu.kliq.hearthsim.card.minion.BaseMinion;
import eu.kliq.hearthsim.card.spell.BaseSpell;

public class CardDatabase {
    private Game mGame;
    private Map<String, JsonCard> mCardsMap;

    public CardDatabase(final Game game) {

        mGame = game;

        final String data = JsonHelper.getJSON();
        final Gson gson = new Gson();
        final Type collectionType = new TypeToken<List<JsonCard>>(){}.getType();
        final List<JsonCard> cards = gson.fromJson(data, collectionType);

        mCardsMap = new HashMap<>();
        for (final JsonCard card : cards) {
            mCardsMap.put(card.getId(), card);
        }
        System.out.println("[CardDatabase] cards in JSON: " + cards.size() + ", cards added: " + mCardsMap.size());
    }

    public BaseCard getCard(final String id) {
        System.out.println("[CardDatabase] getCard(" + id + ")");
        final JsonCard jsonCard = mCardsMap.get(id);
        final String cardType = jsonCard.getType().toLowerCase();
        final String cardSet = jsonCard.getSet().toLowerCase();
        Class<?> cardClass = null;
        BaseCard card = null;

        try {
            cardClass = Class.forName("eu.kliq.hearthsim.card." + cardType + "." + cardSet + "." + id);
        } catch (ClassNotFoundException e) {
            if (cardType.equals("spell")) {
                card = new BaseSpell(mGame);
            } else if (cardType.equals("minion")) {
                card = new BaseMinion(mGame);
            }
        }

        if (cardClass != null) {
            try {
                card = (BaseCard) cardClass.getConstructor(Game.class).newInstance(mGame);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        card.setCost(jsonCard.getCost());

        if (card instanceof BaseMinion) {
            BaseMinion minion = (BaseMinion) card;
            minion.setAttack(jsonCard.getAttack());
            minion.setHealth(jsonCard.getHealth());
            minion.setCharge(jsonCard.getMechanics().contains("CHARGE"));
            minion.setTaunt(jsonCard.getMechanics().contains("TAUNT"));
        }

        return card;
    }
}
