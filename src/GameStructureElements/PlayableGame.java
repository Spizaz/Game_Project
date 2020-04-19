package GameStructureElements;

import GameObjects.MovingGameObjects.*;
import GameObjects.StationaryGameObjects.*;
import Toolkit.Vector;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import edu.princeton.cs.introcs.StdDraw;

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
     * a list containing all the GameObjects.MovingGameObjects.Shrapnel that is in the game
     */
    private List<Shrapnel> shrapnelList;

    /**
     * a list containing all the walls that are in the game
     */
    private List<Wall> wallList;

    /**
     * the coordinates of the center of the screen (duh)
     */
    private Vector screenCenter;

    /**
     * the total width of the screen
     */
    private final double SCREEN_WIDTH = 2;

    /**
     * how far from the center of the screen the fighter has to move before the screen starts to adjust
     */
    private final double SCREEN_WIDTH_BUFFER = .5;

    /*
    private final Toolkit.Button pauseButton = new Toolkit.Button();
     */

    //==================================================================================================================

    public PlayableGame(Color backgroundColor) throws InterruptedException {
        super(backgroundColor);
        this.fighter = new Fighter(new Vector(0, 0));
        this.enemyList = new ArrayList<>();
        this.itemsList = new ArrayList<>();
        this.ammoList = new ArrayList<>();
        this.shrapnelList = new ArrayList<>();
        this.wallList = new ArrayList<>();
        this.screenCenter = new Vector();

        addNewEnemy();
    }

    public static String getName() {
        return "Playable_Game";
    }

    //==================================================================================================================

    /**
     * @return a Toolkit.Vector that contains a good position for a new GameObjects.MovingGameObjects.Enemy to be spawned in at
     */
    private Vector getRandomEnemyPosition(){
        double leftBound = screenCenter.getX() - SCREEN_WIDTH / 2;
        double lowerBound = screenCenter.getY() - SCREEN_WIDTH / 2;

        Vector fighterPosition = fighter.getPosition();
        Vector enemyPosition;

        do{
            enemyPosition = new Vector(
                    Math.random() * SCREEN_WIDTH + leftBound,
                    Math.random() * SCREEN_WIDTH + lowerBound
                    );
        }while (fighterPosition.difference(enemyPosition) <= SCREEN_WIDTH / 2);

        return enemyPosition;
    }

    /**
     * adds a new GameObjects.MovingGameObjects.Enemy to EnemyList that is at a good difficulty for the fighter
     */
    private void addNewEnemy(){

        switch (fighter.getLevel()){
            case 0:
            case 1:
            case 2:
                enemyList.add(new Enemy(getRandomEnemyPosition(), 100, 10e-5, 100));
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                enemyList.add(new Enemy(getRandomEnemyPosition(), 100, 10e-5, 100,
                        new Gun(.5, .5, .05, .025, 0, 0, 25, 1000)) );
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;

        }
    }

    //==================================================================================================================

    @Override
    public void init() {

    }

    @Override
    public void run() {

        Vector fighterNetForce = new Vector();

        //==================================================================================================================
        //WEAPON FIRE
        //==================================================================================================================

        for (int weaponIndex = 0 ; weaponIndex < fighter.getWeapons().length ; weaponIndex++) {
            Weapon weapon = fighter.getWeapon(weaponIndex);

            if(weapon == null) continue;

            //fire the weapon if the weapon is ready to fire
            //but if the weapon is the primary weapon - fire only if the mouse is pressed
            if((weaponIndex == 0) ? weapon.isReadyToFire() && StdDraw.isMousePressed() : weapon.isReadyToFire()){
                Ammo ammo = weapon.fire();

                ammoList.add(ammo);

                //setting recoil force
                fighterNetForce.update(weapon.getDirection().scale(weapon.getRecoilForce()).getInverse());

                weapon.setLastShotFiredFrameStamp(Game.currentFrame);
            }

        }//weaponIndex

        //==================================================================================================================
        //AMMO MOVEMENT
        //==================================================================================================================

        for (int i = 0 ; i < ammoList.size() ; i++) {
            Ammo ammo = ammoList.get(i);

            //remove the ammo if it is out of range
            if (!ammo.isActive()) {
                ammoList.remove(ammo);
                i--;
                continue;
            }

            //if the Ammo is really a GameObjects.MovingGameObjects.Missile
            if(ammo instanceof Missile){
                ((Missile) ammo).setTargetedEnemy(enemyList);
            }


            ammo.move();
        }

        //==================================================================================================================
        //SHRAPNEL MOVEMENT
        //==================================================================================================================

        for(Shrapnel shrapnel : shrapnelList){
            shrapnel.move(null, true);
        }

        //==================================================================================================================
        //FIGHTER MOVEMENT
        //==================================================================================================================

        //setting up the Enemy's acceleration
        fighter.setMovementAcceleration();

        fighter.move(fighterNetForce, true);

        fighter.setDirection();

        //==================================================================================================================
        //ENEMY MOVEMENT
        //==================================================================================================================

        //are any of the Enemies touching any GameObjects.MovingGameObjects.Ammo
        for (int enemyIndex = 0 ; enemyIndex < enemyList.size() ; enemyIndex++) {
            Enemy enemy = enemyList.get(enemyIndex);
            Vector enemyNetForce = new Vector();

            for (int ammoIndex = 0 ; ammoIndex < ammoList.size() ; ammoIndex++) {
                Ammo ammo = ammoList.get(ammoIndex);

                //if the Ammo is touching the Enemy
                if (enemy.isAlive() && enemy.isTouching(ammo)) {
                    //the enemy takes damage and the ammo is gone
                    enemy.addHealth(-ammo.getDamage());

                    //adding in the shrapnel
                    if(ammo instanceof Missile && SkillTree.shrapnelActive.isActive()){
                        for (int i = 0 ; i < 3 ; i++) {
                            shrapnelList.add(new Shrapnel(ammo.getPosition()));
                        }
                    }

                    ammoList.remove(ammo);
                    ammoIndex--;

                    //add the force of the ammo on the Enemy to the net enemy force Toolkit.Vector
                    //the direction of the force is the ammo's velocity
                    Vector ammoForce = ammo.getTotalVelocity().unitVector().scale(ammo.getKnockBackForce());
                    enemyNetForce.update(ammoForce);
                }

            }//ammoIndex

            //for every Shrapnel - if the enemy is touching it - deal damage
            for (int i = 0 ; i < shrapnelList.size() ; i++){
                Shrapnel shrapnel = shrapnelList.get(i);

                if(shrapnel.isActive() && enemy.isTouching(shrapnel)){
                    enemy.addHealth(-10);
                    shrapnelList.remove(shrapnel);
                    i--;
                }
            }

            //if the enemy is dead - remove it from the list
            if(!enemy.isAlive()){
                enemyList.remove(enemy);
                enemyIndex--;
            }
            else {
                //setting up the GameObjects.MovingGameObjects.Enemy's acceleration
                enemy.setDesiredDirection(fighter.getPosition());
                enemy.setAcceleration(enemy.getDesiredDirection().scaledVector(enemy.getMaxAcceleration()));

                //moving the Enemy
                enemy.move(enemyNetForce, true);
            }

        }//enemyIndex

        //==================================================================================================================
        //FIGHTER DAMAGE
        //==================================================================================================================

        //for every Shrapnel - if the fighter is touching it - deal damage
        for (int i = 0 ; i < shrapnelList.size() ; i++){
            Shrapnel shrapnel = shrapnelList.get(i);

            if(shrapnel.isActive() && fighter.isTouching(shrapnel)){
                fighter.addHealth(-10);
                shrapnelList.remove(shrapnel);
                i--;
            }
        }

    }

    @Override
    public void draw() {
        StdDraw.clear(getBackground());

        for(Shrapnel shrapnel : shrapnelList){
            shrapnel.draw();
        }

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
