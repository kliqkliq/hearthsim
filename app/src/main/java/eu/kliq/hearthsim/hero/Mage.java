package eu.kliq.hearthsim.hero;

import eu.kliq.hearthsim.Player;

public class Mage extends BaseHero {

    public Mage() {
        super();
        setSkillDamage(1);
    }

    @Override
    public void onHeroPower(Player player, int position) {
        player.doDamage(position, getSkillDamage());
    }
}
