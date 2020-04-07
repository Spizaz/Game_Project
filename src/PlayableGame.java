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
     * a list containing all the Shrapnel that is in the game
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
    private final Button pauseButton = new Button();
     */

    //==================================================================================================================

    public PlayableGame(Color backgroundColor) throws InterruptedException {
        super("Playable_Game", backgroundColor);
        this.fighter = new Fighter(new Vector(0, 0));
        this.enemyList = new ArrayList<>();
        this.itemsList = new ArrayList<>();
        this.ammoList = new ArrayList<>();
        this.shrapnelList = new ArrayList<>();
        this.wallList = new ArrayList<>();
        this.screenCenter = new Vector();

        addNewEnemy();
    }

    //==================================================================================================================

    /**
     * @return a Vector that contains a good position for a new Enemy to be spawned in at
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
     * adds a new Enemy to EnemyList that is at a good difficulty for the fighter
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
    public void run() {

        Vector fighterNetForce = new Vector();

        //==================================================================================================================
        //WEAPON FIRE
        //==================================================================================================================

        //if the mouse is clicked - fire
        if (StdDraw.isMousePressed()) {

            //if the Weapon is ready to be fired - set the last shot fired
            if (fighter.getPrimaryWeapon().isReadyToFire()) {
                fighter.getPrimaryWeapon().setLastShotFiredFrameStamp(Game.currentFrame);

                Ammo ammo = fighter.getPrimaryWeapon().fire();
                ammoList.add(ammo);

                //push the fighter back with the recoil force of the Weapon
                fighterNetForce.addX(- Math.cos(ammo.getTotalVelocity().getRadian()) * fighter.getPrimaryWeapon().getRecoilForce());
                fighterNetForce.addY(- Math.sin(ammo.getTotalVelocity().getRadian()) * fighter.getPrimaryWeapon().getRecoilForce());
            }

        }

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

            //if the Ammo is really a Missile
            if(ammo instanceof Missile){
                ((Missile) ammo).setTargetedEnemy(enemyList);
            }


            ammo.move();
        }

        //==================================================================================================================
        //SHRAPNEL MOVEMENT
        //==================================================================================================================

        for(Shrapnel shrapnel : shrapnelList){
            shrapnel.addFriction();
            shrapnel.updatePosition();
        }

        //==================================================================================================================
        //FIGHTER MOVEMENT
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
        //ENEMY MOVEMENT
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

                    //adding in the shrapnel
                    if(ammo instanceof Missile && SkillTree.shrapnelActive.isActive()){
                        for (int i = 0 ; i < 3 ; i++) {
                            shrapnelList.add(new Shrapnel(ammo.getPosition()));
                        }
                    }

                    ammoList.remove(ammo);
                    ammoIndex--;

                    //add the force of the ammo on the Enemy to the net enemy force Vector
                    //the direction of the force is the ammo's velocity
                    Vector ammoForce = ammo.getTotalVelocity().unitVector().scale(ammo.getKnockBackForce());
                    enemyNetForce.update(ammoForce);
                }

            }

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
                //setting up the Enemy's acceleration
                enemy.setDesiredDirection(fighter.getPosition());
                enemy.setAcceleration(enemy.getDesiredDirection().scaledVector(enemy.getMaxAcceleration()));

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
