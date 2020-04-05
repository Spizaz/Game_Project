public class Missile extends Ammo {
    public Missile(double range, double damage, double knockBackForce) {
        super("Missile", 32, 18e-5, 1, range, damage, knockBackForce);
        setSpriteFilepath("Images/missile.png");
    }
}
