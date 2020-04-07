public class MachineGun extends Gun {

    public MachineGun(double range, double degreesOfInaccuracy, double recoilForce, double knockBackForce, double criticalDamageChance, double criticalDamageAddedDamage,  double damagePerShot, double shotDelay) {
        super("Machine_Gun", range, degreesOfInaccuracy, recoilForce, knockBackForce, criticalDamageChance, criticalDamageAddedDamage, damagePerShot, shotDelay);
        setAmmoTemplate(new Bullet(range, damagePerShot, knockBackForce));
        setSpriteFilepath("Images/machine_gun.png");
    }
}
