package com.jacky.service.bean;

import java.io.Serializable;

/**
 * 生成的id，一共是64位
 * Created by Jacky on 2018/8/27.
 */
public class Id implements Serializable {
    private static final long serialVersionUID = 6870931236218221183L;
    private long machine;//10位
    private long seq;//10/20位
    private long time;//30/40位
    private long genMethod;//2位
    private long type;//1位
    private long version;//1位

    public Id() {
    }

    public Id(long machine, long seq, long time, long genMethod, long type, long version) {
        super();
        this.machine = machine;
        this.seq = seq;
        this.time = time;
        this.genMethod = genMethod;
        this.type = type;
        this.version = version;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getMachine() {
        return machine;
    }

    public void setMachine(long machine) {
        this.machine = machine;
    }

    public long getSeq() {
        return seq;
    }

    public void setSeq(long seq) {
        this.seq = seq;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getGenMethod() {
        return genMethod;
    }

    public void setGenMethod(long genMethod) {
        this.genMethod = genMethod;
    }

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Id{" +
                "machine=" + machine +
                ", seq=" + seq +
                ", time=" + time +
                ", genMethod=" + genMethod +
                ", type=" + type +
                ", version=" + version +
                '}';
    }
}
