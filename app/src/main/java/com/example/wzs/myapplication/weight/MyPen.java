package com.example.wzs.myapplication.weight;

import android.graphics.Color;
import android.graphics.Paint;


/**
 * 涂鸦时所用的画笔
 */
public class MyPen extends Paint {
    boolean isPen;//是否是画笔 true - 画笔  false - 橡皮差
    private int mPenColor;
    private int mPenSize;
    private static int COLOR = 0x000000;
    private static int STROKE_WIDTH = 30;


    /**
     * 默认的画笔样式∂
     */
    public MyPen() {
        super();
        this.setStrokeJoin(Paint.Join.ROUND);
        this.setStrokeCap(Paint.Cap.ROUND);
        this.setAntiAlias(true);
        this.setStyle(Paint.Style.STROKE);
        setPen(COLOR, STROKE_WIDTH,true);
    }

    /**
     * 设置画笔
     *
     * @param penColor 颜色
     * @param penSize  笔宽
     * @param isp      true：画笔   false:橡皮
     */
    public void setPen(int penColor, int penSize, boolean isp) {
        isPen = isp;
        if (isPen) {
            mPenColor = penColor;
            mPenSize = penSize;
            this.setStrokeWidth(mPenSize);
            this.setColor(mPenColor);
            this.setAlpha(255);
        } else {
            this.setStrokeWidth(mPenSize);
            this.setAlpha(255);
            this.setColor(Color.WHITE);
        }

    }


    public int getPenColor() {
        return mPenColor;
    }

    public int getPenSize() {
        return mPenSize;
    }

    public boolean isPen() {
        return isPen;
    }


}