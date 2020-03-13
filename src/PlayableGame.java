import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PlayableGame extends GameMode{

    public static final double LAG_CORRECTION_COEFFICIENT = 5;

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
    }

    //==================================================================================================================

    public boolean isPauseButtonActive(){
        return false;
    }

    //==================================================================================================================

    @Override
    public void draw() {
        StdDraw.clear(getBackground());

        List<Ammo> ammoThisFrame = new ArrayList<>(ammo);
        for (Ammo ammo : ammoThisFrame){
            ammo.draw(0);
        }

        for (Enemy enemy : enemies){
            enemy.draw(0);
        }

        //draw the fighter looking in the right direction
        fighter.draw();

        StdDraw.show();
    }

    @Override
    public void run() {
        while (true) {

            //if the mouse is clicked - fire
            if(StdDraw.isMousePressed()) {

                //if the Weapon is ready to be fired - set the last shot fired
                if(fighter.getWeapon(0).isReadyToFire()) {
                    fighter.getWeapon(0).setLastShotFiredFrameStamp(Game.currentFrame);
                    ammo.add(fighter.getWeapon(0).fire());
                }

            }

            for(Ammo ammo : ammo){
                ammo.position.update(ammo.getVelocity());
            }

            //setting movement for Fighter
            double accelerationX = 0;
            double accelerationY = 0;

            //W
            if (StdDraw.isKeyPressed(87)) {
                accelerationY+= fighter.getMaxAcceleration();
            }
            //S
            if (StdDraw.isKeyPressed(83)) {
                accelerationY -= fighter.getMaxAcceleration();
            }
            //D
            if (StdDraw.isKeyPressed(68)) {
                accelerationX += fighter.getMaxAcceleration();
            }
            //A
            if (StdDraw.isKeyPressed(65)) {
                accelerationX -= fighter.getMaxAcceleration();
            }

            fighter.setAcceleration(accelerationX, accelerationY);

            fighter.move(true);

            fighter.setWeaponPositions();
        }
    }
}
