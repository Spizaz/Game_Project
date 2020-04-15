package GameStructureElements;

import Toolkit.Text;
import Toolkit.Vector;

import java.util.ArrayList;

public class SkillTree {
    //SKILLS

    /*
    private final static GameStructureElements.Skill bulletsPassThroughEnemies = new GameStructureElements.Skill();

    private final static GameStructureElements.Skill enemiesExplodeOnDeath = new GameStructureElements.Skill();

    private final static GameStructureElements.Skill fireSpreads = new GameStructureElements.Skill();

    private final static GameStructureElements.Skill enemiesStayFrozen = new GameStructureElements.Skill();

    private final static GameStructureElements.Skill killsGainHealth = new GameStructureElements.Skill();
     */

    public final static Skill shrapnelActive = new Skill(new ArrayList<>(), 0,
            new Text("Missiles, upon impact, will leave behind shrapnel for the Enemies to move across"), new Vector(-.5, -.5));
}
