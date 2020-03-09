import java.awt.*;

public abstract class GameMode {

    /**
     * what the GameMode is being referred to by
     */
    private String name;

    /**
     * the background Color of the GameMode
     */
    private Color background;

    //==================================================================================================================

    public GameMode(String name, Color background) {
        this.name = name;
        this.background = background;
    }

    //==================================================================================================================


    public String getName() {
        return name;
    }

    public Color getBackground() {
        return background;
    }

    //==================================================================================================================

    public abstract void draw();

}
