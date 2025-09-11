package me.pajic.cmd.config;

public class ModConfig {
    private int miningDelay;
    private boolean enabledOnServers;

    public ModConfig() {
        miningDelay = 5;
        enabledOnServers = false;
    }

    public int getMiningDelay() {
        return miningDelay;
    }

    public boolean isEnabledOnServers() {
        return enabledOnServers;
    }

    public void setMiningDelay(int miningDelay) {
        this.miningDelay = miningDelay;
        validate();
    }

    public void setEnabledOnServers(boolean enabledOnServers) {
        this.enabledOnServers = enabledOnServers;
    }

    public void validate() {
        if (miningDelay < 0) miningDelay = 0;
        if (miningDelay > 20) miningDelay = 20;
    }
}
