public class Ammo extends MovingGameObject {

    /**
     * the max distance this Ammo will be able to travel
     */
    private double range;

    /**
     * the direction towards the targetedEnemy
     */
    private Vector desiredDirection;

    /**
     * the nearest Enemy
     */
    private Enemy targetedEnemy;

    /**
     * the total distance the Ammo has already traveled
     */
    private double distanceTraveled;

    /**
     * the damage the Ammo will do upon impact
     */
    private double damage;

    /**
     * the force the Ammo will exert on the Object it impacts
     */
    private double knockBackForce;



    //==================================================================================================================

    public Ammo(String name, double maxSpeed, double mass, double range, double damage, double knockBackForce){
        super(name, maxSpeed, mass);
        this.range = range;
        this.damage = damage;
        this.knockBackForce = knockBackForce;
    }

    //==================================================================================================================

    //region Gets, Sets, and Adds


    public Vector getDesiredDirection() {
        return desiredDirection;
    }

    public Enemy getTargetedEnemy() {
        return targetedEnemy;
    }

    public double getDistanceTraveled() {
        return distanceTraveled;
    }

    public void addDistanceTraveled(double distanceTraveledToBeAdded) {
        this.distanceTraveled += distanceTraveledToBeAdded;
    }

    public double getDamage() {
        return damage;
    }

    public void addDamage(double damageToBeAdded){
        this.damage += damageToBeAdded;
    }

    public double getKnockBackForce() {
        return knockBackForce;
    }

    public boolean isActive(){
        return getDistanceTraveled() >= range;
    }


    //endregion

    //==================================================================================================================

    public double move(){
        super.move(false);

        return 0;
    }

    public Ammo clone(Vector position){
        Ammo ammo = new Ammo(getName(), getMaxSpeed(), getMass(), range, getDamage(), getKnockBackForce());
        ammo.setPosition(position);
        ammo.distanceTraveled = 0;
        ammo.setSpriteFilepath(getSpriteFilepath());

        return ammo;
    }
}
