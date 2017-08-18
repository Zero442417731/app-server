package com.example.wzs.myapplication.weight;

import java.util.Date;

/**
 * Created by hxcs-02 on 2017/8/16.
 */

public class TimeDifference {


    private long shijian;

    public TimeDifference() {
        shijian = new Date().getTime();
    }

    /**
     * 返回时间差，每调用一次返回与上一次的时间差（毫秒）
     *
     * @return 时间差
     */
    public long shijiancha() {
        long shijiancha = shijian;
        shijian = (new Date()).getTime();
        return shijian - shijiancha;

    }
}

