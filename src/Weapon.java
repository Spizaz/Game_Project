public abstract class Weapon extends StationaryGameObject {

    /**
     * the euclidean distance that this weapon can fire
     *
     * NOTES : for ___ weapon:
     *    lightning - the distance that the first bolt can travel
     *    missileLauncher - the distance that the missile can travel
     */
    private double range;

    /**
     * determines how far to the left and right the Ammo will miss the intended target
     */
    private double degreesOfInaccuracy;

    /**
     * the force that the Weapon exerts on the user after firing
     */
    private double recoilForce;

    /**
     * the force that the Weapon exerts on the target after contact
     */
    private double knockBackForce;

    /**
     * the percent chance that a hit is a critical attack
     */
    private double criticalDamageChance;

    /**
     * the percentage of added damage that happens on a critical hit
     */
    private double criticalDamageAddedDamage;

    /**
     * determines if the Weapon will fire automatically
     * always false for primary Weapons
     */
    private boolean autoFire;

    /**
     * the amount of money that is takes to purchase the Weapon
     */
    private int price;

    /**
     * the number of times that the damage section of the Weapon has been upgraded
     */
    private int damageUpgradePoints;

    /**
     * the number of times that the range section of the Weapon has been upgraded
     */
    private int rangeUpgradePoints;

    /**
     * the number of times that the accuracy section of the Weapon has been upgraded
     */
    private int accuracyUpgradePoints;

    /**
     * the number of times that the fire rate section of the Weapon has been upgraded
     */
    private int fireRateUpgradePoints;

    /**
     * the number of times that the recoil section of the Weapon has been upgraded
     */
    private int recoilUpgradePoints;

    /**
     * the number of times that the knock back section of the Weapon has been upgraded
     */
    private int knockBackUpgradePoints;

    /**
     * the number of times that the critical damage section of the Weapon has been upgraded
     */
    private int criticalDamageUpgradePoints;

    /**
     * the number of frames that the Weapon needs to wait until firing again
     */
    private double shotDelay;

    /**
     * the frame where the Ammo was last fired
     */
    private long lastShotFiredFrameStamp;

    //==================================================================================================================

    public Weapon(String name, double range, double degreesOfInaccuracy, double recoilForce, double knockBackForce, double criticalDamageChance, double criticalDamageAddedDamage, int price, double shotDelay){
        super(new Vector(), name);
        this.range = range;
        this.degreesOfInaccuracy = degreesOfInaccuracy;
        this.recoilForce = recoilForce;
        this.knockBackForce = knockBackForce;
        this.criticalDamageChance = criticalDamageChance;
        this.criticalDamageAddedDamage = criticalDamageAddedDamage;
        this.autoFire = false;
        this.price = price;
        this.damageUpgradePoints = 0;
        this.rangeUpgradePoints = 0;
        this.accuracyUpgradePoints = 0;
        this.fireRateUpgradePoints = 0;
        this.recoilUpgradePoints = 0;
        this.knockBackUpgradePoints = 0;
        this.criticalDamageUpgradePoints = 0;
        this.shotDelay = shotDelay;
        this.lastShotFiredFrameStamp = 0;
        setSpriteFilepath("Images/unknown_tile.png");
    }

    //==================================================================================================================

    //region Gets, Sets, and Adds


    public double getRange() {
        return range;
    }

    public double getDegreesOfInaccuracy(){
        return degreesOfInaccuracy;
    }

    public double getRecoilForce() {
        return recoilForce;
    }

    public double getKnockBackForce() {
        return knockBackForce;
    }

    public double getCriticalDamageChance() {
        return criticalDamageChance;
    }

    public double getCriticalDamageAddedDamage() {
        return criticalDamageAddedDamage;
    }

    public boolean isAutoFire() {
        return autoFire;
    }

    public void setAutoFire(boolean autoFire) {
        this.autoFire = autoFire;
    }

    public int getPrice() {
        return price;
    }

    public int getDamageUpgradePoints() {
        return damageUpgradePoints;
    }

    public void addDamageUpgradePoints(int damageUpgradePointsAdded) {
        this.damageUpgradePoints += damageUpgradePointsAdded;
    }

    public int getAccuracyUpgradePoints() {
        return accuracyUpgradePoints;
    }

    public void addAccuracyUpgradePoints(int accuracyUpgradePointsAdded) {
        this.accuracyUpgradePoints += accuracyUpgradePointsAdded;
    }

    public int getFireRateUpgradePoints() {
        return fireRateUpgradePoints;
    }

    public void addFireRateUpgradePoints(int fireRateUpgradePointsAdded) {
        this.fireRateUpgradePoints += fireRateUpgradePointsAdded;
    }

    public int getRecoilUpgradePoints() {
        return recoilUpgradePoints;
    }

    public void addRecoilUpgradePoints(int recoilUpgradePointsAdded) {
        this.recoilUpgradePoints += recoilUpgradePointsAdded;
    }

    public int getKnockBackUpgradePoints() {
        return knockBackUpgradePoints;
    }

    public void addKnockBackUpgradePoints(int knockBackUpgradePointsAdded) {
        this.knockBackUpgradePoints += knockBackUpgradePointsAdded;
    }

    public int getCriticalDamageUpgradePoints() {
        return criticalDamageUpgradePoints;
    }

    public void addCriticalDamageUpgradePoints(int criticalDamageUpgradePointsToBeAdded) {
        this.criticalDamageUpgradePoints += criticalDamageUpgradePointsToBeAdded;
    }

    public double getShotDelay() {
        return shotDelay;
    }

    public long getLastShotFiredFrameStamp() {
        return lastShotFiredFrameStamp;
    }

    public void setLastShotFiredFrameStamp(long lastShotFiredFrameStamp) {
        this.lastShotFiredFrameStamp = lastShotFiredFrameStamp;
    }

    /**
     * @return a Vector containing the position of the Head of the Weapon
     */
    public abstract Vector getHeadPosition();

    /**
     * @return a boolean representing if the weapon is ready to be fired
     */
    public abstract boolean isReadyToFire();

    /**
     * @return a double representing the amount of max damage that is done per frame on average
     */
    public abstract double getDamagePerSecond();


    //endregion

    //==================================================================================================================

    public abstract Ammo fire();
}
