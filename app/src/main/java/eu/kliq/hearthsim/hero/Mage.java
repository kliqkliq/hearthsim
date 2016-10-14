package eu.kliq.hearthsim.hero;

import eu.kliq.hearthsim.Player;

public class Mage extends BaseHero {

    public Mage() {
        super();
        setSkillDamage(1);
    }

    @Override
    public void onHeroPower(Player player, Integer position) {
        super.onHeroPower(player, position);
        if (position == null) {
            player.doHeroDamage(getSkillDamage());
        } else {
            player.doMinionDamage(position, getSkillDamage());
        }
    }
}
