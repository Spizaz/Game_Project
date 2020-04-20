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
     * a list containing all the Experience that is in the game
     */
    private List<Experience> experienceList;

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
            case 1:
            case 2:
            case 3:
                enemyList.add(new Enemy(getRandomEnemyPosition(), 100, 10e-5, 100));
                break;
            case 4:
                break;
            case 5:
                enemyList.add(new Enemy(getRandomEnemyPosition(), 100, 10e-5, 100,
                        new Gun(.5, .5, .02, .025, 0, 0, 25, 3000)) );
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

    private void deadEnemy(Enemy enemy){
        int enemyExperienceOnDeath = enemy.getExperienceOnDeath();
        int enemyMoneyOnDeath = enemy.getMoneyOnDeath();

        fighter.addMoney(enemyMoneyOnDeath);

        List<Integer> experienceAmountList;
        int runningExperience;

        //repeat until the experience amount is equal to what it needs to be
        do{
            experienceAmountList = new ArrayList<>();
            runningExperience = 0;

            do {
                int experience = (int) (Math.random() * 5) + 5;

                experienceAmountList.add(experience);
                runningExperience += experience;

            }while(runningExperience < enemyExperienceOnDeath);
        }while (runningExperience != enemyExperienceOnDeath);

        //add the experience into the game
        for (int experienceIndex = 0 ; experienceIndex < experienceAmountList.size() ; experienceIndex++) {

            experienceList.add(new Experience(enemy.getPosition().clone(), experienceAmountList.get(experienceIndex)) );

        }
    }

    //==================================================================================================================

    @Override
    public void init() {
        this.fighter = new Fighter(new Vector(0, 0));
        this.enemyList = new ArrayList<>();
        this.itemsList = new ArrayList<>();
        this.ammoList = new ArrayList<>();
        this.shrapnelList = new ArrayList<>();
        this.wallList = new ArrayList<>();
        this.experienceList = new ArrayList<>();
        this.screenCenter = new Vector();

        addNewEnemy();
    }

    @Override
    public void run() {

        if(enemyList.size() == 0) addNewEnemy();

        Vector fighterNetForce = new Vector();

        //==================================================================================================================
        //FIGHTER WEAPON FIRE
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

            if(ammo.isTouching(fighter)){
                fighter.addHealth(-ammo.getDamage());

                //knockback force
                fighterNetForce.update(ammo.getTotalVelocity().unitVector().scale(ammo.getKnockBackForce()));

                ammoList.remove(ammo);
                i--;
                continue;
            }
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
        //ENEMY MOVEMENT && ENEMY WEAPON FIRE
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

                deadEnemy(enemy);
                continue;
            }

            if(enemy.hasWeapon() && enemy.getWeapon().isReadyToFire()){
                Ammo ammo = enemy.getWeapon().fire();

                ammoList.add(ammo);

                enemy.getWeapon().setLastShotFiredFrameStamp(Game.currentFrame);

                enemyNetForce.update( ammo.getTotalVelocity().unitVector().scale(enemy.getWeapon().getRecoilForce()).getInverse() );
            }

            //setting up the GameObjects.MovingGameObjects.Enemy's acceleration
            enemy.setDesiredDirection(fighter.getPosition());
            enemy.setAcceleration(enemy.getDesiredDirection().scaledVector(enemy.getMaxAcceleration()));

            //moving the Enemy
            enemy.move(enemyNetForce, true);

            enemy.setWeaponPositions();

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

        //==================================================================================================================
        //EXPERIENCE MOVEMENT
        //==================================================================================================================

        for (int i = 0 ; i < experienceList.size() ; i++) {
            Experience experience = experienceList.get(i);

            experience.move(fighter.getPosition());
            if(fighter.isTouching(experience)){
                fighter.addLevelExperience(experience.getExperience());

                experienceList.remove(experience);
                i--;
            }
        }

    }

    @Override
    public void draw() {
        StdDraw.clear(getBackground());

        //adding the background lines
        double frequency = .2;
        double leftLineX = Math.floor((screenCenter.getX() - SCREEN_WIDTH / 2) / frequency) * frequency;
        double bottomLineY = Math.floor((screenCenter.getY() - SCREEN_WIDTH / 2) / frequency) * frequency;

        StdDraw.setPenColor(StdDraw.GRAY);
        for (double x = leftLineX ; x < screenCenter.getX() + SCREEN_WIDTH / 2 ; x += frequency) {
            StdDraw.line(x, screenCenter.getY() - SCREEN_WIDTH / 2, x, screenCenter.getY() + SCREEN_WIDTH / 2);
        }

        for (double y = bottomLineY ; y < screenCenter.getY() + SCREEN_WIDTH / 2 ; y += frequency) {
            StdDraw.line( screenCenter.getX() - SCREEN_WIDTH / 2, y, screenCenter.getX() + SCREEN_WIDTH / 2, y);
        }

        //==================================================================================================================
        //START DRAWING
        //==================================================================================================================


        for(Shrapnel shrapnel : shrapnelList){
            shrapnel.draw();
        }

        for (Wall wall : wallList) {
            wall.draw();
        }

        for(Experience experience : experienceList){
            experience.draw();
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

        //==================================================================================================================
        //END DRAWING
        //==================================================================================================================


        //setting the screen position
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

        //inside of experience bar
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.filledRectangle(screenCenter.getX(), screenCenter.getY() - SCREEN_WIDTH / 2 + SCREEN_WIDTH / 15, SCREEN_WIDTH / 3, .025);
        StdDraw.filledCircle(screenCenter.getX() - SCREEN_WIDTH / 3, screenCenter.getY() - SCREEN_WIDTH / 2 + SCREEN_WIDTH / 15, .025);
        StdDraw.filledCircle(screenCenter.getX() + SCREEN_WIDTH / 3, screenCenter.getY() - SCREEN_WIDTH / 2 + SCREEN_WIDTH / 15, .025);

        if(fighter.getLevelExperience() > 0){
            double percentOfLevel = (double) fighter.getLevelExperience() / fighter.getExperienceToLevelUp();

            StdDraw.setPenColor(StdDraw.YELLOW);
            StdDraw.filledRectangle(
                    SCREEN_WIDTH / 3 * percentOfLevel - SCREEN_WIDTH / 3 + screenCenter.getX(), screenCenter.getY() - SCREEN_WIDTH / 2 + SCREEN_WIDTH / 15,
                    SCREEN_WIDTH / 3 * percentOfLevel, .025
            );
            StdDraw.filledCircle(screenCenter.getX() - SCREEN_WIDTH / 3, screenCenter.getY() - SCREEN_WIDTH / 2 + SCREEN_WIDTH / 15, .025);
        }

        //outside of experience bar
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.arc(screenCenter.getX() - SCREEN_WIDTH / 3, screenCenter.getY() - SCREEN_WIDTH / 2 + SCREEN_WIDTH / 15, .025, 90, 270);

        StdDraw.line(
                screenCenter.getX() - SCREEN_WIDTH / 3, screenCenter.getY() - SCREEN_WIDTH / 2 + SCREEN_WIDTH / 15 + .025,
                screenCenter.getX() + SCREEN_WIDTH / 3, screenCenter.getY() - SCREEN_WIDTH / 2 + SCREEN_WIDTH / 15 + .025
        );
        StdDraw.line(
                screenCenter.getX() - SCREEN_WIDTH / 3, screenCenter.getY() - SCREEN_WIDTH / 2 + SCREEN_WIDTH / 15 - .025,
                screenCenter.getX() + SCREEN_WIDTH / 3, screenCenter.getY() - SCREEN_WIDTH / 2 + SCREEN_WIDTH / 15 - .025
        );

        StdDraw.arc(screenCenter.getX() + SCREEN_WIDTH / 3, screenCenter.getY() - SCREEN_WIDTH / 2 + SCREEN_WIDTH / 15, .025, 270, 90);

        StdDraw.text(screenCenter.getX(), screenCenter.getY() - SCREEN_WIDTH / 2 + SCREEN_WIDTH / 15 - .005, fighter.getLevelExperience() + " / "+ fighter.getExperienceToLevelUp());
        StdDraw.text(screenCenter.getX(), screenCenter.getY() - SCREEN_WIDTH / 2 + SCREEN_WIDTH / 15 + .05, "Level " + fighter.getLevel());

        StdDraw.show();
    }

}
