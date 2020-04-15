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
        super(name, size);
        this.movementVelocity = new Vector();
        this.additionalVelocity = new Vector();
        this.acceleration = new Vector();
        this.maxSpeed = maxSpeed;
        this.mass = mass;
    }

    public MovingGameObject(Vector position, String name, int size, double maxSpeed, double mass) {
        super(position, name, size);
        this.movementVelocity = new Vector();
        this.additionalVelocity = new Vector();
        this.acceleration = new Vector();
        this.maxSpeed = maxSpeed;
        this.mass = mass;
    }

    //==================================================================================================================

    //region Gets and Sets

    public Vector getTotalVelocity(){
        Vector totalVelocity = new Vector();

        totalVelocity.update(movementVelocity);
        totalVelocity.update(additionalVelocity);

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

    public void limitVelocity(){
        //if the Object is moving faster than its max speed
        if(getTotalVelocity().magnitude() > getMaxSpeed()){

            //and if the movementVelocity wants to slow down the Object - allow the movementVelocity to go above its max Speed
            if(getTotalVelocity().update(movementVelocity).magnitude() > getTotalVelocity().magnitude()){
                movementVelocity.clear();
            }
        }
    }

    public void scaleTotalVelocity(double scalar){
        movementVelocity.scale(scalar);
        additionalVelocity.scale(scalar);
    }

    public void updatePosition(){
        updatePosition(getTotalVelocity());
    }

    /**
     * changes the velocity of the Object after experiencing friction
     */
    public void addFriction(){
        double scalar = Math.pow(.994, Game.FRAME_DELAY);

        scaleTotalVelocity(scalar);
    }

    //==================================================================================================================

    public abstract void draw();

}
