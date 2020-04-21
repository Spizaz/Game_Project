package GameStructureElements;

import java.awt.*;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import edu.princeton.cs.introcs.StdDraw;

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
    public static String gameModeID;

    /**
     * the previous gamemode that the game is currently in
     */
    private String previousGameModeID;

    /**
     * the gamemode that the game is currently in
     */
    private GameMode gameMode;

    /**
     * the starting screen of the GameStructureElements.Game
     */
    private GameMenu gameMenu = new GameMenu(StdDraw.WHITE);

    /**
     * the playable game mode
     */
    private PlayableGame playableGame = new PlayableGame(StdDraw.WHITE);

    /**
     * the playable game mode
     */
    private PauseMenu pauseMenu = new PauseMenu(StdDraw.WHITE);

    /**
     * the thread of the gameMode
     */
    private Thread gameModeThread;

    /**
     * the default font for all things in the Game
     */

    //==================================================================================================================
    public Game() throws InterruptedException {
        //gameModeID = GameMenu.getName() + "_init";
        gameModeID = PlayableGame.getName() + "_init";
        //gameModeID = PauseMenu.getName() + "_init";
        gameMode = null;
        previousGameModeID = "";
        Timer timer = new Timer("Game_Timer");
        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {

                //if there was a change in gameModeID
                if (!gameModeID.equals(previousGameModeID)) {

                    boolean init = gameModeID.contains("_init");

                    if (init)
                        gameModeID = gameModeID.substring(0, gameModeID.length() - 5);

                    switch (gameModeID) {
                        case "Game_Menu":
                            gameMode = gameMenu;
                            break;
                        case "Playable_Game":
                            gameMode = playableGame;
                            playableGame.setIgnoreMouse(15);
                            break;
                        case "Pause_Menu":
                            gameMode = pauseMenu;
                            pauseMenu.setPreviousGameModeID(previousGameModeID);
                            break;
                    }

                    if (init) {
                        try {
                            gameMode.init();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }


                    previousGameModeID = gameModeID;
                }

                gameMode.run();
                gameMode.draw();

                Game.currentFrame++;
            }

        };

        timer.scheduleAtFixedRate(timerTask, 0, Game.FRAME_DELAY);

    }

    //==================================================================================================================


    public void setGameModeID(String gameModeID) {
        this.gameModeID = gameModeID;
    }
}
