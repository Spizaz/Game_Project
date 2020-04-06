public class Enemy extends MovingGameObject {

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

    public Enemy(Vector position, double maxHealth, double maxSpeed, double mass) {
        super(position, "Enemy", 32, maxSpeed, mass);
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.desiredDirection = new Vector();
        this.regenHealthPerSecond = 0;
        this.weapon = null;
        setSpriteFilepath("Images/enemy.png");
    }

    public Enemy(Vector position, double maxHealth, double maxSpeed, double mass, Weapon weapon) {
        super(position, "Enemy", 32, maxSpeed, mass);
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.desiredDirection = new Vector();
        this.regenHealthPerSecond = 0;
        this.weapon = weapon;
        setSpriteFilepath("Images/enemy.png");
    }

    public Enemy(Vector position, String name, double maxHealth, double maxSpeed, double mass) {
        super(position, name, 32, maxSpeed, mass);
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.desiredDirection = new Vector();
        this.regenHealthPerSecond = 0;
        this.weapon = null;
    }

    public Enemy(Vector position, String name, double maxHealth, double maxSpeed, double mass, Weapon weapon) {
        super(position, name, 32, maxSpeed, mass);
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.desiredDirection = new Vector();
        this.regenHealthPerSecond = 0;
        this.weapon = weapon;
    }

    public Enemy(Vector position, double maxHealth, double regenHealthPerSecond, double maxSpeed, double mass) {
        super(position, "Enemy", 32, maxSpeed, mass);
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.desiredDirection = new Vector();
        this.regenHealthPerSecond = regenHealthPerSecond;
        this.weapon = null;
        setSpriteFilepath("Images/regen_enemy.png");
    }

    public Enemy(Vector position, double maxHealth, double regenHealthPerSecond, double maxSpeed, double mass, Weapon weapon) {
        super(position, "Enemy", 32, maxSpeed, mass);
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.desiredDirection = new Vector();
        this.regenHealthPerSecond = regenHealthPerSecond;
        this.weapon = weapon;
        setSpriteFilepath("Images/regen_armored_enemy.png");
    }

    public Enemy(Vector position, String name, double maxHealth, double regenHealthPerSecond, double maxSpeed, double mass) {
        super(position, name, 32, maxSpeed, mass);
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.desiredDirection = new Vector();
        this.regenHealthPerSecond = regenHealthPerSecond;
        this.weapon = null;
    }

    public Enemy(Vector position, String name, double maxHealth, double regenHealthPerSecond, double maxSpeed, double mass, Weapon weapon) {
        super(position, name, 32, maxSpeed, mass);
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.desiredDirection = new Vector();
        this.regenHealthPerSecond = regenHealthPerSecond;
        this.weapon = weapon;
    }

    //==================================================================================================================

    //region Gets and Sets


    public void setDesiredDirection(Vector fighterPosition) {

        //if the Enemy has a Weapon
        if (hasWeapon()) {

        }

        //the Enemy does not have a Weapon
        else {
            desiredDirection = this.getPosition().differenceVector(fighterPosition).unitVector();
        }
    }

    public Vector getDesiredDirection(){
        return desiredDirection;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public double getHealth() {
        return health;
    }

    public void addHealth(double healthToBeAdded){
        health += healthToBeAdded;

        if(health > getMaxHealth()) health = getMaxHealth();
        else if(health < 0) health = 0;
    }

    public boolean isAlive(){
        return getHealth() > 0;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getRegenHealthPerSecond() {
        return regenHealthPerSecond;
    }

    public int getExperienceOnDeath() {
        return (int) Math.ceil(( getMaxHealth() / 100 ) + getMaxSpeed() + ( getRegenHealthPerSecond() * 100 ));
    }

    public int getMoneyOnDeath() {
        return (int) Math.ceil(( getMaxHealth() / 100 ) + getMaxSpeed() + ( getRegenHealthPerSecond() * 100 ));
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public double getMaxAcceleration() {
        return getMaxSpeed() * ( .0025 + 0 );
    }

    /**
     * an equation that determines the Enemy's added difficulty to the game
     *
     * @return a double representing how much this enemy's presence makes the game more difficult
     */
    public double getDifficulty() {
        return
                ( maxHealth / 100. ) +
                        ( getMaxSpeed() / getMass() * 1e5 ) +
                        //if the weapon doesn't exist - don't add to difficulty
                        ( ( hasWeapon() ) ?
                                weapon.getDamagePerSecond()
                                : 0 );
    }

    /**
     * @return true if the Enemy has a Weapon
     */
    public boolean hasWeapon() {
        return weapon != null;
    }


    //endregion

    //==================================================================================================================

    public void drawHealthBar(){
        StdDraw.setPenColor(StdDraw.RED);

        double lineLength = health / 1000;
        StdDraw.filledRectangle(getPositionX(), getPositionY() - .06, lineLength / 2, .005);

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.rectangle(getPositionX(), getPositionY() - .06, lineLength / 2, .005);
    }

    public void draw(){
        super.draw(desiredDirection.getRadian());
        drawHealthBar();
    }


}
