package com.example.wzs.myapplication.weight;

import android.graphics.Path;

/**
 * Created by wanglin on 2017/8/27.
 */

public class MyPath extends Path{
   // private Path mpath;
    private float mX, mY;// 临时点坐标
    private String strPath;

    public void touch_start(float x, float y) {
        this.reset();
        this.moveTo(x, y);
        mX = x;
        mY = y;
        strPath = "1," + (int) x + "," + (int) y + ";";
    }

    public void touch_move(float x, float y) {
        this.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
        mX = x;
        mY = y;
        strPath += "2," + (int) x + "," + (int) y + ";";
    }

    public void touch_up() {
        this.lineTo(mX, mY);
        strPath += "3;";
    }

    public String getStrPath() {
        return strPath;
    }

    @Override
    public void reset() {
        super.reset();
        strPath="";
    }


    public void setStrPath(String strPath) {
        reset();
        String[] strArray = strPath.split(";");
        for (int i = 0; i < strArray.length; i++) {
            String[] strnum = strArray[i].split(",");
            if (strnum.length == 3) {
                if (strnum[0] == "1") {
                    touch_start(Float.valueOf(strnum[1]), Float.valueOf(strnum[2]));
                }
                else if (strnum[0] == "2") {
                    touch_move(Float.valueOf(strnum[1]), Float.valueOf(strnum[2]));
                }
            }
            else if (strnum[0] == "3"){
                touch_up();
            }
        }
    }
}
