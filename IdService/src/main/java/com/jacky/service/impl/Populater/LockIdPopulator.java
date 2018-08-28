package com.jacky.service.impl.Populater;

import com.jacky.service.bean.Id;
import com.jacky.service.impl.bean.IdMeta;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Jacky on 2018/8/28.
 */
public class LockIdPopulator extends BasePopulator {

    private Lock lock = new ReentrantLock();

    public LockIdPopulator() {

    }

    public void populateId(Id id, IdMeta idMeta) {
        lock.lock();
        try {
            super.populateId(id, idMeta);
        } finally {
            lock.unlock();
        }
    }

}
