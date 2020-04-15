package GameObjects.MovingGameObjects;

import Toolkit.Vector;

public class SpeedEnemy extends Enemy {

    public SpeedEnemy(Vector position, double maxHealth, double maxSpeed, double mass) {
        super(position, "Speed_Enemy", maxHealth, maxSpeed, mass);
        setSpriteFilepath("Images/speed_enemy.png");
    }

    public SpeedEnemy(Vector position, double maxHealth, double regenHealthPerSecond, double maxSpeed, double mass) {
        super(position, "Speed_Enemy", maxHealth, regenHealthPerSecond, maxSpeed, mass);
        setSpriteFilepath("Images/regen_speed_enemy.png");
    }

    //==================================================================================================================
}
