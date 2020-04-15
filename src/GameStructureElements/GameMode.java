package GameStructureElements;

import java.awt.*;

public abstract class GameMode {

    /**
     * the background Color of the GameStructureElements.GameMode
     */
    private Color background;

    //==================================================================================================================

    public GameMode(Color background) {
        this.background = background;
    }

    //==================================================================================================================

    public Color getBackground() {
        return background;
    }

    //==================================================================================================================

    public abstract void run();

    public abstract void draw();

}
