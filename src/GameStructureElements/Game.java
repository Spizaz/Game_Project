package GameStructureElements;

import java.awt.*;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import edu.princeton.cs.introcs.StdDraw;
import Toolkit.Button;

public class Game {

    /**
     * the maximum number of frames that passes in one second
     * also controls the overall game speed (smaller = faster)
     */
    public static final int FRAME_DELAY = 1000 / 100;

    /**
     * the color of most Buttons in the Game
     */
    public static final Color BUTTON_COLOR = new Color(56, 140, 234, 255);

    /**
     * the default Font of most Texts in the Game
     */
    public static final Font UI_FONT = new Font(null, Font.BOLD, 15);

    /**
     * the number of frames that have passed since the start of the game
     */
    public static volatile long currentFrame;

    /**
     * the gamemode that the game is currently in
     */
    public static String gameModeID = "";

    /**
     * the previous gamemode that the game is currently in
     */
    private String previousGameModeID = "";

    /**
     * the gamemode that the game is currently in
     */
    private GameMode gameMode = null;

    /**
     * the starting screen of the GameStructureElements.Game
     */
    private GameMenu gameMenu = new GameMenu(StdDraw.WHITE);

    /**
     * the playable game mode
     */
    private PlayableGame playableGame = new PlayableGame(StdDraw.WHITE);

    /**
     * the pause menu (duh)
     */
    private PauseMenu pauseMenu = new PauseMenu(StdDraw.WHITE);

    /**
     * the playable game mode
     */
    private Settings settingsMenu = new Settings(StdDraw.WHITE);

    private final BackgroundGame backgroundGame = new BackgroundGame();

    /**
     * the thread of the gameMode
     */
    private Thread gameModeThread;

    /**
     * how many frames the Game will ignore the mouse for
     */
    private static int ignoreMouse = 0;

    /**
     * the level of the volume
     */
    public static int volumeLevel = 3;

    private boolean backgroundGameActive = false;

    //==================================================================================================================
    public Game() throws InterruptedException {
        gameModeID = GameMenu.getName() + "_init";
        //gameModeID = PlayableGame.getName() + "_init";
        //gameModeID = PauseMenu.getName() + "_init";

        updateSounds();

        //==============================================================================================================
        //MAIN GAME LOOP
        //==============================================================================================================

        Timer timer = new Timer("Main_Game_Loop_Timer");
        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {

                //if there was a change in gameModeID
                if (!gameModeID.equals(previousGameModeID)) {

                    boolean gameModeInit = gameModeID.contains("_init");
                    boolean backgroundGameActiveSave = backgroundGameActive;

                    if (gameModeInit)
                        gameModeID = gameModeID.substring(0, gameModeID.length() - 5);

                    switch (gameModeID) {
                        case "Game_Menu":
                            gameMode = gameMenu;
                            backgroundGameActive = true;
                            break;
                        case "Playable_Game":
                            gameMode = playableGame;
                            backgroundGameActive = false;
                            break;
                        case "Pause_Menu":
                            gameMode = pauseMenu;
                            break;
                        case "Settings_Menu":
                            gameMode = settingsMenu;
                            break;
                    }

                    if (gameModeInit) {
                        try {
                            gameMode.init();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if (!backgroundGameActiveSave && backgroundGameActive) {
                        backgroundGame.init();
                    }

                    mouseClick();

                    previousGameModeID = gameModeID;
                }//change in game modes

                StdDraw.clear(gameMode.getBackground());

                if (backgroundGameActive) {
                    backgroundGame.run();
                    backgroundGame.draw();
                }

                gameMode.run();
                gameMode.draw();
                StdDraw.show();

                Game.currentFrame++;

                if (getIgnoreMouse() != 0) Game.ignoreMouse -= Game.FRAME_DELAY;
            }

        };

        timer.scheduleAtFixedRate(timerTask, 0, Game.FRAME_DELAY);

    }

    //==================================================================================================================

    public void setGameModeID(String gameModeID) {
        this.gameModeID = gameModeID;
    }

    public static int getIgnoreMouse() {
        if (ignoreMouse < 0) ignoreMouse = 0;
        return ignoreMouse;
    }

    public static void mouseClick() {
        ignoreMouse = 200;
    }

    public static boolean isMouseAvailable() {
        return getIgnoreMouse() == 0;
    }

    public static boolean isMouseActive() {
        return isMouseAvailable() && StdDraw.isMousePressed();
    }

    public void updateSounds() {
        Button.updateSounds();
    }
}
