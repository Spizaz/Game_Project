package GameStructureElements;

import java.util.Timer;
import java.util.TimerTask;

import edu.princeton.cs.introcs.StdDraw;

public class Game {

    /**
     * the maximum number of frames that passes in one second
     * also controls the overall game speed (smaller = faster)
     */
    public static final int FRAME_DELAY = 1000 / 100;

    public static final double FRAMES_PER_SECOND = 1000. / FRAME_DELAY;

    /**
     * the number of frames that have passed since the start of the game
     */
    public static volatile long currentFrame;

    /**
     * the gamemode that the game is currently in
     */
    private String gameModeID;

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
    private GameMode gameMenu = new GameMenu(StdDraw.WHITE);

    /**
     * the playable game mode
     */
    private GameMode playableGame = new PlayableGame(StdDraw.WHITE);

    /**
     * the playable game mode
     */
    private GameMode pauseMenu = new PauseMenu(StdDraw.WHITE);

    /**
     * the thread of the gameMode
     */
    private Thread gameModeThread;

    //==================================================================================================================

    public Game() throws InterruptedException {
        //gameModeID = GameMenu.getName();
        gameModeID = PlayableGame.getName();
        gameMode = null;
        previousGameModeID = "";
        Timer timer = new Timer("Game_Timer");
        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {

                //if there was a change in gameModeID
                if (!gameModeID.equals(previousGameModeID)) {

                    switch (gameModeID) {
                        case "Game_Menu":
                            gameMode = gameMenu;
                            break;
                        case "Playable_Game":
                            gameMode = playableGame;
                            break;
                        case "Pause_Menu":
                            gameMode = pauseMenu;
                            break;
                    }

                    previousGameModeID = gameModeID;
                    gameMode.init();
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
