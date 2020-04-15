package GameObjects.MovingGameObjects;

import GameObjects.StationaryGameObjects.Weapon;
import Toolkit.Vector;
import edu.princeton.cs.introcs.StdDraw;

public class Enemy extends MovingGameObject {

    /**
     * the maximum health of the GameObjects.MovingGameObjects.Enemy
     */
    private double maxHealth;

    /**
     * the amount of health that the GameObjects.MovingGameObjects.Enemy has - when at 0 the GameObjects.MovingGameObjects.Enemy is dead
     */
    private double health;

    /**
     * the direction unit Toolkit.Vector to the place the GameObjects.MovingGameObjects.Enemy wants to travel to
     */
    private Vector desiredDirection;

    /**
     * the amount of health that the GameObjects.MovingGameObjects.Enemy will regenerate every second - 0 for starting Enemies
     */
    private double regenHealthPerSecond;

    /**
     * the GameObjects.StationaryGameObjects.Weapon that the enemy has to attack the GameObjects.MovingGameObjects.Fighter
     */
    private Weapon weapon;

    //==================================================================================================================

    public Enemy(Vector position, double maxHealth, double maxSpeed, double mass) {
        super(position, "GameObjects.MovingGameObjects.Enemy", 32, maxSpeed, mass);
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.desiredDirection = new Vector();
        this.regenHealthPerSecond = 0;
        this.weapon = null;
        setSpriteFilepath("Images/enemy.png");
    }

    public Enemy(Vector position, double maxHealth, double maxSpeed, double mass, Weapon weapon) {
        super(position, "GameObjects.MovingGameObjects.Enemy", 32, maxSpeed, mass);
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
        super(position, "GameObjects.MovingGameObjects.Enemy", 32, maxSpeed, mass);
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.desiredDirection = new Vector();
        this.regenHealthPerSecond = regenHealthPerSecond;
        this.weapon = null;
        setSpriteFilepath("Images/regen_enemy.png");
    }

    public Enemy(Vector position, double maxHealth, double regenHealthPerSecond, double maxSpeed, double mass, Weapon weapon) {
        super(position, "GameObjects.MovingGameObjects.Enemy", 32, maxSpeed, mass);
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
        desiredDirection = this.getPosition().differenceVector(fighterPosition).unitVector();
    }

    public Vector getDesiredDirection(){
        return desiredDirection;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public double getHealth() {
        if(health > maxHealth) health = maxHealth;
        else if(health < 0) health = 0;

        return health;
    }

    public void addHealth(double healthToBeAdded){
        if(health > 0){
            health += healthToBeAdded;
        }
    }

    public boolean isAlive(){
        return getHealth() > 0;
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
        return getMaxSpeed() * ( .005 );
    }

    /**
     * an equation that determines the GameObjects.MovingGameObjects.Enemy's added difficulty to the game
     *
     * @return a double representing how much this enemy's presence makes the game more difficult
     */
    public double getDifficulty() {
        return( getMaxHealth() / 100 ) +
                        ( getMaxSpeed() / getMass() * 1e5 ) +

                        //if the weapon doesn't exist - don't add to difficulty
                        ( ( hasWeapon() ) ? getWeapon().getDamagePerSecond() : 0 ) +
                        ( getRegenHealthPerSecond() / 10);
    }

    /**
     * @return true if the GameObjects.MovingGameObjects.Enemy has a GameObjects.StationaryGameObjects.Weapon
     */
    public boolean hasWeapon() {
        return getWeapon() != null;
    }


    //endregion

    //==================================================================================================================

    public void drawHealthBar(){
        StdDraw.setPenColor(StdDraw.RED);

        double lineLength = getHealth() / 1000;
        StdDraw.filledRectangle(getPositionX(), getPositionY() - .06, lineLength / 2, .005);

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.rectangle(getPositionX(), getPositionY() - .06, lineLength / 2, .005);
    }

    public void draw(){
        double radian = getDesiredDirection().getRadian();
        super.draw(radian);
        drawHealthBar();
        if(hasWeapon()) weapon.draw(radian);
    }


}
