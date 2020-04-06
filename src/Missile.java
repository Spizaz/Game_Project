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

    public void setTargetedEnemy(List<Enemy> enemies){
        Enemy target = null;
        double smallestDistance = Double.MAX_VALUE;

        //for all the enemies - if it is the closest and inside the missile's range
        for(Enemy enemy : enemies){
            double enemyDistance = getDistance(enemy);

            if(enemyDistance < smallestDistance && enemyDistance < getRange()){
                target = enemy;
            }
        }

        targetedEnemy = target;
    }


    //endregion

    //==================================================================================================================

    @Override
    public void move(){

        //if there is a targeted Enemy - move towards it
        if(targetedEnemy != null){
            setAcceleration(getPosition().differenceVector(targetedEnemy.getPosition()));
            movementVelocity = movementVelocity.update(getAcceleration().scale(.0001)).toUnitVector().scaledVector(getMaxSpeed());
        }

        super.move();
    }

    @Override
    public Missile clone(Vector position) {
        Missile clone = new Missile(getRange(), getDamage(), getKnockBackForce());
        clone.setPosition(position);

        return clone;
    }
}
