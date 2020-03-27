public class Wall extends GameObject{

    /**
     * all the individual points that make up the line
     */
    private Vector[] points;

    //==================================================================================================================

    public Wall(double x1, double y1, double x2, double y2) {
        super("Wall");
        points = new Vector[100];

        double deltaX = (x2 - x1) / points.length;
        double deltaY = (y2 - y1) / points.length;

        for (int i = 0 ; i < points.length ; i++) {
            points[i] = new Vector( x1 + i * deltaX, y1 + i * deltaY);
        }
    }

    //==================================================================================================================

    public void draw(){
        StdDraw.line(points[0].getX(), points[0].getY(), points[points.length - 1].getX(), points[points.length - 1].getY());
    }
}
