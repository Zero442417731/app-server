package com.example.wzs.myapplication.weight;

import android.os.Handler;

import com.example.wzs.myapplication.model.friendMsg.DrawingDataBean;
import com.example.wzs.myapplication.model.friendMsg.HYXX;
import com.example.wzs.myapplication.utils.List2Json;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by wanglin on 2017/8/25.
 */

public class qunliaoxiaoxi {
    private MyCallInterface callInterface;
    private MyPen myPen;
    private MyPath mPath;
    private DrawPath dp;
    private String friendId;

    private LinkedList<DrawingDataBean> inshuju = new LinkedList<>();
    private int screenWidth = 1920;
    private int screenHeight = 1080;

    public qunliaoxiaoxi(HYXX mdate,int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        setQunliao(mdate);
    }

    public void setQunliao(HYXX mdate) {
        boolean pen;
        if (mdate.getCommand() == "1") {
            pen = true;
        } else {
            pen = false;
        }
        friendId = mdate.getUserid();
        dp.mDateCount = mdate.getOrder();
        List<DrawingDataBean> list = List2Json.fromDrawStringZip(mdate.getDrawingData());
        myPen.setPen(mdate.getPaintColor(), mdate.getPaintSize(), pen);
        inshuju.addAll(list);
        mHandler.removeCallbacks(mRunnable);
        mHandler.postDelayed(mRunnable, 0);
    }

    private Handler mHandler = new Handler();
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            mHandler.postDelayed(this, inshuju.getFirst().getT());
            huitu(inshuju.removeFirst());
            if (inshuju.size() == 0) {
                mHandler.removeCallbacks(this);
                return;
            }
        }
    };

    /**
     * 根据数据画图
     *
     * @param indate
     */
    private void huitu(DrawingDataBean indate) {
        switch (indate.getA()) {
            case 1:
                touch_start2(indate.getX() * screenWidth, indate.getY() * screenHeight);
                break;
            case 2:
                m_move(indate.getX() * screenWidth, indate.getY() * screenHeight);
                break;
            case 3:
                touch_up2();
                break;
        }
        callInterface.shuaxin();

    }


    private void touch_start2(float x, float y) {

        if (mPath == null) {
            mPath = new MyPath();
        }
        mPath.touch_start(x, y);
    }

    private void m_move(float x, float y) {
        //这里终点设为两点的中心点的目的在于使绘制的曲线更平滑，如果终点直接设置为x,y，效果和lineto是一样的,实际是折线效果
        mPath.touch_move(x, y);
    }

    private void touch_up2() {
        // mCanvas.drawPath(mPath2, mPaint);
        mPath.touch_up();
        callInterface.jiekou(dp);
        mPath = null;

    }

    public String getFriendId() {
        return friendId;
    }

    public MyPen getMyPen() {
        return myPen;
    }

    public MyPath getmPath() {
        return mPath;
    }

    public interface MyCallInterface {
        public void jiekou(DrawPath dp);
        public void shuaxin();
    }

}

