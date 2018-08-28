package com.jacky.service.impl.Populater;

import com.jacky.service.bean.Id;
import com.jacky.service.impl.bean.IdMeta;
import com.jacky.service.impl.bean.IdType;
import com.jacky.util.TimeUtils;

/**
 * Created by Jacky on 2018/8/28.
 */
public class BasePopulator implements IdPopulator, ResetPopulator {

    protected long sequence = 0;
    protected long lastTimestamp = -1;

    public BasePopulator() {
        super();
    }

    public void reset() {
        sequence = 0;
        lastTimestamp = -1;
    }

    public void populateId(Id id, IdMeta idMeta) {
        long timestamp = TimeUtils.genTime(IdType.parse(id.getType()));
        TimeUtils.validateTimestamp(lastTimestamp, timestamp);
        if (timestamp == lastTimestamp) {
            sequence++;
            sequence &= idMeta.getSeqBitsMask();
            if (sequence == 0) {
                //如果浮动值已经满了，知道下一个刻度时间内
                timestamp = TimeUtils.tillNextTimeUnit(lastTimestamp, IdType.parse(id.getType()));
            }
        } else {
            lastTimestamp = timestamp;
            sequence = 0;
        }
        id.setSeq(sequence);
        id.setTime(timestamp);

    }
}
