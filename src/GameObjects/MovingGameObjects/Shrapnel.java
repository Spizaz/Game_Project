package GameObjects.MovingGameObjects;

import GameObjects.GameObject;
import Toolkit.Vector;
import edu.princeton.cs.introcs.StdDraw;

public class Shrapnel extends MovingGameObject {

    /**
     * a number that determines which way the GameObjects.MovingGameObjects.Shrapnel is facing
     * for drawing purposes only
     */
    private double direction;

    //==================================================================================================================

    public Shrapnel(Vector position) {
        super(position, "GameObjects.MovingGameObjects.Shrapnel", 16, 5e-4, 1);
        setSpriteFilepath("Images/shrapnel" + (int) (Math.random() * 5 + 1) + ".png");

        direction = Math.random();
        movementVelocity = new Vector(Math.cos(direction), Math.sin(direction)).scale(getMaxSpeed());
    }

    //==================================================================================================================

    public boolean isActive(){
        return getTotalVelocity().magnitude() < getMaxSpeed() / 100;
    }

    @Override
    public void draw() {
        StdDraw.picture(getPositionX(), getPositionY(), getSpriteFilepath(), GameObject.PIXEL_SIZE * 16, GameObject.PIXEL_SIZE * 16, Math.toDegrees(direction));
    }
}
