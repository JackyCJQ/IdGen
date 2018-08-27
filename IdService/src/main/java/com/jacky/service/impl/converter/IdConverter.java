package com.jacky.service.impl.converter;

import com.jacky.service.bean.Id;

/**
 * Created by Jacky on 2018/8/27.
 */
public interface IdConverter {
    long convert(Id id);

    Id convert(long id);
}
