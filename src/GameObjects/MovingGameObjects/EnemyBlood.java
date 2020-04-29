package GameObjects.MovingGameObjects;

import Toolkit.Vector;

public class EnemyBlood extends Blood {

    public EnemyBlood(Vector position, double maxSpeed, double minSpeed, double direction) {
        super(position, "Enemy_Blood", maxSpeed, minSpeed, direction);
        setSpriteFilepath("Images/enemy_blood.png");
    }
}
