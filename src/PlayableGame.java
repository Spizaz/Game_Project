import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PlayableGame extends GameMode{

    private Fighter fighter;

    private List<Enemy> enemies;

    private List<Item> items;

    private List<Ammo> ammo;

    /*
    private final Button pauseButton = new Button();
     */

    //==================================================================================================================

    public PlayableGame(Color backgroundColor){
        super("Playable_Game", backgroundColor);
        this.fighter = new Fighter(new Vector(.5, .5));
        this.enemies = new ArrayList<>();
        this.items = new ArrayList<>();
        this.ammo = new ArrayList<>();
    }

    //==================================================================================================================

    public boolean isPauseButtonActive(){
        return false;
    }

    //==================================================================================================================

    @Override
    public void draw() {

    }
}
