public class Weapon extends StationaryGameObject {

    /**
     * determines which weapon goes on the head of the Fighter
     * has an increased size
     */
    private boolean isPrimaryWeapon;

    /**
     * the euclidean distance that this weapon can fire
     *
     * NOTES : for ___ weapon:
     *    lightning - the distance that the first bolt can travel
     *    missileLauncher - the distance that the missile can travel
     */
    private double range;

    /**
     * the position of the head of the Weapon (x, y)
     * where the Ammo will leave the Weapon from
     */
    private Vector headPosition;

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

    //==================================================================================================================

    public Weapon(String name) {
        super(name);
        this.isPrimaryWeapon = false;
        this.range = 0;
        this.headPosition = new Vector();
        this.degreesOfInaccuracy = 0;
        this.recoilForce = 0;
        this.knockBackForce = 0;
        this.criticalDamageChance = 0;
        this.criticalDamageAddedDamage = 0;
        this.autoFire = false;
        this.price = 0;
        this.damageUpgradePoints = 0;
        this.accuracyUpgradePoints = 0;
        this.fireRateUpgradePoints = 0;
        this.recoilUpgradePoints = 0;
        this.knockBackUpgradePoints = 0;
        this.criticalDamageUpgradePoints = 0;
        setSpriteFilepath("Images/unknown_tile.png");
    }

    public Weapon(String name, boolean isPrimaryWeapon, double range, double degreesOfInaccuracy, double recoilForce, double knockBackForce, double criticalDamageChance, double criticalDamageAddedDamage, boolean autoFire, int price, double width, double height){
        super(new Vector(), name, width, height);
        this.isPrimaryWeapon = isPrimaryWeapon;
        this.range = range;
        this.headPosition = new Vector();
        this.degreesOfInaccuracy = degreesOfInaccuracy;
        this.recoilForce = recoilForce;
        this.knockBackForce = knockBackForce;
        this.criticalDamageChance = criticalDamageChance;
        this.criticalDamageAddedDamage = criticalDamageAddedDamage;
        this.autoFire = autoFire;
        this.price = price;
        this.damageUpgradePoints = 0;
        this.accuracyUpgradePoints = 0;
        this.fireRateUpgradePoints = 0;
        this.recoilUpgradePoints = 0;
        this.knockBackUpgradePoints = 0;
        this.criticalDamageUpgradePoints = 0;
        setSpriteFilepath("Images/unknown_tile.png");
    }

    //==================================================================================================================

    //region Gets, Sets, and Adds


    public boolean isPrimaryWeapon() {
        return isPrimaryWeapon;
    }

    public void setPrimaryWeapon(boolean primaryWeapon) {
        isPrimaryWeapon = primaryWeapon;
    }

    public double getRange() {
        return range;
    }

    public Vector getHeadPosition() {
        return headPosition;
    }

    public void setHeadPosition(Vector headPosition) {
        this.headPosition = headPosition;
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


    //endregion

    //==================================================================================================================

    public void fire(){

    }
}
