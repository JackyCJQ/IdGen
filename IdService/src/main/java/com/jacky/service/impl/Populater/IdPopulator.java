package com.jacky.service.impl.Populater;

import com.jacky.service.bean.Id;
import com.jacky.service.impl.bean.IdMeta;

/**
 * Created by Jacky on 2018/8/28.
 */
public interface IdPopulator {
    void populateId(Id id, IdMeta idMeta);
}
