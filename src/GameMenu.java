import java.awt.*;

public class GameMenu extends GameMode {

    /*
    private final Button playGameButton = new Button();

    private final Button settingsButton = new Button();

    private final Button tutorialButton = new Button();

     */

    //==================================================================================================================

    public GameMenu(Color background) {
        super("Game_Mode", background);
    }

    //==================================================================================================================

    @Override
    public void draw() {
        StdDraw.clear(getBackground());
    }

    @Override
    public void run() {

    }
}
