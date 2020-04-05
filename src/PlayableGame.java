import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PlayableGame extends GameMode {

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

    private Vector screenCenter;

    private final double SCREEN_WIDTH = 2;

    private final double SCREEN_WIDTH_BUFFER = .5;

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
        this.screenCenter = new Vector();

        enemyList.add(new Enemy(new Vector(.5, .5), 100, 8e-5, 100));
    }

    //==================================================================================================================

    public boolean isPauseButtonActive() {
        return false;
    }

    public double getCurrentGameDifficulty() {
        double difficulty = 0;

        List<Enemy> enemyList = new ArrayList<>(this.enemyList);
        for (Enemy enemy : enemyList) {
            difficulty += enemy.getDifficulty();
        }

        return difficulty;
    }

    //==================================================================================================================

    @Override
    public void run() {

        Vector fighterNetForce = new Vector();

        //if the mouse is clicked - fire
        if (StdDraw.isMousePressed()) {

            //if the Weapon is ready to be fired - set the last shot fired
            if (fighter.getPrimaryWeapon().isReadyToFire()) {
                fighter.getPrimaryWeapon().setLastShotFiredFrameStamp(Game.currentFrame);

                Ammo ammo = fighter.getPrimaryWeapon().fire();
                ammo.movementVelocity.update(fighter.getTotalVelocity());
                ammoList.add(ammo);

                //push the fighter back with the recoil force of the Weapon
                fighterNetForce.addX(- Math.cos(ammo.getTotalVelocity().getRadian()) * fighter.getPrimaryWeapon().getRecoilForce());
                fighterNetForce.addY(- Math.sin(ammo.getTotalVelocity().getRadian()) * fighter.getPrimaryWeapon().getRecoilForce());
            }

        }

        //==================================================================================================================
        //movement for ammo
        //==================================================================================================================

        for (int i = 0 ; i < ammoList.size() ; i++) {
            Ammo ammo = ammoList.get(i);

            //remove the ammo if it is out of range
            if (ammo.isActive()) {
                ammoList.remove(ammo);
                i--;
                continue;
            }

            ammo.position.update(ammo.getTotalVelocity());
            ammo.addDistanceTraveled(ammo.getTotalVelocity().magnitude());
        }

        //==================================================================================================================
        //Fighter movement
        //==================================================================================================================

        //setting up the Enemy's acceleration
        fighter.setMovementAcceleration();

        //movement speed is updated by intentional acceleration
        fighter.movementVelocity.update(fighter.getAcceleration().scaledVector(Game.FRAME_DELAY));

        //caps the movementVelocity
        fighter.limitVelocity();

        //the additional Forces that are acted on by the
        fighter.additionalVelocity.update(fighterNetForce.scale(1 / fighter.getMass()).scale(Game.FRAME_DELAY));

        fighter.addFriction();
        fighter.updatePosition();

        //==================================================================================================================
        //enemy movement
        //==================================================================================================================

        //are any of the Enemies touching any Ammo
        for (int enemyIndex = 0 ; enemyIndex < enemyList.size() ; enemyIndex++) {
            Enemy enemy = enemyList.get(enemyIndex);
            Vector enemyNetForce = new Vector();

            for (int ammoIndex = 0 ; ammoIndex < ammoList.size() ; ammoIndex++) {
                Ammo ammo = ammoList.get(ammoIndex);

                //if the Ammo is touching the Enemy
                if (enemy.isAlive() && enemy.isTouching(ammo)) {
                    //the enemy takes damage and the ammo is gone
                    enemy.addHealth(-ammo.getDamage());
                    ammoList.remove(ammo);
                    ammoIndex--;

                    //add the force of the ammo on the Enemy to the net enemy force Vector
                    //the direction of the force is the ammo's velocity
                    Vector ammoForce = ammo.getTotalVelocity().unitVector().scale(ammo.getKnockBackForce());
                    enemyNetForce.update(ammoForce);
                }

            }

            //if the enemy is dead - remove it from the list
            if(!enemy.isAlive()){
                enemyList.remove(enemy);
                enemyIndex--;
            }
            else {
                //setting up the Enemy's acceleration
                enemy.setDesiredDirection(fighter.position);
                enemy.setAcceleration(enemy.getDesiredDirection().scaledVector(fighter.getMaxAcceleration()));

                //movement speed is updated by intentional acceleration
                enemy.movementVelocity.update(enemy.getAcceleration().scale(Game.FRAME_DELAY));

                //caps the movementVelocity
                enemy.limitVelocity();

                //the additional Forces that are acted on by the
                enemy.additionalVelocity.update(enemyNetForce.scale(Game.FRAME_DELAY / enemy.getMass()));

                enemy.addFriction();
                enemy.updatePosition();




            }
        }

    }

    @Override
    public void draw() {
        StdDraw.clear(getBackground());

        for (Wall wall : wallList) {
            wall.draw();
        }

        for (Enemy enemy : enemyList) {
            enemy.draw();
        }

        for (Ammo ammo : ammoList) {
            //draws the ammo facing towards where it is going
            ammo.draw();
        }

        //draw the fighter looking in the right direction
        fighter.draw();

        if(screenCenter.getX() - fighter.getPositionX() > SCREEN_WIDTH_BUFFER){
            screenCenter.setX(fighter.getPositionX() + SCREEN_WIDTH_BUFFER);
        }
        else if(fighter.getPositionX() - screenCenter.getX() > SCREEN_WIDTH_BUFFER){
            screenCenter.setX(fighter.getPositionX() - SCREEN_WIDTH_BUFFER);
        }

        if(screenCenter.getY() - fighter.getPositionY() > SCREEN_WIDTH_BUFFER){
            screenCenter.setY(fighter.getPositionY() + SCREEN_WIDTH_BUFFER);
        }
        else if(fighter.getPositionY() - screenCenter.getY() > SCREEN_WIDTH_BUFFER){
            screenCenter.setY(fighter.getPositionY() - SCREEN_WIDTH_BUFFER);
        }

        StdDraw.setXscale(screenCenter.getX() - SCREEN_WIDTH / 2, screenCenter.getX() + SCREEN_WIDTH / 2);
        StdDraw.setYscale(screenCenter.getY() - SCREEN_WIDTH / 2, screenCenter.getY() + SCREEN_WIDTH / 2);

        StdDraw.show();
    }

}
