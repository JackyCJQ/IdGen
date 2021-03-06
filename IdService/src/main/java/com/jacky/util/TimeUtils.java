package com.jacky.util;

import com.jacky.service.impl.bean.IdType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Jacky on 2018/8/27.
 */
public class TimeUtils {
    protected static final Logger log = LoggerFactory.getLogger(TimeUtils.class);
    public static final long EPOCH = 1535357328811L;


    public static void validateTimestamp(long lastTimestamp, long timestamp) {
        if (timestamp < lastTimestamp) {
            if (log.isErrorEnabled())
                log.error(String
                        .format("Clock moved backwards.  Refusing to generate id for %d second/milisecond.",
                                lastTimestamp - timestamp));

            throw new IllegalStateException(
                    String.format(
                            "Clock moved backwards.  Refusing to generate id for %d second/milisecond.",
                            lastTimestamp - timestamp));
        }
    }

    public static long tillNextTimeUnit(final long lastTimestamp, final IdType idType) {
        if (log.isInfoEnabled())
            log.info(String
                    .format("Ids are used out during %d. Waiting till next second/milisencond.",
                            lastTimestamp));

        long timestamp = genTime(idType);
        while (timestamp <= lastTimestamp) {
            timestamp = genTime(idType);
        }
        if (log.isInfoEnabled())
            log.info(String.format("Next second/milisencond %d is up.",
                    timestamp));
        return timestamp;
    }

    public static long genTime(final IdType idType) {
        if (idType == idType.MAX_PEAK) {
            return (System.currentTimeMillis() - TimeUtils.EPOCH) / 1000;
        } else if (idType == idType.MIN_GRANULARITY) {
            return (System.currentTimeMillis() - TimeUtils.EPOCH);
        }
        return (System.currentTimeMillis() - TimeUtils.EPOCH) / 1000;
    }


}
