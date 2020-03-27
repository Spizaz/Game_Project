import java.awt.*;

public class MovingGameObject extends GameObject {

    /**
     * the magnitude of the x and y components of the Objects velocity
     */
    private Vector velocity;

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

    public MovingGameObject(String name) {
        super(name);
        this.velocity = new Vector();
        this.acceleration = new Vector();
        this.maxSpeed = 0;
        this.mass = 0;
    }

    public MovingGameObject(String name, double maxSpeed, double mass) {
        super(name);
        this.velocity = new Vector();
        this.acceleration = new Vector();
        this.maxSpeed = maxSpeed;
        this.mass = mass;
    }

    public MovingGameObject(Vector position, String name, double maxSpeed, double mass) {
        super(position, name);
        this.velocity = new Vector();
        this.acceleration = new Vector();
        this.maxSpeed = maxSpeed;
        this.mass = mass;
    }

    //==================================================================================================================

    //region Gets and Sets


    public double getVelocityX(){
        return velocity.getX();
    }

    public double getVelocityY(){
        return velocity.getY();
    }

    public Vector getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }

    public void setVelocity(double x, double y) {
        this.velocity.setX(x);
        this.velocity.setY(y);
    }

    public double getAccelerationX(){
        return acceleration.getX();
    }

    public double getAccelerationY(){
        return acceleration.getY();
    }

    public Vector getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Vector acceleration) {
        this.acceleration = acceleration;
    }

    public void setAcceleration(double x, double y) {
        this.acceleration.setX(x);
        this.acceleration.setY(y);
    }

    public double getMaxSpeed() {
        return maxSpeed * Game.FRAME_DELAY;
    }

    public double getMass() {
        return mass;
    }


    //endregion

    //==================================================================================================================

    /**
     * moves the Object by first updating velocity and the position
     */

    public void move(boolean friction){
        velocity.update(acceleration.scaledVector(Game.FRAME_DELAY));

        if(velocity.magnitude() > getMaxSpeed()){
            velocity = velocity.unitVector().scaledVector(getMaxSpeed());
        }

        if(friction && acceleration.magnitude() == 0) velocity = velocity.scaledVector(Math.pow(.994, Game.FRAME_DELAY));

        position.update(velocity);
    }
}
