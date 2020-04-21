package GameObjects.MovingGameObjects;

import Toolkit.Vector;

public class Fire extends MovingGameObject {

    private double extraRadian;
    public double timeAlive;

    public Fire(Vector position, double direction, double timeAlive) {
        super(position, "Fire", 4, 24e-4, 0);
        this.extraRadian = Math.random() * 2 * Math.PI;
        this.timeAlive = timeAlive;
        movementVelocity = Vector.radianToVector(direction + ( Math.random() - .5 ) * 2).scale(1. / 200 * Math.random());
        setSpriteFilepath("Images/Fire/fire" + (int) ( Math.random() * 10 + 1 ) + ".png");
    }

    //==================================================================================================================

    public boolean isActive() {
        return timeAlive > 0;
    }

    //==================================================================================================================

    @Override
    public void draw() {
        super.draw(getTotalVelocity().getRadian() + extraRadian);
    }
}
