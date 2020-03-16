public class ProjectileLauncher extends Weapon {

    /**
     * the amount of damage that each shot fires
     */
    private double damagePerShot;

    private Ammo ammoTemplate;

    private int ammoSpeedUpgradePoints;

    //==================================================================================================================

    public ProjectileLauncher(String name, double range, double degreesOfInaccuracy, double recoilForce, double knockBackForce, double criticalDamageChance, double criticalDamageAddedDamage, int price, double damagePerShot, double framesShotDelay) {
        super(name, range, degreesOfInaccuracy, recoilForce, knockBackForce, criticalDamageChance, criticalDamageAddedDamage, price, framesShotDelay);
        this.damagePerShot = damagePerShot;
        this.ammoSpeedUpgradePoints = 0;
    }

    //==================================================================================================================


    //region Get, Sets, and Adds


    public double getDamagePerShot() {
        return damagePerShot;
    }

    public Ammo getAmmoTemplate() {
        return ammoTemplate;
    }

    public void setAmmoTemplate(Ammo ammoTemplate) {
        this.ammoTemplate = ammoTemplate;
    }

    public double getAmmoMaxSpeed(){
        return 3e-3 * (1 + getAmmoSpeedUpgradePoints() / 10.);
    }

    public int getAmmoSpeedUpgradePoints() {
        return ammoSpeedUpgradePoints;
    }

    public void addAmmoSpeedUpgradePoints(int ammoSpeedUpgradePointsToBeAdded) {
        this.ammoSpeedUpgradePoints += ammoSpeedUpgradePointsToBeAdded;
    }

    @Override
    public Vector getHeadPosition() {
        return new Vector(getPositionX(), getPositionY());
    }

    public boolean isReadyToFire(){
        return Game.currentFrame - getLastShotFiredFrameStamp() >= getFramesShotDelay();
    }


    //endregion

    //==================================================================================================================

    @Override
    public Ammo fire() {
        Ammo ammo = getAmmoTemplate().clone(getHeadPosition());

        double velocityDirection = getDirection().getRadian();
        velocityDirection += (Math.random() * 2 - 1) * getDegreesOfInaccuracy();
        ammo.setVelocity(Vector.radianToVector(velocityDirection).scaledVector(ammo.getMaxSpeed()));

        setSpriteFilepath(getSpriteFilepath());

        return ammo;
    }

}
