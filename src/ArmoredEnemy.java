public class ArmoredEnemy extends Enemy{

    public ArmoredEnemy(Vector position, double maxHealth, double maxSpeed, double mass){
        super(position, "Armored_Enemy", maxHealth, maxSpeed, mass);
        setSpriteFilepath("Images/armored_enemy.png");
    }

    public ArmoredEnemy(Vector position, double maxHealth, double regenHealthPerSecond, double maxSpeed, double mass){
        super(position, "Armored_Enemy", maxHealth, regenHealthPerSecond, maxSpeed, mass);
        setSpriteFilepath("Images/regen_armored_enemy.png");
    }

    //==================================================================================================================
}
