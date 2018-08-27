package com.jacky.service.impl.converter;

import com.jacky.service.bean.Id;
import com.jacky.service.impl.bean.IdMeta;
import com.jacky.service.impl.bean.IdMetaFactory;
import com.jacky.service.impl.bean.IdType;

/**
 * Created by Jacky on 2018/8/27.
 */
public class IdConverterImpl implements IdConverter {

    private IdMeta meta;

    public IdConverterImpl() {

    }

    public IdConverterImpl(IdType type) {
        this(IdMetaFactory.getIdMeta(type));
    }

    public IdConverterImpl(IdMeta idMeta) {
        this.meta = idMeta;
    }


    public long convert(Id id) {
        return doConvert(id, meta);
    }

    public long doConvert(Id id, IdMeta idMeta) {
        long result = 0;
        result |= id.getMachine();
        result |= id.getSeq() << idMeta.getSeqBitsStartPos();
        result |= id.getTime() << idMeta.getTimeBitsStartPos();
        result |= id.getGenMethod() << idMeta.getGenMethodStartPos();
        result |= id.getType() << idMeta.getTypeBitsStartPos();
        result |= id.getVersion() << idMeta.getVersionBitsStartPos();
        return result;
    }


    public Id convert(long id) {
        return doConvert(id, meta);
    }

    public Id doConvert(long id, IdMeta idMeta) {

        Id result = new Id();
        result.setMachine(id & idMeta.getMechineBitsMask());
        result.setSeq((id >>> idMeta.getSeqBitsStartPos()) & idMeta.getSeqBitsMask());
        result.setTime((id >>> idMeta.getTimeBitsStartPos()) & idMeta.getTimeBitsMask());
        result.setGenMethod((id >>> idMeta.getGenMethodStartPos()) & idMeta.getGenMethodBitsMask());
        result.setType((id >>> idMeta.getTypeBitsStartPos()) & idMeta.getTypeBitsMask());
        result.setVersion(id >>> idMeta.getVersionBitsStartPos() & idMeta.getVersionBitsMask());
        return result;
    }

    public IdMeta getMeta() {
        return meta;
    }

    public void setMeta(IdMeta meta) {
        this.meta = meta;
    }
}
