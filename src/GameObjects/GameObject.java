package GameObjects;

import Toolkit.Vector;
import edu.princeton.cs.introcs.StdDraw;

public class GameObject {

    /**
     * the size in screen coordinates of a single pixel
     */
    public static final double PIXEL_SIZE = .0025;

    /**
     * the physical location of the GameObjects.GameObject (x , y)
     */
    private volatile Vector position;

    /**
     * the filename of the picture that will be used to draw the Object
     */
    private String spriteFilepath;

    /**
     * a from of identification for the Object
     */
    private String name;

    /**
     * the number of GamePixels the image is at its widest
     */
    private int size;

    //==================================================================================================================

    //the children of the GameObjects.GameObject class need to set their own spriteFileName
    public GameObject(String name) {
        this.position = new Vector();
        this.name = name;
        this.size = 0;
    }

    public GameObject(String name, int size) {
        this.position = new Vector();
        this.name = name;
        this.size = size;
    }

    public GameObject(Vector position, String name, int size) {
        this.position = position;
        this.name = name;
        this.size = size;
    }

    //==================================================================================================================

    //region Gets and Sets


    public double getPositionX() {
        return position.getX();
    }

    public double getPositionY() {
        return position.getY();
    }

    public Vector getPosition() {
        return position.clone();
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public void setPosition(double x, double y) {
        this.position.setX(x);
        this.position.setY(y);
    }

    public void updatePosition(Vector vector){
        position.update(vector);
    }

    public String getSpriteFilepath() {
        return spriteFilepath;
    }

    public void setSpriteFilepath(String spriteFilepath) {
        this.spriteFilepath = spriteFilepath;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public double getPhysicalSize() {
        return getSize() * GameObject.PIXEL_SIZE;
    }

    //endregion

    //==================================================================================================================

    /**
     * determines if an object is touching another object
     *
     * @param other - the object in question
     * @return - true if touching - false if not
     */
    public boolean isTouching(GameObject other) {
        return ( getDistance(other) <= ( this.getPhysicalSize() + other.getPhysicalSize() ) / 2 );
    }

    public double getDistance(GameObject other) {
        return getPosition().difference(other.getPosition());
    }

    //==================================================================================================================


    /**
     * draws the object onto the screen
     */

    public void draw(double radian) {
        StdDraw.picture(getPositionX(), getPositionY(), getSpriteFilepath(), GameObject.PIXEL_SIZE * 32, GameObject.PIXEL_SIZE * 32, Math.toDegrees(radian));
    }
}
