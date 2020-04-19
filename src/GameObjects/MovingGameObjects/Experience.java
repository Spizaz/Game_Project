package GameObjects.MovingGameObjects;

import Toolkit.Vector;

public class Experience extends MovingGameObject{

    public Experience(Vector position) {
        super(position, "Experience", 16, 16e-5, 0);
        setSpriteFilepath("Images/experience.png");
    }

    //==================================================================================================================

    @Override
    public void draw() {
        super.draw(getTotalVelocity().getRadian());
    }
}
