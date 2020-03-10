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

    public PlayableGame(Color backgroundColor) throws InterruptedException {
        super("Playable_Game", backgroundColor);
        this.fighter = new Fighter(new Vector(.5, .5));
        this.enemies = new ArrayList<>();
        this.items = new ArrayList<>();
        this.ammo = new ArrayList<>();

        Thread fighterMovement = new Thread(fighter, "fighter_movement");
        fighterMovement.start();
    }

    //==================================================================================================================

    public boolean isPauseButtonActive(){
        return false;
    }

    //==================================================================================================================

    @Override
    public void draw() {
        StdDraw.clear(getBackground());

        for (Ammo ammo : ammo){
            ammo.draw(0);
        }

        for (Enemy enemy : enemies){
            enemy.draw(0);
        }

        //draw the fighter looking in the right direction
        fighter.draw();

        //StdDraw.picture(.5, .5, "Images/unknown_tile.png", 1, 1, 0 );

        StdDraw.show();
    }
}
