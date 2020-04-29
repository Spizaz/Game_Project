package Toolkit;

import java.awt.*;

import GameStructureElements.Game;
import edu.princeton.cs.introcs.StdAudio;
import edu.princeton.cs.introcs.StdDraw;

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

    private boolean isMouseAbove() {
        double mouseX = StdDraw.mouseX();
        double mouseY = StdDraw.mouseY();

        return ( mouseX >= position.getX() - width / 2 ) && ( mouseX <= position.getX() + width / 2 ) && ( mouseY >= position.getY() - height / 2 ) && ( mouseY <= position.getY() + height / 2 );
    }

    public boolean isClicked() {
        return isMouseAbove() && StdDraw.isMousePressed();
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

        if (text != null)
            text.draw(position.getX(), position.getY());
    }

    public static synchronized void playSelected() {
        StdAudio.play("Sounds/selected.wav");
    }

    public static synchronized void playDeselected() {
        StdAudio.play("Sounds/deselected.wav");
    }

    public static void updateSounds() {
        double[] selectedAudio = StdAudio.read("Sounds/original_selected.wav");
        double[] deselectedAudio = StdAudio.read("Sounds/original_deselected.wav");

        for (int i = 0 ; i < selectedAudio.length ; i++) {
            selectedAudio[i] *= Game.volumeLevel / 10.;
        }

        for (int i = 0 ; i < deselectedAudio.length ; i++) {
            deselectedAudio[i] *= Game.volumeLevel / 10.;
        }

        StdAudio.save("Sounds/selected.wav", selectedAudio);
        StdAudio.save("Sounds/deselected.wav", deselectedAudio);
    }
}
