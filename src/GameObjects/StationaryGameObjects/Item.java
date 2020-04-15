package GameObjects.StationaryGameObjects;

import Toolkit.Vector;

public class Item extends StationaryGameObject {

    /**
     * the chance that the GameObjects.StationaryGameObjects.Item will spawn
     */
    private double spawnRate;

    //==================================================================================================================

    public Item() {
        super("GameObjects.StationaryGameObjects.Item");
        setSpriteFilepath("Images/unknown_tile.png");
    }

    public Item(Vector position, String name, double spawnRate) {
        super(position, name, 1);
        this.spawnRate = spawnRate;
    }

    //==================================================================================================================
}
