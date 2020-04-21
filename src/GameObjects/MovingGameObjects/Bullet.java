package GameObjects.MovingGameObjects;

import Toolkit.Vector;

public class Bullet extends Ammo {

    public Bullet(double range, double damage, double knockBackForce) {
        super("GameObjects.MovingGameObjects.Bullet", 11, 18e-5, 1, range, damage, knockBackForce);
        setSpriteFilepath("Images/bullet.png");
    }

    //==================================================================================================================

    public Bullet clone(Vector position) {
        Bullet clone = new Bullet(getRange(), getDamage(), getKnockBackForce());
        clone.setPosition(position);
        clone.addAmmoSpeedUpgradePoints(this.getAmmoSpeedUpgradePoints());

        return clone;
    }
}
