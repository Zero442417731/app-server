package com.example.wzs.myapplication.weight;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.nonecity.R;


/**
 * Created by Administrator on 2016/1/8.
 */
public class SideBarView extends View {
    public static String[] b = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "#"};
    private int selectPos = 0;

    private final int defaultNormalColor = Color.TRANSPARENT;
    private final int defaultPressColor = Color.parseColor("#1F000000");
    private final int defaultTextSize = 30;
    private final int defaultNorTextColor = Color.parseColor("#cc181818");
    private final int defaultPressTextColor = Color.parseColor("#1dcdef");


    private int sideBarBgNorColor;
    private int sideBarBgPressColor;
    private int sideBarTextSize;
    private int sideBarNorTextColor;
    private int sideBarPressTextColor;


    public SideBarView(Context context) {
        this(context, null);
    }

    public SideBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init();
    }

    public SideBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SideBarView, defStyleAttr, 0);
        sideBarBgNorColor = typedArray.getColor(R.styleable.SideBarView_sidebar_nor_background, defaultNormalColor);
        sideBarBgPressColor = typedArray.getColor(R.styleable.SideBarView_sidebar_press_background, defaultPressColor);
        sideBarTextSize = typedArray.getInt(R.styleable.SideBarView_sidebar_text_size, defaultTextSize);
        sideBarNorTextColor = typedArray.getColor(R.styleable.SideBarView_sidebar_text_color_nor, defaultNorTextColor);
        sideBarPressTextColor = typedArray.getColor(R.styleable.SideBarView_sidebar_text_color_press, defaultPressTextColor);

        typedArray.recycle();

        init();
    }

    Paint paint;
    Paint paintSelect;

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setColor(Color.parseColor("#F7F7F7"));
        paint.setTextSize(sideBarTextSize);
        paintSelect = new Paint();
        paintSelect.setAntiAlias(true);
        paintSelect.setTypeface(Typeface.DEFAULT_BOLD);
        paintSelect.setTextSize(sideBarTextSize);
        paintSelect.setColor(Color.parseColor("#1dcdef"));
    }

    int height;
    int width;
    int perHeight;

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        float y = event.getY();
        int position = (int) (y / perHeight);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                setBackgroundColor(sideBarBgPressColor);
                selectPos = position;
                if (listener != null)
                    listener.onLetterSelected(b[selectPos]);
                invalidate();
                break;


            case MotionEvent.ACTION_MOVE:
                //切换到其他字母
                if (position != selectPos)
                    selectPos = position;
                //防止数组越界
                if (listener != null && 0 <= selectPos && selectPos <= b.length - 1) {
                    //回调按下的字母
                    listener.onLetterChanged(b[selectPos]);
                }
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                setBackgroundColor(sideBarBgNorColor);

                if (listener != null && 0 <= selectPos && selectPos <= b.length - 1) {
                    //回调按下的字母
                    listener.onLetterReleased(b[selectPos]);
                }
                break;
        }


        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        height = getHeight();
        width = getWidth();
        perHeight = height / b.length;
        for (int i = 0; i < b.length; i++) {
            //判断是不是我们按下的当前字母
            if (selectPos == i) {
                //绘制文字圆形背景
                canvas.drawCircle(width / 2, perHeight / 2 + i * perHeight, 23, paintSelect);
                paint.setColor(Color.WHITE);
            } else {
                paint.setColor(Color.GRAY);
            }
            //获取文字的宽高
            Rect rect = new Rect();
            paint.getTextBounds(b[i], 0, 1, rect);
            int wordWidth = rect.width();
            //绘制字母
            float wordX = width / 2 - wordWidth / 2;
            float wordY = width / 2 + i * perHeight;
            canvas.drawText(b[i], wordX, wordY, paint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = resolveMeasure(widthMeasureSpec, true);
        int height = resolveMeasure(heightMeasureSpec, false);
        setMeasuredDimension(width, height);
    }

    private int resolveMeasure(int measureSpec, boolean isWidth) {

        int result = 0;
        int padding = isWidth ? getPaddingLeft() + getPaddingRight() : getPaddingTop() + getPaddingBottom();

        // 获取宽度测量规格中的mode
        int mode = MeasureSpec.getMode(measureSpec);

        // 获取宽度测量规格中的size
        int size = MeasureSpec.getSize(measureSpec);

        switch (mode) {
            case MeasureSpec.EXACTLY:
                result = size;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                float textWidth = paint.measureText(b[0]);
                if (isWidth) {
                    result = getSuggestedMinimumWidth() > textWidth ? getSuggestedMinimumWidth() : (int) textWidth;
                    result += padding;
                    result = Math.min(result, size);
                } else {
                    result = size;
                    result = Math.max(result, size);
                }

                break;
        }


        return result;
    }


    public float dp2px(float dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }

    public void setTouchIndex(String word) {
        for (int i = 0; i < b.length; i++) {
            if (b[i].equals(word)) {
                selectPos = i;
                invalidate();
                return;
            }
        }
    }

    @Override
    protected int getSuggestedMinimumWidth() {
        return (int) dp2px(25);
    }


    public interface LetterSelectListener {
        void onLetterSelected(String letter);

        void onLetterChanged(String letter);

        void onLetterReleased(String letter);
    }

    private LetterSelectListener listener;

    public void setOnLetterSelectListen(LetterSelectListener listen) {
        this.listener = listen;
    }


}
