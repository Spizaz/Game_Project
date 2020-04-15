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
     * the background Color of the Button
     */
    private Color backgroundColor;

    /**
     * the Color of the border
     */
    private Color borderColor;

    //==================================================================================================================

    public Button(Vector position, double width, double height, Color backgroundColor, Color borderColor) {
        this.position = position;
        this.width = width;
        this.height = height;
        this.text = null;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
    }

    //==================================================================================================================

    public void setText(Text text) {
        this.text = text;
    }

    //==================================================================================================================

    /**
     * draws the Button onto the screen
     */
    public void draw() {
        StdDraw.setPenColor(backgroundColor);
        StdDraw.filledRectangle(position.getX(), position.getY(), width / 2, height / 2);

        StdDraw.setPenColor(borderColor);
        StdDraw.rectangle(position.getX(), position.getY(), width / 2, height / 2);
    }
}
