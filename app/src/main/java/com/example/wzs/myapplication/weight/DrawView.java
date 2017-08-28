package com.example.wzs.myapplication.weight;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


import com.example.wzs.myapplication.model.friendMsg.DrawingDataBean;
import com.example.wzs.myapplication.model.friendMsg.HYXX;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by hxcs-02 on 2017/8/17.
 */

public class DrawView extends View {
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Path mPath;
    private Path mPath2;
    private Paint mBitmapPaint;// 画布的画笔
    private Paint mPaint;// 真实的画笔
    private float mX, mY;// 临时点坐标
    private float mX2, mY2;// 临时点坐标
    private TimeDifference time_cha;
    private DrawingDataBean drawingDataBean;
    private mSendDrawing fasong;
    private LinkedList<DrawingDataBean> inshuju = new LinkedList<>();
    //Message msg;


    /**
     * 是否可以涂画，默认false
     */
    private boolean isb = false;
    /**
     * 画板的id
     */
    private String boardId;
    /**
     * 用户的id
     */
    private long userid;
    private String friendId;

    Thread t1 = new Thread();
    private static final float TOUCH_TOLERANCE = 4;
    //Date curDate = new Date(System.currentTimeMillis());//获取当前时间

    // 保存Path路径的集合,用List集合来模拟栈
    private static List<DrawPath> savePath;

    // 记录Path路径的对象
    private DrawPath dp;
    private DrawPath dp2;

    private int screenWidth, screenHeight;// 屏幕长宽


    /**
     * 本地数据导入画板
     */
    public void DataImport(DrawPathdate shuju) {
        boardId = shuju.huabanID;
        friendId = shuju.friendID;
        savePath = shuju.DrawPaths;
        mBitmap = Bitmap.createBitmap(screenWidth, screenHeight,
                Bitmap.Config.ARGB_8888);
        mCanvas.setBitmap(mBitmap);// 重新设置画布，相当于清空画布
        // 清空画布，但是如果图片有背景的话，则使用上面的重新初始化的方法，用该方法会将背景清空掉...
        if (savePath != null && savePath.size() > 0) {
            Iterator<DrawPath> iter = savePath.iterator();
            while (iter.hasNext()) {
                DrawPath drawPath = iter.next();
                mCanvas.drawPath(drawPath.path, drawPath.paint);
            }
            invalidate();// 刷新
        }

    }


    private Handler mHandler = new Handler();
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            mHandler.postDelayed(this, inshuju.getFirst().getT());
            huitu(inshuju.removeFirst());
            invalidate();
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
        invalidate();
    }

    private void touch_start2(float x, float y) {
        mX2 = x;
        mY2 = y;
        if (mPath2 == null) {
            mPath2 = new Path();
        }
        mPath2.moveTo(x, y);
    }
    private void m_move(float x, float y) {
        //这里终点设为两点的中心点的目的在于使绘制的曲线更平滑，如果终点直接设置为x,y，效果和lineto是一样的,实际是折线效果
            mPath2.quadTo(mX2, mY2, (x + mX2) / 2, (y + mY2) / 2);
            mCanvas.drawPath(mPath2, mPaint);

            mX2 = x;
            mY2 = y;
    }

    private void touch_up2() {
        mPath2.reset();

    }

    //本地绘画
    public void setCanvasDate(List<DrawingDataBean> mdate) {
        inshuju.addAll(mdate);
        mHandler.removeCallbacks(mRunnable);
        mHandler.postDelayed(mRunnable, 0);
    }

    public DrawView(Context context, String huabandeid, String friendID, int w, int h) {
        super(context);
        boardId = huabandeid;
        screenWidth = w;
        screenHeight = h;
        this.friendId = friendID;
        mBitmap = Bitmap.createBitmap(screenWidth, screenHeight,
                Bitmap.Config.ARGB_8888);
        // 保存一次一次绘制出来的图形
        mCanvas = new Canvas(mBitmap);
        //mBitmapPaint = new Paint(Paint.DITHER_FLAG);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);// 设置外边缘
        mPaint.setStrokeCap(Paint.Cap.ROUND);// 形状
        mPaint.setStrokeWidth(30);// 画笔宽度
        mPaint.setColor(0xFF333300);// 画笔颜色
        /** 画笔特效 */
        // float[] direction =new float[]{0,1,1};//光源方向
        // float light =0.6f;//环境光亮度
        // float specular =6;//反射等级
        // float blur=3.5f;//模糊级别
        // EmbossMaskFilter emboss =new EmbossMaskFilter(direction,light,specular,blur);
        // EmbossMaskFilter emboss =new EmbossMaskFilter(direction,light,specular,blur);
        // BlurMaskFilter eeee =new BlurMaskFilter(10,BlurMaskFilter.Blur.SOLID);
        //  mPaint.setMaskFilter(eeee);
        // mPaint.getPathEffect();
        savePath = new ArrayList<DrawPath>();
    }

    /**
     * 设置是否为画笔工具
     */


    public void setIsb(boolean setisbi) {
        isb = setisbi;
    }

    /**
     * 设置画笔
     */

    public void setmPaint(int penColor, int penSize, boolean bPen) {
        if (bPen) {//画笔
            mPaint = new Paint(Paint.FILTER_BITMAP_FLAG);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(penSize);
            mPaint.setColor(penColor);
            // mPaint.setAlpha(mPenAlpha);
            mPaint.setAntiAlias(true);
            mPaint.setStrokeJoin(Paint.Join.ROUND);
            mPaint.setStrokeCap(Paint.Cap.ROUND);
            mPaint.getPathEffect();
        } else {//橡皮擦
            mPaint = new Paint();
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setAlpha(255);
            mPaint.setColor(Color.WHITE);
            mPaint.setStrokeWidth(penSize);
            mPaint.setAntiAlias(true);
            mPaint.setStrokeJoin(Paint.Join.ROUND);
            mPaint.setStrokeCap(Paint.Cap.ROUND);
            mPaint.getPathEffect();


        }
    }

    public void isFlag(int flag) {
        switch (flag) {
            case 1:
                break;
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawColor(0xff22FFFF);
        // 将前面已经画过得显示出来
        canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
        if (mPath != null) {
            // 实时的显示
            canvas.drawPath(mPath, mPaint);
            // canvas.drawPath(mPath2, mPaint);
        }
    }


    /**
     * 撤销的核心思想就是将画布清空， 将保存下来的Path路径最后一个移除掉， 重新将路径画在画布上面。
     */
    public void undo() {
        mBitmap = Bitmap.createBitmap(screenWidth, screenHeight,
                Bitmap.Config.ARGB_8888);
        mCanvas.setBitmap(mBitmap);// 重新设置画布，相当于清空画布
        // 清空画布，但是如果图片有背景的话，则使用上面的重新初始化的方法，用该方法会将背景清空掉...
        if (savePath != null && savePath.size() > 0) {
            // 移除最后一个path,相当于出栈操作
            savePath.remove(savePath.size() - 1);
            Iterator<DrawPath> iter = savePath.iterator();
            while (iter.hasNext()) {
                DrawPath drawPath = iter.next();
                mCanvas.drawPath(drawPath.path, drawPath.paint);
            }
            invalidate();// 刷新

			/* 在这里保存图片纯粹是为了方便,保存图片进行验证 */
            String fileUrl = Environment.getExternalStorageDirectory()
                    .toString() + "/android/data/test.png";
            try {
                FileOutputStream fos = new FileOutputStream(new File(fileUrl));
                mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                fos.flush();
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 清空画板
     */
    public void CleanCanvas() {
        mBitmap = Bitmap.createBitmap(screenWidth, screenHeight,
                Bitmap.Config.ARGB_8888);
        mCanvas.setBitmap(mBitmap);// 重新设置画布，相当于清空画布

        savePath.clear();
        invalidate();

    }

    /**
     * 重做的核心思想就是将撤销的路径保存到另外一个集合里面(栈)， 然后从redo的集合里面取出最顶端对象， 画在画布上面即可。
     */
    public void redo() {


        // TODO
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isb) {
            drawingDataBean = new DrawingDataBean();
            float xx = event.getX();
            float yy = event.getY();
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    touch_start(xx, yy);
                    //  Log.d("操作：", "按下操作 颜色：" + Integer.toHexString(mPaint.getColor()) + "笔宽：" + mPaint.getStrokeWidth() + "坐标:" + x + "," + y);
                    break;
                case MotionEvent.ACTION_MOVE:
                    touch_move(xx, yy);
                    break;
                case MotionEvent.ACTION_UP:
                    touch_up();
                    break;
            }
            invalidate();
            return true;
        }
        return true;
    }


    private void touch_start(float x, float y) {

        // 每次down下去重新new一个Path
        mPath = new Path();
        time_cha = new TimeDifference();
        drawingDataBean.setAll(1,x / screenWidth, y / screenHeight,  0);
        dp = new DrawPath();
        dp.path = mPath;
        dp.paint = mPaint;
        dp.userID = 100001;
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
        fasong = new mSendDrawing(boardId, friendId, drawingDataBean);
    }


    private void touch_move(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(mY - y);
        drawingDataBean = new DrawingDataBean();
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            // 从x1,y1到x2,y2画一条贝塞尔曲线，更平滑(直接用mPath.lineTo也是可以的)
            // 由此就可以制作各种画笔
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
        drawingDataBean.setAll( 2,x / screenWidth, y / screenHeight, time_cha.shijiancha());
        fasong.mSendMess_move(drawingDataBean);
    }


    private void touch_up() {
        mPath.lineTo(mX, mY);
        // mPath2.lineTo(mX + 50, mY + 50);
        //画笔抬起上一条会消失
        //   mCanvas.drawColor(0x5522FFFF);
        mCanvas.drawPath(mPath, mPaint);
        mCanvas.save();
        //mCanvas.drawPath(mPath2, mPaint);
        // 将一条完整的路径保存下来(相当于入栈操作)
        dp.path = mPath;
        dp.mDateCount = (new Date()).getTime();
        savePath.add(dp);
        drawingDataBean.setAll( 3,mX / screenWidth, mY / screenHeight, time_cha.shijiancha());
        fasong.mSendMess_up(drawingDataBean);

        mPath = null;// 重新置空
    }

}
