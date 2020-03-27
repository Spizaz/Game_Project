import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class PlayableGame extends GameMode{

    /**
     * the user controlled protagonist of the game
     */
    private Fighter fighter;

    /**
     * a list containing all the enemies that are in the game
     */
    private List<Enemy> enemyList;

    /**
     * a list containing all the enemies that are in the game
     */
    private List<Item> itemsList;

    /**
     * a list containing all the ammo that is in the game
     */
    private List<Ammo> ammoList;

    /**
     * a list containing all the walls that are in the game
     */
    private List<Wall> wallList;

    /**
     * a number indicating how difficult the game should be
     * to keep the game interesting
     */
    private double gameDifficulty;

    /*
    private final Button pauseButton = new Button();
     */

    //==================================================================================================================

    public PlayableGame(Color backgroundColor) throws InterruptedException {
        super("Playable_Game", backgroundColor);
        this.fighter = new Fighter(new Vector(0, 0));
        this.enemyList = new ArrayList<>();
        this.itemsList = new ArrayList<>();
        this.ammoList = new ArrayList<>();
        this.wallList = new ArrayList<>();
        this.gameDifficulty = 1;
    }

    //==================================================================================================================

    public boolean isPauseButtonActive(){
        return false;
    }

    public double getCurrentGameDifficulty(){
        double  difficulty = 0;

        List<Enemy> enemyList = new ArrayList<>(this.enemyList);
        for(Enemy enemy : enemyList){
            difficulty += enemy.getDifficulty();
        }

        return difficulty;
    }

    //==================================================================================================================

    @Override
    public void run() {

        enemyList.add(new Enemy(new Vector(.75, .75), 100, 1, 100));

        //if the mouse is clicked - fire
        if(StdDraw.isMousePressed()) {

            //if the Weapon is ready to be fired - set the last shot fired
            if(fighter.getWeapon(0).isReadyToFire()) {
                fighter.getWeapon(0).setLastShotFiredFrameStamp(Game.currentFrame);
                ammoList.add(fighter.getWeapon(0).fire());
            }

        }

        //movement for ammo
        for (int i = 0 ; i < ammoList.size() ; i++) {
            Ammo ammo = ammoList.get(i);

            //remove the ammo if it is out of range
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

    }

    @Override
    public void draw() {
        StdDraw.clear(getBackground());

        for (Wall wall : wallList){
            wall.draw();
        }

        List<Ammo> ammoThisFrame = new ArrayList<>(ammoList);
        for (Ammo ammo : ammoThisFrame){
            //draws the ammo facing towards where it is going
            ammo.draw(ammo.getVelocity().getRadian());
        }

        for (Enemy enemy : enemyList){
            enemy.draw(0);
        }

        //draw the fighter looking in the right direction
        fighter.draw();

        StdDraw.show();
    }

}
