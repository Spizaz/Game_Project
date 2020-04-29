package GameObjects.MovingGameObjects;

import GameStructureElements.Game;
import Toolkit.Vector;

public class Blood extends MovingGameObject {

    private final double extraRadian;
    private final double minSpeed;

    public Blood(Vector position, String name, double maxSpeed, double minSpeed, double direction) {
        super(position, name, 4, maxSpeed, 0);
        this.extraRadian = Math.random() * 2 * Math.PI;
        this.minSpeed = minSpeed * Game.FRAME_DELAY;
        this.movementVelocity = Vector.radianToVector(direction + ( Math.random() - .5 ) * 2).unitVector().scale(getMaxSpeed() * Math.sqrt(Math.random() / 2 + .5));
    }

    //==================================================================================================================

    public boolean isActive() {
        return getTotalVelocity().magnitude() > minSpeed;
    }

    //==================================================================================================================

    @Override
    public void draw() {
        super.draw(getTotalVelocity().getRadian() + extraRadian);
    }
}
