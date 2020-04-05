public class Bullet extends Ammo {

    public Bullet(double range, double damage, double knockBackForce) {
        super("Bullet", 11, 18e-5, 1, range, damage, knockBackForce);
        setSpriteFilepath("Images/bullet.png");
    }
}
