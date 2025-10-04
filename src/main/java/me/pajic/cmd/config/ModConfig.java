package me.pajic.cmd.config;

public class ModConfig {
    private int miningDelay;
    private int placementDelay;
    private boolean enabledOnServers;

    public ModConfig() {
        miningDelay = 5;
        placementDelay = 4;
        enabledOnServers = false;
    }

    public int getMiningDelay() {
        return miningDelay;
    }

    public int getPlacementDelay() {
        return placementDelay;
    }

    public boolean isEnabledOnServers() {
        return enabledOnServers;
    }

    public void setMiningDelay(int miningDelay) {
        this.miningDelay = validate(miningDelay);
    }

    public void setPlacementDelay(int placementDelay) {
        this.placementDelay = validate(placementDelay);
    }

    public void setEnabledOnServers(boolean enabledOnServers) {
        this.enabledOnServers = enabledOnServers;
    }

    private int validate(int i) {
        if (i < 0) return 0;
        else if (miningDelay > 20) return 20;
        return i;
    }

    public void validateAll() {
        miningDelay = validate(miningDelay);
        placementDelay = validate(placementDelay);
    }
}
