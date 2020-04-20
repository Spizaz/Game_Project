package GameObjects.StationaryGameObjects;

import Toolkit.Vector;
import GameStructureElements.Game;

public class PowerUp extends Item {

    /**
     * determines how long the GameObjects.StationaryGameObjects.PowerUp will be active for in frames
     */
    private long activeTime;

    /**
     * the time stamp of when the GameObjects.StationaryGameObjects.PowerUp was activated in frames - -1 if unactivated
     */
    private long activationTime;

    public PowerUp(Vector position, double spawnRate) {
        super(position, "Power_Up", spawnRate);
        this.activeTime = 0;
        this.activationTime = -1;
    }

    public PowerUp(Vector position, String name, double spawnRate, long activeTime) {
        super(position, name, spawnRate);
        this.activeTime = activeTime;
        this.activationTime = -1;
    }

    //==================================================================================================================

    public double getActiveTime() {
        return activeTime;
    }

    public double getActivationTime() {
        return activationTime;
    }

    public void setActivationTime(long activationTime) {
        this.activationTime = activationTime;
    }

    //==================================================================================================================

    /**
     * determines if the GameObjects.StationaryGameObjects.PowerUp is currently active
     * <p>
     * the GameObjects.StationaryGameObjects.PowerUp is active if the activationTime has been set and
     * the frames passed since the activation time is less than activeTime
     *
     * @return true if active - false otherwise
     */
    public boolean isActive() {
        return ( activationTime != -1 ) && ( Game.currentFrame - getActivationTime() <= getActiveTime() );
    }
}
