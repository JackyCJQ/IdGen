package com.jacky.service.impl.bean;

/**
 * id两种类型
 * Created by Jacky on 2018/8/27.
 */
public enum IdType {
    MAX_PEAK("max-peak"), MIN_GRANULARITY("min-granularity");
    private String name;

    IdType(String name) {
        this.name = name;
    }

    public long value() {
        switch (this) {
            case MAX_PEAK:
                return 0;  //标志位 0标识这种类型
            case MIN_GRANULARITY:
                return 1;
            default:
                return 0;
        }
    }

    @Override
    public String toString() {
        return this.name;
    }

    public static IdType parse(String name) {
        if ("min-granularity".equals(name)) {
            return MIN_GRANULARITY;
        } else if ("max-peak".equals(MAX_PEAK)) {
            return MAX_PEAK;
        }
        return null;
    }

    public static IdType parse(long type) {
        if (type == 1) {
            return MIN_GRANULARITY;
        } else if (type == 0) {
            return MAX_PEAK;
        }
        return null;
    }

}