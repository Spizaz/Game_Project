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


}
