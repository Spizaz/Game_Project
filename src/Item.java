public class Item extends StationaryGameObject {

    /**
     * the chance that the Item will spawn
     */
    private double spawnRate;

    //==================================================================================================================

    public Item(){
        super("Item");
        setSpriteFilepath("Images/unknown_tile.png");
    }

    public Item(Vector position, String name, double width, double height){
        super(position, name, width, height);
        this.spawnRate = 0;
    }

    public Item(Vector position, String name, double spawnRate, double width, double height){
        super(position, name, width, height);
        this.spawnRate = spawnRate;
    }

    //==================================================================================================================
}
