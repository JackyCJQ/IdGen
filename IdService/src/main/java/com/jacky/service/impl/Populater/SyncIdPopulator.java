package com.jacky.service.impl.Populater;

import com.jacky.service.bean.Id;
import com.jacky.service.impl.bean.IdMeta;

/**
 * Created by Jacky on 2018/8/28.
 */
public class SyncIdPopulator extends BasePopulator {
    public SyncIdPopulator() {
        super();
    }

    //利用synchronized来加锁的实现
    public synchronized void populateId(Id id, IdMeta idMeta) {
        super.populateId(id, idMeta);
    }
}
