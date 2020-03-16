public class MachineGun extends Gun {

    public MachineGun(double range, double degreesOfInaccuracy, double recoilForce, double knockBackForce, double criticalDamageChance, double criticalDamageAddedDamage, int price, double damagePerShot, double framesShotDelay) {
        super("Machine_Gun", range, degreesOfInaccuracy, recoilForce, knockBackForce, criticalDamageChance, criticalDamageAddedDamage, price, damagePerShot, framesShotDelay);
        setAmmoTemplate(new Bullet(getAmmoMaxSpeed(), range, damagePerShot, knockBackForce));
        setSpriteFilepath("Images/machine_gun.png");
    }
}
