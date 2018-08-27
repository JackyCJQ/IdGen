package com.jacky.service.impl;

import com.jacky.service.bean.Id;
import com.jacky.service.impl.bean.IdMeta;
import com.jacky.service.impl.bean.IdType;
import com.jacky.service.impl.converter.IdConverter;
import com.jacky.service.impl.provider.MachineIdProvider;
import com.jacky.service.interf.IdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * id生成服务的抽象实现
 * Created by Jacky on 2018/8/27.
 */
public class AbstractIdServiceImpl implements IdService {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    /**
     * 默认实现
     */
    protected long machineId = -1;
    protected long genMethod = 0;
    protected long type = 0;
    protected long version = 0;

    /**
     * id生成类型以及对应的编码设置
     */
    protected IdType idType;
    protected IdMeta idMeta;

    protected IdConverter idConverter;
    protected MachineIdProvider machineIdProvider;

    public AbstractIdServiceImpl() {
        idType = IdType.MAX_PEAK;
    }

    public AbstractIdServiceImpl(String type) {
        idType = IdType.parse(type);
    }

    public AbstractIdServiceImpl(IdType idType) {
        this.idType = idType;
    }

    public void init() {
        this.machineId = machineIdProvider.getMechineId();
        if (machineId < 0) {
            log.error("The machine ID is not configured properly so that Vesta Service refuses to start.");

            throw new IllegalStateException(
                    "The machine ID is not configured properly so that Vesta Service refuses to start.");

        }



    }


    public IdMeta getIdMeta() {
        return idMeta;
    }

    public long genId() {
        return 0;
    }

    public Id expId(long id) {
        return null;
    }

    public long makeId(long time, long seq) {
        return 0;
    }

    public long makeId(long time, long seq, long machine) {
        return 0;
    }

    public long makeId(long genMethod, long time, long seq, long machine) {
        return 0;
    }

    public long makeId(long type, long genMethod, long time, long seq, long machine) {
        return 0;
    }

    public long makeId(long version, long type, long genMethod, long time, long seq, long mechine) {
        return 0;
    }

    public Date transTime(long time) {
        return null;
    }
}
