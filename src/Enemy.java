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

    //==================================================================================================================


    public Enemy() {
        super("Enemy", 0, 0);
        this.maxHealth = 0;
        this.health = 0;
        this.desiredDirection = new Vector();
        this.regenHealthPerSecond = 0;
    }

    public Enemy(Vector position, double maxHealth, double maxSpeed, double mass){
        super(position, "Enemy", maxSpeed, mass);
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.desiredDirection = new Vector();
        this.regenHealthPerSecond = 0;
    }

    public Enemy(Vector position, double maxHealth, double regenHealthPerSecond, double maxSpeed, double mass){
        super(position, "Enemy", maxSpeed, mass);
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.desiredDirection = new Vector();
        this.regenHealthPerSecond = regenHealthPerSecond;
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


    //endregion

    //==================================================================================================================



}
