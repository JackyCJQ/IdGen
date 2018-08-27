package com.jacky.service.impl.provider;

/**
 * Created by Jacky on 2018/8/27.
 */
public class PropertyMachineIdProvider implements MachineIdProvider {
    private long machineId;

    public long getMechineId() {
        return machineId;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }
}
