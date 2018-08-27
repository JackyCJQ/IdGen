package com.jacky.service.impl.bean;

/**
 * 源数据初始化工厂
 * Created by Jacky on 2018/8/27.
 */
public class IdMetaFactory {
    private static IdMeta maxPeak = new IdMeta((byte) 10, (byte) 20, (byte) 30, (byte) 2, (byte) 1, (byte) 1);
    private static IdMeta minGranularity = new IdMeta((byte) 10, (byte) 10, (byte) 40, (byte) 2, (byte) 1, (byte) 1);

    /**
     * 根据id类型获取对应的id结构
     *
     * @param type
     * @return
     */
    public static IdMeta getIdMeta(IdType type) {
        if (IdType.MAX_PEAK.equals(type)) {
            return maxPeak;
        } else if (IdType.MIN_GRANULARITY.equals(type)) {
            return minGranularity;
        }
        return null;
    }
}
