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

    public MovingGameObject(){
        super("Moving_Game_Object");
        this.velocity = new Vector();
        this.acceleration = new Vector();
        this.maxSpeed = 0;
        this.mass = 0;
        setSpriteFilepath("Images/unknown_tile.png");
    }

    public MovingGameObject(String name) {
        super(name);
        this.velocity = new Vector();
        this.acceleration = new Vector();
        this.maxSpeed = 0;
        this.mass = 0;
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

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public double getMass() {
        return mass;
    }


    //endregion

    //==================================================================================================================

    /**
     * moves the Object by first updating velocity and the position
     */

    public void move(){

    }
}
