package GameObjects.MovingGameObjects;

import GameStructureElements.Game;
import Toolkit.Vector;

public class Experience extends MovingGameObject{

    private int experience;

    //==================================================================================================================

    public Experience(Vector position, int experience) {
        super(position, "Experience", 8, 1e-3, 0);
        this.experience = experience;
        setSpriteFilepath("Images/experience.png");

        double radian = Math.random() * Math.PI * 2;
        movementVelocity = new Vector(Math.cos(radian), Math.sin(radian)).scale(3e-4 * Game.FRAME_DELAY);
    }

    //==================================================================================================================

    public int getExperience() {
        return experience;
    }

    //==================================================================================================================

    public void move(Vector fighterPosition){
        double distance = getPosition().difference(fighterPosition);

        if(distance < .4)
            setAcceleration(getPosition().differenceVector(fighterPosition).scale(3e-10 / Math.pow(distance, 10)));

        super.move(null, true);
    }

    @Override
    public void draw() {
        super.draw(getTotalVelocity().getRadian());
    }
}
