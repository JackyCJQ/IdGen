package com.jacky.service.impl.Populater;

import com.jacky.service.bean.Id;
import com.jacky.service.impl.bean.IdMeta;
import com.jacky.service.impl.bean.IdType;
import com.jacky.util.TimeUtils;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Jacky on 2018/8/28.
 */
public class AtomicIdPopulator implements IdPopulator, ResetPopulator {
    class Variant {
        private long sequence = 0;
        private long lastTimestamp = -1;
    }

    private AtomicReference<Variant> variant = new AtomicReference<Variant>();

    public AtomicIdPopulator() {
        super();
    }


    public void reset() {
        variant = new AtomicReference<Variant>();
    }

    public void populateId(Id id, IdMeta idMeta) {
        Variant varOld, varNew;
        long timestamp, sequence;
        while (true) {
            varOld = variant.get();
            timestamp = TimeUtils.genTime(IdType.parse(id.getType()));
            TimeUtils.validateTimestamp(varOld.lastTimestamp, timestamp);
            sequence = varOld.sequence;
            //如果在同一个时间片内
            if (timestamp == varOld.lastTimestamp) {
                sequence++;
                sequence &= idMeta.getSeqBitsMask();
                //如果sequence已经用完了 只能等到下个时间片内了
                if (sequence == 0) {
                    timestamp = TimeUtils.tillNextTimeUnit(varOld.lastTimestamp, IdType.parse(id.getType()));
                }

            } else {
                sequence = 0;
            }
            varNew = new Variant();
            varNew.sequence = sequence;
            varNew.lastTimestamp = timestamp;

            if (variant.compareAndSet(varOld, varNew)) {
                id.setSeq(sequence);
                id.setTime(timestamp);
                break;
            }

        }
    }
}
