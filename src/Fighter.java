import java.util.ArrayList;
import java.util.List;

public class Fighter extends MovingGameObject {

    /**
     * the total experience the Fighter has accrued
     */
    private int totalExperience;

    /**
     * the amount of experience that the Fighter has on the level it is currently at
     */
    private int levelExperience;

    /**
     * the amount of levelExperience need to level up
     */
    private int experienceToLevelUp;

    /**
     * the level that the Fighter is currently on
     */
    private int level;

    /**
     * the amount of money that the Fighter has to make purchases
     */
    private int money;

    /**
     * the amount of health that the Fighter has - @ 0 game over
     */
    private double health;

    /**
     * the maximum amount of health the Fighter can have
     */
    private double maxHealth;

    /**
     * the amount of health the Fighter gains per second
     */
    private double regenHealthPerSecond;

    /**
     * all of the weapons the Fighter has equipped
     */
    private Weapon[] weapons;

    /**
     * all of the powerUps that are currently active for the Fighter
     */
    private List<PowerUp> activePowerUps;

    /**
     * the number of upgrade points the user has spent on increasing the max speed of the Fighter
     */
    private int maxSpeedUpgradePoints;

    /**
     * the number of upgrade points the user has spent on increasing the max health of the Fighter
     */
    private int maxHealthUpgradePoints;

    /**
     * the number of upgrade points the user has spent on increasing the regeneration of the health of the Fighter
     */
    private int healthRegenUpgradePoints;

    /**
     * the number of upgrade points the user has spent on increasing the money gained from killing enemies
     */
    private int moneyPerKillUpgradePoints;

    /**
     * the number of upgrade points the user has spent on increasing the length of time each powerUp lasts
     */
    private int powerUpTimeLengthUpgradePoints;

    /**
     * the number of upgrade points the user has spent on increasing the effectiveness of each powerUp
     */
    private int powerUpEffectivenessUpgradePoints;

    /**
     * the number of upgrade points the user has spent on increasing the max speed of the Fighter
     */
    private int healthGlobeEffectivenessUpgradePoints;

    /**
     * the number of upgrade points the user has spent on increasing the max speed of the Fighter
     */
    private int experienceGlobeEffectivenessUpgradePoints;

    /**
     * the number of upgrade points the user has spent on increasing the max speed of the Fighter
     */
    private int coinValueUpgradePoints;

    /**
     * the number of upgrade points that the Fighter has yet to spend
     */
    private int unusedUpgradePoints;

    /**
     * the number of skill points the Fighter has yet to spend
     */
    private int unusedSkillPoints;

    //==================================================================================================================

    public Fighter(Vector position) throws InterruptedException {
        // TODO: 3/8/2020 may need to edit the stats one line below
        super(position, "Fighter", 32, 12e-5, 100);
        this.totalExperience = 0;
        this.levelExperience = 0;
        this.experienceToLevelUp = 25;
        this.level = 5;
        this.money = 0; // TODO: 3/8/2020 give Fighter enough money to buy a basic gun
        this.health = 100;
        this.maxHealth = 100;
        this.regenHealthPerSecond = 0;
        this.weapons = new Weapon[4];
        this.activePowerUps = new ArrayList<>();
        this.maxSpeedUpgradePoints = 0;
        this.maxHealthUpgradePoints = 0;
        this.healthRegenUpgradePoints = 0;
        this.moneyPerKillUpgradePoints = 0;
        this.powerUpTimeLengthUpgradePoints = 0;
        this.powerUpEffectivenessUpgradePoints = 0;
        this.healthGlobeEffectivenessUpgradePoints = 0;
        this.experienceGlobeEffectivenessUpgradePoints = 0;
        this.coinValueUpgradePoints = 0;
        this.unusedUpgradePoints = 0;
        this.unusedSkillPoints = 0;
        setSpriteFilepath("Images/fighter.png");

        //weapons[0] = new MachineGun(.5, 0, .05, .01, 0, 0, 25, 500);
        weapons[0] = new MissileLauncher(.5, 0, .05, .05, 0, 0, 25, 1000);
        //SkillTree.shrapnelActive.setActive(true);
    }

    //==================================================================================================================

    //region Gets, Sets, and Adds

    /**
     * @return the Vector that points towards the Mouse from the Fighter
     */
    public Vector getDirection() {
        Vector direction = getPosition().differenceVector(new Vector(StdDraw.mouseX(), StdDraw.mouseY()));

        //if the game can't find the mouse for some reason
        if (direction.magnitude() == 0) return new Vector(1, 0);

        return direction;
    }

    public double getMaxSpeed() {
        return super.getMaxSpeed() * ( 1 + getMaxSpeedUpgradePoints() / 5. );
    }

    public double getMaxAcceleration() {
        return getMaxSpeed() * .005;
    }

    public void setMovementAcceleration(){
        Vector acceleration = new Vector();
        //W
        if (StdDraw.isKeyPressed(87)) {
            acceleration.addY(getMaxAcceleration());
        }
        //S
        if (StdDraw.isKeyPressed(83)) {
            acceleration.addY(-getMaxAcceleration());
        }
        //D
        if (StdDraw.isKeyPressed(68)) {
            acceleration.addX(getMaxAcceleration());
        }
        //A
        if (StdDraw.isKeyPressed(65)) {
            acceleration.addX(-getMaxAcceleration());
        }

        setAcceleration(acceleration);
    }

    public int getTotalExperience() {
        return totalExperience;
    }

    public int getLevelExperience() {
        return levelExperience;
    }

    public void addLevelExperience(int experienceToBeAdded) {
        this.levelExperience += experienceToBeAdded;
        this.totalExperience += experienceToBeAdded;
    }

    public int getExperienceToLevelUp() {
        // TODO: 3/8/2020 add some equation to calculate the experience needed y = 25 * 2 ^ current level
        return 0;
    }

    public int getLevel() {
        return level;
    }

    public int getMoney() {
        return money;
    }

    public void addMoney(int moneyToBeAdded) {
        this.money += moneyToBeAdded;
    }

    public double getHealth() {
        return health;
    }

    public void addHealth(double healthToBeAdded) {
        this.health += healthToBeAdded;
    }

    public double getRegenHealthPerSecond() {
        return regenHealthPerSecond;
    }

    public Weapon getWeapon(int weaponIndex) {
        return weapons[weaponIndex];
    }

    public void setWeapon(Weapon weapon, int index) {
        weapons[index] = weapon;
    }

    public Weapon getPrimaryWeapon(){
        return getWeapon(0);
    }

    public List<PowerUp> getActivePowerUps() {
        // TODO: 3/8/2020 loop through the list to check if they are still active
        return activePowerUps;
    }

    public void addActivePowerPp(PowerUp powerUp) {
        // TODO: 3/8/2020 set all the stats necessary for the PowerUp being added
        this.activePowerUps.add(powerUp);
    }

    public int getMaxSpeedUpgradePoints() {
        return maxSpeedUpgradePoints;
    }

    public void addMaxSpeedUpgradePoints(int maxSpeedUpgradePoints) {
        this.maxSpeedUpgradePoints += maxSpeedUpgradePoints;
    }

    public int getMaxHealthUpgradePoints() {
        return maxHealthUpgradePoints;
    }

    public void addMaxHealthUpgradePoints(int maxHealthUpgradePoints) {
        this.maxHealthUpgradePoints += maxHealthUpgradePoints;
    }

    public int getHealthRegenUpgradePoints() {
        return healthRegenUpgradePoints;
    }

    public void addHealthRegenUpgradePoints(int healthRegenUpgradePoints) {
        this.healthRegenUpgradePoints += healthRegenUpgradePoints;
    }

    public int getMoneyPerKillUpgradePoints() {
        return moneyPerKillUpgradePoints;
    }

    public void addMoneyPerKillUpgradePoints(int moneyPerKillUpgradePoints) {
        this.moneyPerKillUpgradePoints += moneyPerKillUpgradePoints;
    }

    public int getPowerUpTimeLengthUpgradePoints() {
        return powerUpTimeLengthUpgradePoints;
    }

    public void addPowerUpTimeLengthUpgradePoints(int powerUpTimeLengthUpgradePoints) {
        this.powerUpTimeLengthUpgradePoints += powerUpTimeLengthUpgradePoints;
    }

    public int getPowerUpEffectivenessUpgradePoints() {
        return powerUpEffectivenessUpgradePoints;
    }

    public void addPowerUpEffectivenessUpgradePoints(int powerUpEffectivenessUpgradePoints) {
        this.powerUpEffectivenessUpgradePoints += powerUpEffectivenessUpgradePoints;
    }

    public int getHealthGlobeEffectivenessUpgradePoints() {
        return healthGlobeEffectivenessUpgradePoints;
    }

    public void addHealthGlobeEffectivenessUpgradePoints(int healthGlobeEffectivenessUpgradePoints) {
        this.healthGlobeEffectivenessUpgradePoints += healthGlobeEffectivenessUpgradePoints;
    }

    public int getExperienceGlobeEffectivenessUpgradePoints() {
        return experienceGlobeEffectivenessUpgradePoints;
    }

    public void addExperienceGlobeEffectivenessUpgradePoints(int experienceGlobeEffectivenessUpgradePoints) {
        this.experienceGlobeEffectivenessUpgradePoints += experienceGlobeEffectivenessUpgradePoints;
    }

    public int getCoinValueUpgradePoints() {
        return coinValueUpgradePoints;
    }

    public void addCoinValueUpgradePoints(int coinValueUpgradePoints) {
        this.coinValueUpgradePoints += coinValueUpgradePoints;
    }

    public int getUnusedUpgradePoints() {
        return unusedUpgradePoints;
    }

    public void addUnusedUpgradePoints(int unusedUpgradePointsToBeAdded) {
        this.unusedUpgradePoints += unusedUpgradePointsToBeAdded;
    }

    public int getUnusedSkillPoints() {
        return unusedSkillPoints;
    }

    public void addUnusedSkillPoints(int unusedSkillPointsToBeAdded) {
        this.unusedSkillPoints += unusedSkillPointsToBeAdded;
    }

    public void setWeaponPositions(double direction) {

        for (int weaponIndex = 0 ; weaponIndex < weapons.length ; weaponIndex++) {

            Weapon weapon = weapons[weaponIndex];

            if (weapon != null) {
                //sets the position of the Weapon - rotated some from the center of the Fighter
                weapon.setPosition(
                        getPositionX() + Math.cos(direction + weaponIndex * ( 2 * Math.PI / weapons.length )) * GameObject.PIXEL_SIZE / 2 * 32,
                        getPositionY() + Math.sin(direction + weaponIndex * ( 2 * Math.PI / weapons.length )) * GameObject.PIXEL_SIZE / 2 * 32
                );

                //sets the direction of the Weapon
                // TODO: 3/12/2020 fixme
                weapon.setDirection(
                        Math.cos(direction + weaponIndex * ( 2 * Math.PI / weapons.length )),
                        Math.sin(direction + weaponIndex * ( 2 * Math.PI / weapons.length ))

                );
            }
        }
    }

    //endregion

    //==================================================================================================================



    //==================================================================================================================

    public void drawHealthBar(){
        StdDraw.setPenColor(StdDraw.GREEN);

        double lineLength = health / 1000;
        StdDraw.filledRectangle(getPositionX(), getPositionY() - .06, lineLength / 2, .005);

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.rectangle(getPositionX(), getPositionY() - .06, lineLength / 2, .005);
    }

    public void draw() {
        drawHealthBar();

        double direction = getDirection().getRadian();

        super.draw(direction);

        setWeaponPositions(direction);

        //for every weapon so long as it exists - draw it facing the right way
        for (int weaponIndex = 0 ; weaponIndex < weapons.length ; weaponIndex++) {
            Weapon weapon = weapons[weaponIndex];

            if (weapon != null) {
                weapon.draw(direction + ( Math.PI * 2 / weapons.length * weaponIndex ));
            }
        }
    }
}
