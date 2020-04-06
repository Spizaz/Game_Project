import java.awt.*;
import java.util.ArrayList;

public class SkillTree {
    //SKILLS

    /*
    private final static Skill bulletsPassThroughEnemies = new Skill();

    private final static Skill enemiesExplodeOnDeath = new Skill();

    private final static Skill fireSpreads = new Skill();

    private final static Skill enemiesStayFrozen = new Skill();

    private final static Skill killsGainHealth = new Skill();
     */

    public final static Skill shrapnelActive = new Skill(new ArrayList<>(), 0,
            new Text("Missiles, upon impact, will leave behind shrapnel for the Enemies to move across"), new Vector(-.5, -.5));
}
