package GameObjects.MovingGameObjects;

import GameStructureElements.Game;
import GameObjects.GameObject;
import Toolkit.Vector;

public abstract class MovingGameObject extends GameObject {

    /**
     * the magnitude of the x and y components of the Objects velocity caused by intentional movement
     */
    public Vector movementVelocity;

    /**
     * the magnitude of the x and y components of the Objects velocity caused by unintentional movement
     */
    public Vector additionalVelocity;

    /**
     * the magnitude of the x and y components of the Objects acceleration
     */
    private Vector acceleration;

    /**
     * the maximum magnitude of velocity
     */
    private double maxSpeed;

    /**
     * the mass of the Object - affects forces and stuff
     */
    private double mass;

    //==================================================================================================================

    public MovingGameObject(String name, int size, double maxSpeed, double mass) {
        super( name, size );
        this.movementVelocity = new Vector();
        this.additionalVelocity = new Vector();
        this.acceleration = new Vector();
        this.maxSpeed = maxSpeed;
        this.mass = mass;
    }

    public MovingGameObject(Vector position, String name, int size, double maxSpeed, double mass) {
        super( position, name, size );
        this.movementVelocity = new Vector();
        this.additionalVelocity = new Vector();
        this.acceleration = new Vector();
        this.maxSpeed = maxSpeed;
        this.mass = mass;
    }

    //==================================================================================================================

    //region Gets and Sets

    public Vector getTotalVelocity() {
        Vector totalVelocity = new Vector();

        totalVelocity.update( movementVelocity );
        totalVelocity.update( additionalVelocity );

        return totalVelocity;
    }

    public double getAccelerationX() {
        return acceleration.getX();
    }

    public double getAccelerationY() {
        return acceleration.getY();
    }

    public Vector getAcceleration() {
        return acceleration.clone();
    }

    public void setAcceleration(Vector acceleration) {
        this.acceleration = acceleration;
    }

    public double getMaxSpeed() {
        return maxSpeed * Game.FRAME_DELAY;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public double getMass() {
        return mass;
    }


    //endregion

    //==================================================================================================================

    private void limitVelocity() {
        //if the Object is moving faster than its max speed
        if (movementVelocity.magnitude() > getMaxSpeed()) {
            movementVelocity = movementVelocity.unitVector().scale(getMaxSpeed());
        }
    }

    public void scaleTotalVelocity(double scalar) {
        movementVelocity.scale( scalar );
        additionalVelocity.scale( scalar );
    }

    private void updateVelocity(Vector netForce, boolean friction) {
        //intentional movement is caused by intentional acceleration
        movementVelocity.update( acceleration.scaledVector( Game.FRAME_DELAY ) );

        //limit the amount of intentional velocity to the maximum speed
        limitVelocity();

        //the unintentional forces acting on the Object changing its velocity
        if (netForce != null)
            additionalVelocity.update( netForce.scaledVector( Game.FRAME_DELAY / getMass() ) );

        if (friction)
            addFriction();
    }

    public void move(Vector netForce, boolean friction) {
        updateVelocity( netForce, friction );

        updatePosition( getTotalVelocity() );
    }

    /**
     * changes the velocity of the Object after experiencing friction
     */
    private void addFriction() {
        double scalar = Math.pow( .994, Game.FRAME_DELAY );

        scaleTotalVelocity( scalar );
    }

    //==================================================================================================================

    public abstract void draw();

}
