package GameStructureElements;


import GameObjects.GameObject;
import Toolkit.*;
import Toolkit.Button;
import edu.princeton.cs.introcs.StdDraw;
import GameObjects.MovingGameObjects.*;
import GameObjects.StationaryGameObjects.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class GameMenu extends GameMode {

    private final Button playGameButton = new Button(new Vector(0, .1666), .5, .333 / 2,
            Game.BUTTON_COLOR, StdDraw.BLACK,
            new Text("Play", Game.UI_FONT));

    private final Button tutorialButton = new Button(new Vector(0, -.1666), .5, .333 / 2,
            Game.BUTTON_COLOR, StdDraw.BLACK,
            new Text("Tutorial", Game.UI_FONT));

    private final Button settingsButton = new Button(new Vector(0, -.5), .5, .333 / 2,
            Game.BUTTON_COLOR, StdDraw.BLACK,
            new Text("Settings", Game.UI_FONT));

    private final Button[] buttons = {playGameButton, tutorialButton, settingsButton};

    private List<Enemy> enemyList;

    private Fighter fighter;

    private List<Ammo> ammoList;

    private List<Fire> fireList;


    //==================================================================================================================

    public GameMenu(Color background) {
        super(background);
    }

    public static String getName() {
        return "Game_Menu";
    }

    public Enemy getNearestEnemy() {
        Enemy nearestEnemy = null;
        double nearestDistance = Double.MAX_VALUE;

        //finds the closest Enemy
        for (Enemy enemy : enemyList) {
            if (enemy.getDistance(fighter) < nearestDistance) {
                nearestDistance = enemy.getPosition().difference(fighter.getPosition());
                nearestEnemy = enemy;
            }
        }

        return nearestEnemy;
    }

    private void addEnemy() {
        //sets the enemyPosition at least .5 away from the center of the screen
        Vector enemyPosition;

        do {
            enemyPosition = new Vector(( Math.random() - .5 ) * 2, ( Math.random() - .5 ) * 2);
        } while (enemyPosition.difference(fighter.getPosition()) < .5);


        //adds a random type of Enemy
        switch ((int) ( Math.random() * 6 )) {
            case 0:
                enemyList.add(new Enemy(enemyPosition, 100 * ( Math.random() + .5 ), 10e-5 * ( Math.random() + .5 ), 100 * ( Math.random() + .5 )));
                break;
            case 1:
                enemyList.add(new Enemy(enemyPosition, 100, 3 * ( Math.random() + .5 ), 10e-5 * ( Math.random() + .5 ), 100));
                break;
            case 2:
                enemyList.add(new ArmoredEnemy(enemyPosition, 100, 7e-5 * ( Math.random() + .5 ), 200));
                break;
            case 3:
                enemyList.add(new ArmoredEnemy(enemyPosition, 100, 3 * ( Math.random() + .5 ), 7e-5 * ( Math.random() + .5 ), 200));
                break;
            case 4:
                enemyList.add(new SpeedEnemy(enemyPosition, 100, 14e-5 * ( Math.random() + .5 ), 50));
                break;
            case 5:
                enemyList.add(new SpeedEnemy(enemyPosition, 100, 3 * ( Math.random() + .5 ), 14e-5 * ( Math.random() + .5 ), 50));
                break;
        }//switch
    }

    private void addWaveOfEnemies() {
        int numberOfEnemies = (int) ( Math.random() * 3 ) + 2 - enemyList.size();

        for (int i = 0 ; i < numberOfEnemies ; i++) {
            addEnemy();
        }//i
    }

    //==================================================================================================================

    @Override
    public void init() {
        this.enemyList = new ArrayList<>();
        this.ammoList = new ArrayList<>();
        this.fireList = new ArrayList<>();

        StdDraw.setScale(-1, 1);

        fighter = new Fighter(new Vector(0, 0));
        fighter.setMaxSpeed(24e-5);

        addWaveOfEnemies();

        //add weapons to the Fighter while the Fighter has 1 or 0 Weapons or no Primary Weapon
        do {

            //for each Weapon index for the Fighter
            for (int weaponIndex = 0 ; weaponIndex < fighter.getMaxNumberOfWeapons() ; weaponIndex++) {

                if (Math.random() > .5) {
                    switch ((int) ( Math.random() * 3 )) {
                        case 0:
                            fighter.setWeapon(new Gun(.5, .2, .01, .01, .1, 10, 25, 1000), weaponIndex);
                            ( (Gun) fighter.getWeapon(weaponIndex) ).addAmmoSpeedUpgradePoints(10);
                            break;
                        case 1:
                            fighter.setWeapon(new MachineGun(.5, .5, .01, .03, .1, 10, 10, 500), weaponIndex);
                            ( (MachineGun) fighter.getWeapon(weaponIndex) ).addAmmoSpeedUpgradePoints(10);
                            break;
                        case 2:
                            fighter.setWeapon(new MissileLauncher(.75, .01, .05, .1, 10, 25, 2000), weaponIndex);
                            ( (MissileLauncher) fighter.getWeapon(weaponIndex) ).addAmmoSpeedUpgradePoints(10);
                            break;
                    }//switch
                }//if
                else {
                    fighter.setWeapon(null, weaponIndex);
                }
            }//weaponIndex

        } while (fighter.getPrimaryWeapon() == null || fighter.getNumberOfWeapons() < 2);
    }

    @Override
    public void run() {

        if (enemyList.size() < 2) {
            addWaveOfEnemies();
        }

        //==================================================================================================================
        //WEAPON FIRE
        //==================================================================================================================

        Vector fighterNetForce = new Vector();

        for (int weaponIndex = 0 ; weaponIndex < fighter.getMaxNumberOfWeapons() ; weaponIndex++) {
            Weapon weapon = fighter.getWeapon(weaponIndex);
            if (weapon == null) continue;

            Ammo ammo = null;

            //for the primary Weapon - if the nearest Enemy is within range - fire
            if (weaponIndex == 0 && weapon.isReadyToFire()) {
                if (getNearestEnemy().getPosition().difference(fighter.getPosition()) <= weapon.getRange())
                    ammo = weapon.fire();
                weapon.setLastShotFiredFrameStamp(Game.currentFrame);
            } else if (weapon.isReadyToFire()) {
                ammo = weapon.fire();
                weapon.setLastShotFiredFrameStamp(Game.currentFrame);
            }

            //recoil force
            if (ammo != null) {
                ammoList.add(ammo);

                //push the fighter back with the recoil force of the GameObjects.StationaryGameObjects.Weapon
                Vector recoilForce = ammo.getTotalVelocity().unitVector().scale(weapon.getRecoilForce());
                fighterNetForce.update(recoilForce);
            }
        }

        //==================================================================================================================
        //AMMO MOVEMENT
        //==================================================================================================================

        for (int i = 0 ; i < ammoList.size() ; i++) {
            Ammo ammo = ammoList.get(i);

            //remove the ammo if it is out of range
            if (!ammo.isActive()) {

                if (ammo instanceof Missile) {
                    fireList.addAll(( (Missile) ammo ).explode());
                }

                ammoList.remove(ammo);
                i--;
                continue;
            }

            //if the Ammo is really a Missile
            if (ammo instanceof Missile) {
                ( (Missile) ammo ).setTargetedEnemy(enemyList);
                ( (Missile) ammo ).move(fighter);

                if (Math.random() > .035 * Game.FRAME_DELAY) {
                    Vector position = new Vector(
                            ammo.getPositionX() - Math.cos(ammo.getTotalVelocity().getRadian()) * ammo.getSize() / 2 * GameObject.PIXEL_SIZE,
                            ammo.getPositionY() - Math.sin(ammo.getTotalVelocity().getRadian()) * ammo.getSize() / 2 * GameObject.PIXEL_SIZE
                    );

                    fireList.add(new Fire(position, ammo.getTotalVelocity().getInverse().getRadian(), 100));
                }
            } else {
                ammo.move();
            }

            if (ammo.isTouching(fighter)) {
                fighter.addHealth(-ammo.getDamage());

                //knockback force
                fighterNetForce.update(ammo.getTotalVelocity().unitVector().scale(ammo.getKnockBackForce()));

                if (ammo instanceof Missile) {
                    fireList.addAll(( (Missile) ammo ).explode());
                }

                ammoList.remove(ammo);
                i--;
                continue;
            }
        }

        //==================================================================================================================
        //FIRE MOVEMENT
        //==================================================================================================================

        for (int i = 0 ; i < fireList.size() ; i++) {
            Fire fire = fireList.get(i);
            fire.move(null, true);

            if (!fire.isActive()) {
                fireList.remove(fire);
                i--;
                continue;
            }

            fire.timeAlive -= Game.FRAME_DELAY;
        }

        //==================================================================================================================
        //FIGHTER MOVEMENT
        //==================================================================================================================

        Vector desiredDirection = new Vector();

        Enemy nearestEnemy = getNearestEnemy();

        for (Enemy enemy : enemyList) {
            Vector enemyStrengthVector = fighter.getPosition().differenceVector(enemy.getPosition()).scale(1 / fighter.getDistance(enemy));

            if (fighter.getDistance(enemy) < fighter.getPrimaryWeapon().getRange() * .75)
                enemyStrengthVector = enemyStrengthVector.getInverse();

            if (enemy == nearestEnemy)
                enemyStrengthVector.scale(2);

            desiredDirection.update(enemyStrengthVector);
        }

        desiredDirection = desiredDirection.unitVector().scale(fighter.getMaxAcceleration());


        fighter.setAcceleration(desiredDirection);

        fighter.move(fighterNetForce, true);

        if (nearestEnemy != null)
            fighter.setDirection(fighter.getDirection().update(fighter.getPosition().differenceVector(nearestEnemy.getPosition()).unitVector().scale(.05)));

        //==================================================================================================================
        //ENEMY MOVEMENT
        //==================================================================================================================

        //enemy
        for (int enemyIndex = 0 ; enemyIndex < enemyList.size() ; enemyIndex++) {
            Enemy enemy = enemyList.get(enemyIndex);
            Vector enemyNetForce = new Vector();

            //ammo
            for (int ammoIndex = 0 ; ammoIndex < ammoList.size() ; ammoIndex++) {
                Ammo ammo = ammoList.get(ammoIndex);

                //if the enemy is touching the ammo
                if (enemy.isTouching(ammo)) {
                    Vector ammoForce = ammo.getTotalVelocity().unitVector().scale(ammo.getKnockBackForce());
                    enemyNetForce.update(ammoForce);

                    enemy.addHealth(-ammo.getDamage());

                    if (ammo instanceof Missile) {
                        fireList.addAll(( (Missile) ammo ).explode());
                    }

                    ammoList.remove(ammo);
                }

            }

            if (enemy.getHealth() == 0) {
                enemyList.remove(enemy);
            }

            //setting up the GameObjects.MovingGameObjects.Enemy's acceleration
            enemy.setDesiredDirection(fighter.getPosition());
            enemy.setAcceleration(enemy.getDesiredDirection().scaledVector(enemy.getMaxAcceleration()));

            enemy.move(enemyNetForce, true);

            //==================================================================================================================
            //OTHER STUFF
            //==================================================================================================================

            if (playGameButton.isClicked()) {
                Game.gameModeID = PlayableGame.getName() + "_init";
            }
        }
    }

    @Override
    public void draw() {
        StdDraw.clear(getBackground());

        for (Fire fire : fireList) {
            fire.draw();
        }

        for (Ammo ammo : ammoList) {
            ammo.draw();
        }

        for (Enemy enemy : enemyList) {
            enemy.draw();
        }

        fighter.draw();

        for (Button button : buttons) {
            button.draw();
        }

        new Text("CAN'T THINK OF GAME NAME YET", 40).draw(0, .75);

        StdDraw.show();
    }
}
