public class ArmoredEnemy extends Enemy{

    public ArmoredEnemy(){
        super("Armored_Enemy");
        setSpriteFilepath("armored_enemy.png");
    }

    public ArmoredEnemy(Vector position, double maxHealth, double maxSpeed, double mass){
        super(position, "Armored_Enemy", maxHealth, maxSpeed, mass);
        setSpriteFilepath("armored_enemy.png");
    }

    public ArmoredEnemy(Vector position, double maxHealth, double regenHealthPerSecond, double maxSpeed, double mass){
        super(position, "Armored_Enemy", maxHealth, regenHealthPerSecond, maxSpeed, mass);
        setSpriteFilepath("armored_enemy.png");
    }

    //==================================================================================================================
}
