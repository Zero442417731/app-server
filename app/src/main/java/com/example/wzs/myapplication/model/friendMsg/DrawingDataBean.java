package com.example.wzs.myapplication.model.friendMsg;

/**
 * Created by hxcs-02 on 2017/8/22.
 */

public class DrawingDataBean {


    /**
     * action : 动作, int类型：1down，2move，3up
     * X : 起始x坐标值,  float类型
     * Y : 起始y坐标值,  float类型
     * Ttime : 毫秒延时.long类型
     */

    private int A;
    private float X;
    private float Y;

    public int getA() {
        return A;
    }

    public void setA(int a) {
        this.A = a;
    }

    public long getT() {
        return T;
    }

    public void setT(long t) {
        T = t;
    }

    private long T;

    public void setAll(int action,float x, float y, long ttime) {
        this.A = action;
        X = x;
        Y = y;
        T = ttime;
    }


    public float getX() {
        return X;
    }

    public void setX(float x) {
        X = x;
    }

    public float getY() {
        return Y;
    }

    public void setY(float y) {
        Y = y;
    }

}
