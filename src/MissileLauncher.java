public class MissileLauncher extends ProjectileLauncher {
    public MissileLauncher(double range, double degreesOfInaccuracy, double recoilForce, double knockBackForce, double criticalDamageChance, double criticalDamageAddedDamage, int price, double damagePerShot, double shotDelay) {
        super("Missile_Launcher", range, degreesOfInaccuracy, recoilForce, knockBackForce, criticalDamageChance, criticalDamageAddedDamage, price, damagePerShot, shotDelay);
        setAmmoTemplate(new Missile(range, damagePerShot, knockBackForce));
        setSpriteFilepath("Images/missile_launcher.png");
    }
}
