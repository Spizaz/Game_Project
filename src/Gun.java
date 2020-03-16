public class Gun extends ProjectileLauncher {

    public Gun(String name, double range, double degreesOfInaccuracy, double recoilForce, double knockBackForce, double criticalDamageChance, double criticalDamageAddedDamage, int price, double damagePerShot, double framesShotDelay) {
        super(name, range, degreesOfInaccuracy, recoilForce, knockBackForce, criticalDamageChance, criticalDamageAddedDamage, price, damagePerShot, framesShotDelay);
        setAmmoTemplate(new Bullet(getAmmoMaxSpeed(), range, damagePerShot, knockBackForce));
    }

    public Gun(double range, double degreesOfInaccuracy, double recoilForce, double knockBackForce, double criticalDamageChance, double criticalDamageAddedDamage, int price, double damagePerShot, double framesShotDelay) {
        super("Gun", range, degreesOfInaccuracy, recoilForce, knockBackForce, criticalDamageChance, criticalDamageAddedDamage, price, damagePerShot, framesShotDelay);
        setAmmoTemplate(new Bullet(getAmmoMaxSpeed(), range, damagePerShot, knockBackForce));
        setSpriteFilepath("Images/gun.png");
    }

    //==================================================================================================================

    @Override
    public Vector getHeadPosition() {
        return new Vector(getPositionX(), getPositionY());
    }

    //==================================================================================================================


}
