import GameStructureElements.Game;
import edu.princeton.cs.introcs.StdDraw;

public class Runner {
    public static void main(String[] args) throws InterruptedException {
        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(750, 750);

        Game game = new Game();
    }
}
