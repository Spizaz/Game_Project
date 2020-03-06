public class GameObject {

    /**
     * the physical location of the GameObject (x , y)
     */
    private Vector position;

    /**
     * the filename of the picture that will be used to draw the Object
     */
    private String spriteFileName;

    /**
     * a from of identification for the Object
     */
    private String name;

    //==================================================================================================================

    public GameObject(){
        this.position = new Vector();
        this.spriteFileName = "";
        this.name = "Game_Object";
    }

    public GameObject(String name){
        this.position = new Vector();
        this.spriteFileName = "";
        this.name = "Game_Object";
    }

    public GameObject(Vector position, String name){
        this.position = position;
        this.spriteFileName = "";
        this.name = "Game_Object";
    }

    //==================================================================================================================

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

    public String getName() {
        return name;
    }

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

    public void draw(){

    }
}
