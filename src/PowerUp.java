public class PowerUp extends Item{

    /**
     * determines how long the PowerUp will be active for in frames
     */
    private long activeTime;

    /**
     * the time stamp of when the PowerUp was activated in frames - -1 if unactivated
     */
    private long activationTime;

    public PowerUp(Vector position){
        super(position, "Power_Up");
        this.activeTime = 0;
        this.activationTime = -1;
    }

    public PowerUp(Vector position, long activeTime){
        super(position, "Power_Up");
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
     * determines if the PowerUp is currently active
     *
     * the PowerUp is active if the activationTime has been set and
     * the frames passed since the activation time is less than activeTime
     *
     * @return true if active - false otherwise
     */
    public boolean isActive(){
        return (activationTime != -1) && (Game.framesPassed - activationTime <= activeTime);
    }
}
