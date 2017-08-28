package com.example.wzs.myapplication.weight;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;

import com.example.wzs.myapplication.model.friendMsg.HYXX;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static com.example.wzs.myapplication.config.Constant.TOUCH_TOLERANCE;

/**
 * Created by hxcs-02 on 2017/8/17.
 */

public class DrawView extends View implements qunliaoxiaoxi.MyCallInterface {
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Paint mBitmapPaint;// 画布的画笔
    private MyPen myPen = new MyPen();//我的笔
    private MyPath mPath;//我画的路径
    private float mX, mY;// 临时点坐标
    private TimeDifference time_cha;//计算时间差
    private mSendDrawing fasong = new mSendDrawing();//发送画笔内容
    private boolean isb = false;//是否可以涂画，默认false
    private String boardId;//画板id
    private String userid;//我的ID
    private String friendId;//对方id

    private static List<DrawPath> savePath;// 保存Path路径的集合,用List集合来模拟栈
    private DrawPath dp;   // 记录Path路径的对象

    private List<qunliaoxiaoxi> qunliao= new ArrayList<qunliaoxiaoxi>();

    private int screenWidth, screenHeight;// 屏幕长宽


    @Override
    public void jiekou(DrawPath dp) {
        mCanvas.drawPath(dp.path, dp.paint);
        savePath.add(dp);
    }


    @Override
    public void shuaxin() {
        invalidate();// 刷新
    }


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
            for(DrawPath dd:savePath){
                mCanvas.drawPath(dd.path, dd.paint);
            }
            invalidate();// 刷新
        }

    }

    //本地绘画
    public void setCanvasDate(HYXX mdate) {
        boolean bNull=true;
        if(qunliao.size()>0){
            for (qunliaoxiaoxi qun : qunliao){
                if (qun.getFriendId()==mdate.getUserid()){
                    qun.setQunliao(mdate);
                    bNull=false;
                }
            }
        }
        if(bNull){
            qunliao.add(new qunliaoxiaoxi(mdate,screenWidth,screenHeight));
        }


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
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);
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
    public void setMypen(int penColor, int penSize, boolean bPen) {
        myPen.setPen(penColor, penSize, bPen);
    }



    @Override
    public void onDraw(Canvas canvas) {
        //画布背景颜色
        //canvas.drawColor(0xff22FFFF);
        // 将前面已经画过得显示出来
        canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
        if (mPath != null) {
            // 实时的显示
            canvas.drawPath(mPath, myPen);
        }
        if(qunliao.size()>0){
            for (qunliaoxiaoxi qun : qunliao){
                if(qun.getmPath()!=null){
                    canvas.drawPath(qun.getmPath(), qun.getMyPen());
                }
            }
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
            float xx = event.getX();
            float yy = event.getY();
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    touch_start(xx, yy);
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
        mPath = new MyPath();
        time_cha = new TimeDifference();
        dp = new DrawPath();
        dp.paint = myPen;
        dp.userID = 100001;
        dp.mDateCount = (new Date()).getTime();
        mPath.touch_start(x, y);
        mX = x;
        mY = y;
        //设置发送参数
        fasong.SetmSendDrawing(boardId, friendId, dp.mDateCount, myPen.getColor(), (int) myPen.getStrokeWidth());
        fasong.senddrawing(1, x / screenWidth, y / screenHeight, 0);
    }


    private void touch_move(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(mY - y);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            mPath.touch_move(x, y);
            mX = x;
            mY = y;
            fasong.senddrawing(2, x / screenWidth, y / screenHeight, time_cha.shijiancha());
        }

    }

    private void touch_up() {
        mPath.touch_up();
        //画笔抬起上一条会消失,
        //mCanvas.drawColor(0x5522FFFF);
        mCanvas.drawPath(mPath, myPen);
        //mCanvas.save();
        // 将一条完整的路径保存下来(相当于入栈操作)
        dp.path = mPath;
        savePath.add(dp);
        fasong.senddrawing(3, mX / screenWidth, mY / screenHeight, time_cha.shijiancha());
        mPath.reset();// 重新置空
    }

}
