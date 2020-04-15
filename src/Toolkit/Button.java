package Toolkit;

import java.awt.*;
import edu.princeton.cs.introcs.StdDraw;

public class Button {

    /**
     * the physical location of the Toolkit.Button
     */
    private Vector position;

    /**
     * the width of the button
     */
    private double width;

    /**
     * the height of the Toolkit.Button
     */
    private double height;

    /**
     * what the Toolkit.Button says on it
     */
    private Text text;

    /**
     * the background Color of the Toolkit.Button
     */
    private Color backgroundColor;

    /**
     * the Color of the border
     */
    private Color borderColor;

    //==================================================================================================================

    public Button(Vector position, double width, double height, Color backgroundColor, Color borderColor, Text text) {
        this.position = position;
        this.width = width;
        this.height = height;
        this.text = null;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
        this.text = text;
    }

    //==================================================================================================================



    //==================================================================================================================

    /**
     * draws the Toolkit.Button onto the screen
     */
    public void draw() {
        StdDraw.setPenColor(backgroundColor);
        StdDraw.filledRectangle(position.getX(), position.getY(), width / 2, height / 2);

        StdDraw.setPenColor(borderColor);
        StdDraw.rectangle(position.getX(), position.getY(), width / 2, height / 2);

        if(text != null)
            text.draw(position.getX(), position.getY());
    }
}
