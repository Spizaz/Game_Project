import java.util.ArrayList;
import java.util.List;

public class Fighter extends MovingGameObject {

    /**
     * the direction the Fighter is looking - the direction pointed towards the mouse
     */
    private Vector direction;

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
     * the number of upgrade points the user has spent on increasing the acceleration of the Fighter
     */
    private int accelerationUpgradePoints;

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

    //==================================================================================================================

    //SKILLS

    /*
    private final static Skill bulletsPassThroughEnemies = new Skill();

    private final static Skill enemiesExplodeOnDeath = new Skill();

    private final static Skill fireSpreads = new Skill();

    private final static Skill enemiesStayFrozen = new Skill();

    private final static Skill killsGainHealth = new Skill();
     */

    //==================================================================================================================

    public Fighter(Vector position) {
        // TODO: 3/8/2020 may need to edit the stats one line below
        super(position, "Fighter", 1, 100);
        this.direction = new Vector();
        this.totalExperience = 0;
        this.levelExperience = 0;
        this.experienceToLevelUp = 25;
        this.level = 0;
        this.money = 0; // TODO: 3/8/2020 give Fighter enough money to buy a basic gun
        this.health = 100;
        this.regenHealthPerSecond = 0;
        this.weapons = new Weapon[4];
        this.activePowerUps = new ArrayList<>();
        this.maxSpeedUpgradePoints = 0;
        this.accelerationUpgradePoints = 0;
        this.maxHealthUpgradePoints = 0;
        this.healthRegenUpgradePoints = 0;
        this.moneyPerKillUpgradePoints = 0;
        this.powerUpTimeLengthUpgradePoints = 0;
        this.powerUpEffectivenessUpgradePoints = 0;
        this.healthGlobeEffectivenessUpgradePoints = 0;
        this.experienceGlobeEffectivenessUpgradePoints = 0;
        this.coinValueUpgradePoints = 0;
    }

    //==================================================================================================================


    //region Gets, Sets, and Adds


    public Vector getDirection() {
        return direction;
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

    public Weapon[] getWeapons() {
        return weapons;
    }

    public Weapon getWeapon(int weaponIndex) {
        return weapons[weaponIndex];
    }

    public void setWeapon(Weapon weapon, int index){
        weapons[index] = weapon;
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

    public int getAccelerationUpgradePoints() {
        return accelerationUpgradePoints;
    }

    public void addAccelerationUpgradePoints(int accelerationUpgradePoints) {
        this.accelerationUpgradePoints += accelerationUpgradePoints;
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


    //endregion

    //==================================================================================================================
}
