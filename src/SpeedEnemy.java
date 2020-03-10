public class SpeedEnemy extends Enemy{

    public SpeedEnemy(Vector position, double maxHealth, double maxSpeed, double mass, double width, double height){
        super(position, "Speed_Enemy", maxHealth, maxSpeed, mass, width, height);
        setSpriteFilepath("Images/speed_enemy.png");
    }

    public SpeedEnemy(Vector position, double maxHealth, double regenHealthPerSecond, double maxSpeed, double mass, double width, double height){
        super(position, "Speed_Enemy", maxHealth, regenHealthPerSecond, maxSpeed, mass, width, height);
        setSpriteFilepath("Images/regen_speed_enemy.png");
    }

    //==================================================================================================================
}
