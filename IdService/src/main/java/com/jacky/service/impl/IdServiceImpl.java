package com.jacky.service.impl;

import com.jacky.service.bean.Id;
import com.jacky.service.impl.Populater.AtomicIdPopulator;
import com.jacky.service.impl.Populater.IdPopulator;
import com.jacky.service.impl.Populater.LockIdPopulator;
import com.jacky.service.impl.Populater.SyncIdPopulator;
import com.jacky.service.impl.bean.IdType;
import com.jacky.util.CommonUtils;

/**
 * Created by Jacky on 2018/8/28.
 */
public class IdServiceImpl extends AbstractIdServiceImpl {
    //可以在运行时指定的参数变量
    private static final String SYNC_LOCK_IMPL_KEY = "vesta.sync.lock.impl.key";
    private static final String ATOMIC_IMPL_KEY = "vesta.atomic.impl.key";
    protected IdPopulator idPopulator;

    public IdServiceImpl() {
        super();
        initPopulator();
    }

    public IdServiceImpl(String type) {
        super(type);
        initPopulator();
    }

    public IdServiceImpl(IdType idType) {
        super(idType);
        initPopulator();
    }

    public void initPopulator() {

        if (idPopulator != null) {
            log.info("The " + idPopulator.getClass().getCanonicalName() + " is used.");
        } else if (CommonUtils.isPropKeyOn(SYNC_LOCK_IMPL_KEY)) {
            log.info("The SyncIdPopulator is used.");
            idPopulator = new SyncIdPopulator();
        } else if (CommonUtils.isPropKeyOn(ATOMIC_IMPL_KEY)) {
            log.info("The AtomicIdPopulator is used.");
            idPopulator = new AtomicIdPopulator();
        } else {
            log.info("The default LockIdPopulator is used.");
            idPopulator = new LockIdPopulator();
        }
    }


    protected void populateId(Id id) {
        idPopulator.populateId(id, this.idMeta);
    }

    public void setIdPopulator(IdPopulator idPopulator) {
        this.idPopulator = idPopulator;
    }
}
