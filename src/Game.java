public class Game {

    /**
     * the number of frames that have passed since the start of the game
     */
    public static volatile long framesPassed;

    /**
     * the gamemode that the game is currently in
     */
    private String gameModeID;

    /**
     * the gamemode that the game is currently in
     */
    private GameMode gameMode;

    private PlayableGame playableGame = new PlayableGame(StdDraw.WHITE);

    public Game() throws InterruptedException {
        gameModeID = playableGame.getName();
        gameMode = null;

        while (true){
            if(gameModeID.equals(playableGame.getName())){
                gameMode = playableGame;
            }

            gameMode.draw();
        }
    }
}
