package GameStructureElements;

import Toolkit.Text;
import Toolkit.Vector;

import java.util.List;

public class Skill {

    /**
     * the number of skill points it takes to buy this GameStructureElements.Skill
     */
    private int cost;

    /**
     * whether or not the GameStructureElements.Skill is being used
     */
    private boolean active;

    /**
     * the Skills that are needed to unlock this GameStructureElements.Skill
     */
    private List<Skill> prerequisiteSkills;

    /**
     * a little description of the skill
     */
    private Text description;

    /**
     * where the skill will be physically located in the skill tree
     */
    private Vector position;

    //==================================================================================================================

    public Skill(List<Skill> prerequisiteSkills, int cost, Text description, Vector position) {
        this.prerequisiteSkills = prerequisiteSkills;
        this.cost = cost;
        this.active = false;
        this.description = description;
        this.position = position;
    }

    //==================================================================================================================

    //region Gets and Sets


    public int getCost() {
        return cost;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Skill> getPrerequisiteSkills() {
        return prerequisiteSkills;
    }

    public Text getDescription() {
        return description;
    }

    public Vector getPosition() {
        return position;
    }


    //endregion

    //==================================================================================================================

    /**
     * determines if the GameObjects.MovingGameObjects.Fighter can buy this certain GameStructureElements.Skill
     *
     * @param unusedSkillPoints - the number of unused GameStructureElements.Skill points the GameObjects.MovingGameObjects.Fighter has
     * @return true if can buy - false otherwise
     */
    public boolean isBuyable(int unusedSkillPoints) {
        // TODO: 3/8/2020
        return false;
    }
}
