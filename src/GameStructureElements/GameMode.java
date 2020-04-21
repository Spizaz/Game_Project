package GameStructureElements;

import java.awt.*;
import java.io.IOException;

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

    public abstract void init() throws IOException;

    public abstract void run();

    public abstract void draw();

}
