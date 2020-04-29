package GameStructureElements;


import Toolkit.*;
import Toolkit.Button;
import edu.princeton.cs.introcs.StdDraw;
import java.awt.*;


public class GameMenu extends GameMode {

    private final Button playGameButton = new Button(new Vector(0, .1666), .5, .333 / 2,
            Game.BUTTON_COLOR, StdDraw.BLACK,
            new Text("Play", Game.UI_FONT));

    private final Button tutorialButton = new Button(new Vector(0, -.1666), .5, .333 / 2,
            Game.BUTTON_COLOR, StdDraw.BLACK,
            new Text("Tutorial", Game.UI_FONT));

    private final Button settingsButton = new Button(new Vector(0, -.5), .5, .333 / 2,
            Game.BUTTON_COLOR, StdDraw.BLACK,
            new Text("Settings", Game.UI_FONT));

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
    public void init() {

    }

    @Override
    public void run() {

        //==================================================================================================================
        //OTHER STUFF
        //==================================================================================================================

        if (playGameButton.isClicked()) {
            Button.playSelected();

            Game.gameModeID = PlayableGame.getName() + "_init";
        }
    }

    @Override
    public void draw() {

        for (Button button : buttons) {
            button.draw();
        }

        new Text("CAN'T THINK OF GAME NAME YET", 40).draw(0, .75);
    }
}
