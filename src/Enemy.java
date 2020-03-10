public class Enemy extends MovingGameObject{

    /**
     * the maximum health of the Enemy
     */
    private double maxHealth;

    /**
     * the amount of health that the Enemy has - when at 0 the Enemy is dead
     */
    private double health;

    /**
     * the direction unit Vector to the place the Enemy wants to travel to
     */
    private Vector desiredDirection;

    /**
     * the amount of health that the Enemy will regenerate every second - 0 for starting Enemies
     */
    private double regenHealthPerSecond;

    /**
     * the Weapon that the enemy has to attack the Fighter
     */
    private Weapon weapon;

    //==================================================================================================================

    public Enemy() {
        super("Enemy");
        this.maxHealth = 0;
        this.health = 0;
        this.desiredDirection = new Vector();
        this.regenHealthPerSecond = 0;
        this.weapon = null;
        setSpriteFilepath("Images/enemy.png");
    }

    public Enemy(Vector position, double maxHealth, double maxSpeed, double mass, double width, double height){
        super(position, "Enemy", maxSpeed, mass, width, height);
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.desiredDirection = new Vector();
        this.regenHealthPerSecond = 0;
        this.weapon = null;
        setSpriteFilepath("Images/enemy.png");
    }

    public Enemy(Vector position, double maxHealth, double maxSpeed, double mass, Weapon weapon, double width, double height){
        super(position, "Enemy", maxSpeed, mass, width, height);
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.desiredDirection = new Vector();
        this.regenHealthPerSecond = 0;
        this.weapon = weapon;
        setSpriteFilepath("Images/enemy.png");
    }

    public Enemy(Vector position, String name, double maxHealth, double maxSpeed, double mass, double width, double height){
        super(position, name, maxSpeed, mass, width, height);
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.desiredDirection = new Vector();
        this.regenHealthPerSecond = 0;
        this.weapon = null;
    }
    public Enemy(Vector position, String name, double maxHealth, double maxSpeed, double mass, Weapon weapon, double width, double height){
        super(position, name, maxSpeed, mass, width, height);
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.desiredDirection = new Vector();
        this.regenHealthPerSecond = 0;
        this.weapon = weapon;
    }

    public Enemy(Vector position, double maxHealth, double regenHealthPerSecond, double maxSpeed, double mass, double width, double height){
        super(position, "Enemy", maxSpeed, mass, width, height);
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.desiredDirection = new Vector();
        this.regenHealthPerSecond = regenHealthPerSecond;
        this.weapon = null;
        setSpriteFilepath("Images/regen_enemy.png");
    }

    public Enemy(Vector position, double maxHealth, double regenHealthPerSecond, double maxSpeed, double mass, Weapon weapon, double width, double height){
        super(position, "Enemy", maxSpeed, mass, width, height);
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.desiredDirection = new Vector();
        this.regenHealthPerSecond = regenHealthPerSecond;
        this.weapon = weapon;
        setSpriteFilepath("Images/regen_armored_enemy.png");
    }

    public Enemy(Vector position, String name, double maxHealth, double regenHealthPerSecond, double maxSpeed, double mass, double width, double height){
        super(position, name, maxSpeed, mass, width, height);
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.desiredDirection = new Vector();
        this.regenHealthPerSecond = regenHealthPerSecond;
        this.weapon = null;
    }

    public Enemy(Vector position, String name, double maxHealth, double regenHealthPerSecond, double maxSpeed, double mass, Weapon weapon, double width, double height){
        super(position, name, maxSpeed, mass, width, height);
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.desiredDirection = new Vector();
        this.regenHealthPerSecond = regenHealthPerSecond;
        this.weapon = weapon;
    }

    //==================================================================================================================

    //region Gets and Sets


    public double getMaxHealth() {
        return maxHealth;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getRegenHealthPerSecond() {
        return regenHealthPerSecond;
    }

    public int getExperienceOnDeath(){
        return (int) Math.ceil( (getMaxHealth() / 100) + getMaxSpeed() + (getRegenHealthPerSecond() * 100) );
    }

    public int getMoneyOnDeath(){
        return (int) Math.ceil( (getMaxHealth() / 100) + getMaxSpeed() + (getRegenHealthPerSecond() * 100) );
    }

    public Weapon getWeapon(){
        return weapon;
    }


    //endregion

    //==================================================================================================================



}
