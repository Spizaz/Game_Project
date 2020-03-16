import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class PlayableGame extends GameMode{

    private Fighter fighter;

    private List<Enemy> enemiesList;

    private List<Item> itemsList;

    private List<Ammo> ammoList;

    /*
    private final Button pauseButton = new Button();
     */

    //==================================================================================================================

    public PlayableGame(Color backgroundColor) throws InterruptedException {
        super("Playable_Game", backgroundColor);
        this.fighter = new Fighter(new Vector(.5, .5));
        this.enemiesList = new ArrayList<>();
        this.itemsList = new ArrayList<>();
        this.ammoList = new ArrayList<>();
    }

    //==================================================================================================================

    public boolean isPauseButtonActive(){
        return false;
    }

    //==================================================================================================================

    @Override
    public void draw() {
        StdDraw.clear(getBackground());

        List<Ammo> ammoThisFrame = new ArrayList<>(ammoList);
        for (Ammo ammo : ammoThisFrame){
            ammo.draw(0);
        }

        for (Enemy enemy : enemiesList){
            enemy.draw(0);
        }

        //draw the fighter looking in the right direction
        fighter.draw();

        StdDraw.show();
    }

    @Override
    public void run() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                //if the mouse is clicked - fire
                if(StdDraw.isMousePressed()) {

                    //if the Weapon is ready to be fired - set the last shot fired
                    if(fighter.getWeapon(0).isReadyToFire()) {
                        fighter.getWeapon(0).setLastShotFiredFrameStamp(Game.currentFrame);
                        ammoList.add(fighter.getWeapon(0).fire());
                    }

                }

                for (int i = 0 ; i < ammoList.size() ; i++) {
                    Ammo ammo = ammoList.get(i);

                    //if the distance from the ammo is really far - delete it
                    if (ammo.isActive()) {
                        ammoList.remove(ammo);
                        i--;
                        continue;
                    }

                    ammo.position.update(ammo.getVelocity());
                    ammo.addDistanceTraveled(ammo.getVelocity().magnitude());
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
        };

        timer.scheduleAtFixedRate(timerTask, 0, 1000/60);

    }
}
