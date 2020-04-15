package GameStructureElements;

import java.awt.*;
import Toolkit.Button;
import Toolkit.Vector;
import edu.princeton.cs.introcs.StdDraw;


public class GameMenu extends GameMode {




    private final Button playGameButton = new Button(new Vector(0, .1666), .5, .333 / 2, new Color(56, 140, 234, 255), StdDraw.BLACK);

    private final Button tutorialButton = new Button(new Vector(0, -.1666), .5, .333 / 2, new Color(56, 140, 234, 255), StdDraw.BLACK);

    private final Button settingsButton = new Button(new Vector(0, -.5), .5, .333 / 2, new Color(56, 140, 234, 255), StdDraw.BLACK);

    private final Button[] buttons = {playGameButton, tutorialButton, settingsButton};


    //==================================================================================================================

    public GameMenu(Color background) {
        super(background);
    }

    public static String getName() {
        return "Game_Menu";
    }

    //==================================================================================================================

    @Override
    public void draw() {
        StdDraw.clear(getBackground());

        for (Button button : buttons){
            button.draw();
        }

        StdDraw.show();
    }

    @Override
    public void run() {

    }
}
