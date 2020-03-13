import java.util.jar.JarOutputStream;

public class GameObject {

    /**
     * the size in screen coordinates of a single pixel
     */
    public static final double PIXEL_SIZE = .05;

    /**
     * the physical location of the GameObject (x , y)
     */
    public volatile Vector position;

    /**
     * the filename of the picture that will be used to draw the Object
     */
    private String spriteFilepath;

    /**
     * a from of identification for the Object
     */
    private String name;

    //==================================================================================================================

    public GameObject(){
        this.position = new Vector();
        this.spriteFilepath = "";
        this.name = "Game_Object";
    }

    //the children of the GameObject class need to set their own spriteFileName
    public GameObject(String name){
        this.position = new Vector();
        this.name = name;
    }

    public GameObject(Vector position, String name){
        this.position = position;
        this.name = name;
    }

    //==================================================================================================================

    //region Gets and Sets


    public double getPositionX(){
        return position.getX();
    }

    public double getPositionY(){
        return position.getY();
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public void setPosition(double x, double y) {
        this.position.setX(x);
        this.position.setY(y);
    }

    public String getSpriteFilepath(){
        return spriteFilepath;
    }

    public void setSpriteFilepath(String spriteFilepath){
        this.spriteFilepath = spriteFilepath;
    }

    public String getName() {
        return name;
    }


    //endregion

    //==================================================================================================================

    /**
     * determines if an object is touching another object
     * @param other - the object in question
     * @return - true if touching - false if not
     */

    public boolean isTouching(GameObject other){

        return false;
    }


    /**
     * draws the object onto the screen
     */

    public void draw(double degrees){
        StdDraw.picture(getPositionX(), getPositionY(), getSpriteFilepath(), GameObject.PIXEL_SIZE, GameObject.PIXEL_SIZE,  degrees);
    }
}
