import java.awt.*;

public class Text {

    /**
     * what the Text is actually going to say
     */
    private String text;

    /**
     * what the Font of the text is
     */
    private Font font;

    /**
     * what color the text will be
     */
    private Color textColor;

    //==================================================================================================================


    public Text(String text, Font font, Color textColor) {
        this.text = text;
        this.font = font;
        this.textColor = textColor;
    }

    //==================================================================================================================

    public void draw(double xPos, double yPos) {

    }
}
