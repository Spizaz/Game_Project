public class SpeedEnemy extends Enemy{

    public SpeedEnemy(){
        super("Speed_Enemy");
        setSpriteFilepath("speed_enemy.png");
    }

    public SpeedEnemy(Vector position, double maxHealth, double maxSpeed, double mass){
        super(position, "Speed_Enemy", maxHealth, maxSpeed, mass);
        setSpriteFilepath("speed_enemy.png");
    }

    public SpeedEnemy(Vector position, double maxHealth, double regenHealthPerSecond, double maxSpeed, double mass){
        super(position, "Speed_Enemy", maxHealth, regenHealthPerSecond, maxSpeed, mass);
        setSpriteFilepath("speed_enemy.png");
    }

    //==================================================================================================================
}
