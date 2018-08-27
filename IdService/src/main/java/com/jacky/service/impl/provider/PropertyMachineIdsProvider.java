package com.jacky.service.impl.provider;

/**
 * Created by Jacky on 2018/8/27.
 */
public class PropertyMachineIdsProvider implements MachineIdsProvider {

    private long[] machineIds;
    private int currentIndex;

    public long getNextMachingId() {
        return getMachineId();
    }

    public long getMachineId() {
        return machineIds[currentIndex++ % machineIds.length];
    }

    public void setMachineIds(long[] machineIds) {
        this.machineIds = machineIds;
    }

}
