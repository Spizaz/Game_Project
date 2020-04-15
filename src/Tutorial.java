import java.awt.*;

public class Tutorial extends GameMode {

    //private final Button toGameMenu = new Button();

    private final Image[] images = new Image[]{};

    private final Vector[] imagePositions = new Vector[]{};

    private final Text[] texts = new Text[]{};

    private final Vector[] textPositions = new Vector[]{};

    //==================================================================================================================

    public Tutorial(Color background) {
        super(background);
    }

    public static String getName(){
        return "Tutorial";
    }

    //==================================================================================================================

    @Override
    public void draw() {

    }

    @Override
    public void run() {

    }
}
