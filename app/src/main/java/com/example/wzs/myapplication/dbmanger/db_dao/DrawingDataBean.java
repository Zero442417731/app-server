package com.example.wzs.myapplication.dbmanger.db_dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by hxcs-02 on 2017/8/18.
 */

@Entity
public class DrawingDataBean {
    @Id
    private Long id;
    private long Ttime;
    private int action;
    private float X;
    private float Y;
    @Generated(hash = 1824376398)
    public DrawingDataBean(Long id, long Ttime, int action, float X, float Y) {
        this.id = id;
        this.Ttime = Ttime;
        this.action = action;
        this.X = X;
        this.Y = Y;
    }
    @Generated(hash = 900195387)
    public DrawingDataBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public long getTtime() {
        return this.Ttime;
    }
    public void setTtime(long Ttime) {
        this.Ttime = Ttime;
    }
    public int getAction() {
        return this.action;
    }
    public void setAction(int action) {
        this.action = action;
    }
    public float getX() {
        return this.X;
    }
    public void setX(float X) {
        this.X = X;
    }
    public float getY() {
        return this.Y;
    }
    public void setY(float Y) {
        this.Y = Y;
    }

}
