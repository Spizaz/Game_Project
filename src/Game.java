import java.util.Timer;
import java.util.TimerTask;

public class Game {

    /**
     * the number of frames that have passed since the start of the game
     */
    public static volatile long currentFrame;

    /**
     * the gamemode that the game is currently in
     */
    private String gameModeID;

    /**
     * the name of the gamemode that was just running
     */
    private String previousGameModeID;

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
        previousGameModeID = "";
        gameModeID = playableGame.getName();
        gameMode = null;
        Timer timer = new Timer();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                boolean gameModeChange = false;

                if(gameModeID.equals(playableGame.getName()) && !previousGameModeID.equals(playableGame.getName())){
                    gameMode = playableGame;
                    previousGameModeID = playableGame.getName();
                    gameModeChange = true;
                }

                if(gameModeChange){
                    gameModeThread = new Thread(gameMode,playableGame.getName() + "_Thread");
                    gameModeThread.start();
                }

                gameMode.draw();

                currentFrame++;
            }
        };

        timer.scheduleAtFixedRate(timerTask, 0, 1000/60);

    }
}
