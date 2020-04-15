package GameStructureElements;

import java.awt.*;

import Toolkit.*;

public class Tutorial extends GameMode {

    //private final Toolkit.Button toGameMenu = new Toolkit.Button();

    private final Image[] images = new Image[]{};

    private final Vector[] imagePositions = new Vector[]{};

    private final Text[] texts = new Text[]{};

    private final Vector[] textPositions = new Vector[]{};

    //==================================================================================================================

    public Tutorial(Color background) {
        super(background);
    }

    public static String getName(){
        return "GameStructureElements.Tutorial";
    }

    //==================================================================================================================

    @Override
    public void draw() {

    }

    @Override
    public void init() {

    }

    @Override
    public void run() {

    }
}
