package GameStructureElements;

import edu.princeton.cs.introcs.StdDraw;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import Toolkit.Button;
import Toolkit.*;

public class PauseMenu extends GameMode {

    private int fileSaveCounter = 0;

    private final Button backButton = new Button(new Vector(-.85, .85), .1, .1,
            Game.BUTTON_COLOR, StdDraw.BLACK, new Text("BACK", 10));
    /*

    private final Button skillTreeButton = new Button();

    private final Button viewFighterButton = new Button();

    private final Button settingsButton = new Button();

     */

    private final Button[] buttons = {backButton};

    public PauseMenu(Color background) {
        super(background);
    }

    //==================================================================================================================

    public static String getName() {
        return "Pause_Menu";
    }

    //==================================================================================================================

    @Override
    public void init() throws IOException {
        StdDraw.save("Images/Saves/pause_menu_background_save" + ++fileSaveCounter + ".png");
        StdDraw.setScale(-1, 1);
    }

    @Override
    public void run() {
        if (Game.isMouseAvailable() && ( backButton.isClicked() || StdDraw.isKeyPressed(27) )) {
            Game.mouseClick();
            Button.playDeselected();

            Game.gameModeID = PlayableGame.getName();
            File backgroundImage = new File("Images/Saves/pause_menu_background_save" + fileSaveCounter + ".png");
            backgroundImage.delete();
        }
    }

    @Override
    public void draw() {
        StdDraw.picture(0, 0, "Images/Saves/pause_menu_background_save" + fileSaveCounter + ".png", 2, 2);
        StdDraw.clear(new Color(0, 0, 0, 128));

        for (Button button : buttons) {
            button.draw();
        }
    }
}
