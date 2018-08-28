package com.jacky.service.impl;

import com.jacky.service.bean.Id;
import com.jacky.service.impl.bean.IdMeta;
import com.jacky.service.impl.bean.IdMetaFactory;
import com.jacky.service.impl.bean.IdType;
import com.jacky.service.impl.converter.IdConverter;
import com.jacky.service.impl.converter.IdConverterImpl;
import com.jacky.service.impl.provider.MachineIdProvider;
import com.jacky.service.interf.IdService;
import com.jacky.util.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * id生成服务的抽象实现
 * Created by Jacky on 2018/8/27.
 */
public abstract class AbstractIdServiceImpl implements IdService {
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
        //机器码不能为空
        if (machineId < 0) {
            log.error("The machine ID is not configured properly so that Vesta Service refuses to start.");

            throw new IllegalStateException(
                    "The machine ID is not configured properly so that Vesta Service refuses to start.");

        }
        //如果idMeta为空
        if (this.idMeta == null) {
            this.setIdMeta(IdMetaFactory.getIdMeta(idType));
            this.setType(idType.value());
        } else {
            if (this.idMeta.getTimeBits() == 30) {
                setType(0);
            } else if (this.idMeta.getTimeBits() == 40) {
                setType(1);
            } else {
                throw new RuntimeException("Init Error. The time bits in IdMeta should be set to 30 or 40!");
            }
        }
        setIdConverter(new IdConverterImpl(this.idMeta));

    }

    public IdMeta getIdMeta() {
        return idMeta;
    }

    public long genId() {
        Id id = new Id();
        id.setMachine(machineId);
        id.setGenMethod(genMethod);
        id.setType(type);
        id.setVersion(version);
        populateId(id);
        long ret = idConverter.convert(id);
        // Use trace because it cause low performance
        if (log.isTraceEnabled())
            log.trace(String.format("Id: %s => %d", id, ret));
        return ret;
    }

    protected abstract void populateId(Id id);

    public Id expId(long id) {

        return idConverter.convert(id);
    }

    public long makeId(long time, long seq) {
        return makeId(time, seq, machineId);
    }

    public long makeId(long time, long seq, long machine) {

        return makeId(genMethod, time, seq, machine);
    }

    public long makeId(long genMethod, long time, long seq, long machine) {

        return makeId(type, genMethod, time, seq, machine);
    }

    public long makeId(long type, long genMethod, long time, long seq, long machine) {

        return makeId(version, type, genMethod, time, seq, machine);
    }

    public long makeId(long version, long type, long genMethod, long time, long seq, long mechine) {
        IdType idType = IdType.parse(type);
        Id id = new Id(mechine, seq, time, genMethod, type, version);
        IdConverter idConverter = new IdConverterImpl(idType);
        return idConverter.convert(id);
    }

    public Date transTime(long time) {
        if (idType == IdType.MAX_PEAK) {
            return new Date(time * 1000 + TimeUtils.EPOCH);
        } else if (idType == IdType.MIN_GRANULARITY) {
            return new Date(time + TimeUtils.EPOCH);
        }
        return null;
    }

    public void setIdMeta(IdMeta idMeta) {
        this.idMeta = idMeta;
    }

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }

    public IdConverter getIdConverter() {
        return idConverter;
    }

    public void setIdConverter(IdConverter idConverter) {
        this.idConverter = idConverter;
    }
}
