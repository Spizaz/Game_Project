import java.util.Timer;
import java.util.TimerTask;

public class Game {

    /**
     * the maximum number of frames that passes in one second
     * also controls the overall game speed (smaller = faster)
     */
    public static final int FRAME_DELAY = 1000 / 60;

    /**
     * the number of frames that have passed since the start of the game
     */
    public static volatile long currentFrame;

    /**
     * the gamemode that the game is currently in
     */
    private String gameModeID;

    /**
     * the gamemode that the game is currently in
     */
    private GameMode gameMode;

    /**
     * the playable game mode
     */
    private PlayableGame playableGame = new PlayableGame(StdDraw.WHITE);

    /**
     * the thread of the gameMode
     */
    private Thread gameModeThread;

    public Game() throws InterruptedException {
        gameModeID = playableGame.getName();
        gameMode = null;
        Timer timer = new Timer("Game_Timer");

        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {

                if(gameModeID.equals(playableGame.getName())){
                    gameMode = playableGame;
                }

                gameMode.run();
                gameMode.draw();

                if(Game.currentFrame % 60 == 0) System.out.println(Game.currentFrame/60);

                Game.currentFrame++;
            }

        };

        timer.scheduleAtFixedRate(timerTask, 0, Game.FRAME_DELAY);

    }
}
