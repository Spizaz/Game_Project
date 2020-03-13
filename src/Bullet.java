public class Bullet extends Ammo{

    public Bullet(double maxSpeed, double range, double damage, double knockBackForce) {
        super("Bullet", maxSpeed, 1, range, damage, knockBackForce);
        setSpriteFilepath("Images/bullet.png");
    }

}
