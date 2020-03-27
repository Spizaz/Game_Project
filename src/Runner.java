public class Runner {
    public static void main(String[] args) throws InterruptedException {
        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(1200,675);
        StdDraw.setXscale(-1, 1);
        StdDraw.setYscale(-.5625, .5625);

        Game game = new Game();
    }
}
