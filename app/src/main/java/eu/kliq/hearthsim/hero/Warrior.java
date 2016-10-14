package eu.kliq.hearthsim.hero;

import eu.kliq.hearthsim.Player;

public class Warrior extends BaseHero {

    @Override
    public void onHeroPower(Player player, Integer position) {
        addArmor(2);
    }
}
