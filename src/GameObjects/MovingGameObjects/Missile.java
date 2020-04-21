package GameObjects.MovingGameObjects;

import GameObjects.GameObject;
import GameStructureElements.Game;
import Toolkit.Vector;

import java.util.ArrayList;
import java.util.List;

public class Missile extends Ammo {

    /**
     * the nearest Enemy
     */
    private Enemy targetedEnemy;

    //==================================================================================================================

    public Missile(double range, double damage, double knockBackForce) {
        super("Missile", 17, 18e-5, 1, range, damage, knockBackForce);
        setSpriteFilepath("Images/missile.png");


        targetedEnemy = null;
    }

    //==================================================================================================================

    //region Gets and Sets


    public Vector getDesiredDirection() {
        return new Vector();
    }

    public void setTargetedEnemy(List<Enemy> enemies) {
        Enemy target = null;
        double smallestDistance = Double.MAX_VALUE;

        //for all the enemies - if it is the closest and inside the missile's range
        for (Enemy enemy : enemies) {
            double enemyDistance = getDistance(enemy);

            if (enemyDistance < smallestDistance && enemyDistance < getRange()) {
                target = enemy;
            }
        }

        targetedEnemy = target;
    }


    //endregion

    //==================================================================================================================

    public void move(Fighter fighter) {

        //if there is a targeted Enemy - move towards it
        if (targetedEnemy != null) {
            if (getDistance(fighter) > .1) {
                setAcceleration(getPosition().differenceVector(targetedEnemy.getPosition()).scale(7e-6 * ( 1 + getAmmoSpeedUpgradePoints() / 5. )));
            } else {
                setAcceleration(fighter.getPosition().differenceVector(this.getPosition()).scale(7e-6 * ( 1 + getAmmoSpeedUpgradePoints() / 5. )));
            }
            movementVelocity.update(getAcceleration().scaledVector(Game.FRAME_DELAY));
        }

        super.move();
    }

    public List<Fire> explode() {
        List<Fire> fireList = new ArrayList<>();

        for (int j = 0 ; j < 25 ; j++) {
            Vector position = new Vector(
                    getPositionX() + Math.cos(getTotalVelocity().getRadian()) * getSize() / 2 * GameObject.PIXEL_SIZE,
                    getPositionY() + Math.sin(getTotalVelocity().getRadian()) * getSize() / 2 * GameObject.PIXEL_SIZE
            );

            fireList.add(new Fire(position, Math.random() * 2 * Math.PI, 350));
        }

        return fireList;
    }

    @Override
    public Missile clone(Vector position) {
        Missile clone = new Missile(getRange(), getDamage(), getKnockBackForce());
        clone.setPosition(position);
        clone.addAmmoSpeedUpgradePoints(this.getAmmoSpeedUpgradePoints());

        return clone;
    }
}
