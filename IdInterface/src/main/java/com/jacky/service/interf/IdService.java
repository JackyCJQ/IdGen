package com.jacky.service.interf;

import com.jacky.service.bean.Id;

import java.util.Date;

/**
 * 生成id的核心接口
 * Created by Jacky on 2018/8/27.
 */
public interface IdService {

    /**
     * 生成id
     *
     * @return
     */
    long genId();

    /**
     * 根据特定的时间生成id
     *
     * @param id
     * @return
     */
    Id expId(long id);

    /**
     * 一下是手工生成id
     *
     * @param time
     * @param seq
     * @return
     */
    long makeId(long time, long seq);

    long makeId(long time, long seq, long machine);

    long makeId(long genMethod, long time, long seq, long machine);

    long makeId(long type, long genMethod, long time, long seq, long machine);

    long makeId(long version, long type, long genMethod, long time, long seq, long mechine);

    /**
     * 转为可读的时间格式
     *
     * @param time
     * @return
     */
    Date transTime(long time);

}
