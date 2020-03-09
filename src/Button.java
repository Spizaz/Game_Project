import java.awt.*;

public class Button {

    /**
     * the physical location of the Button
     */
    private Vector position;

    /**
     * the width of the button
     */
    private double width;

    /**
     * the height of the Button
     */
    private double height;

    /**
     * what the Button says on it
     */
    private Text text;

    /**
     * the Color of the background when the mouse is over the Button or is active
     */
    private Color activeBackgroundColor;

    /**
     * the background Color if the Button is not active
     */
    private Color passiveBackgroundColor;

    /**
     * the Color of the border
     */
    private Color borderColor;

    /**
     * if the Button has been clicked on
     */
    private boolean isActive;

    //==================================================================================================================

    public Button(Vector position, double width, double height, Text text, Color activeBackgroundColor, Color passiveBackgroundColor, Color borderColor) {
        this.position = position;
        this.width = width;
        this.height = height;
        this.text = text;
        this.activeBackgroundColor = activeBackgroundColor;
        this.passiveBackgroundColor = passiveBackgroundColor;
        this.borderColor = borderColor;
        this.isActive = false;
    }

    //==================================================================================================================

    /**
     * draws the Button onto the screen
     */
    public void draw(){

    }
}
