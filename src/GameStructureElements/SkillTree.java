package GameStructureElements;

import Toolkit.Text;
import Toolkit.Vector;

import java.util.ArrayList;

public class SkillTree {
    //SKILLS

    /*
    private final static Skill bulletsPassThroughEnemies = new GameStructureElements.Skill();

    private final static Skill enemiesExplodeOnDeath = new GameStructureElements.Skill();

    private final static Skill fireSpreads = new GameStructureElements.Skill();

    private final static Skill enemiesStayFrozen = new GameStructureElements.Skill();

    private final static Skill killsGainHealth = new GameStructureElements.Skill();
     */

    public final static Skill shrapnelActive = new Skill(new ArrayList<>(), 0,
            new Text("Missiles, upon impact, will leave behind shrapnel for the Enemies to move across", 20), new Vector(-.5, -.5));
}
