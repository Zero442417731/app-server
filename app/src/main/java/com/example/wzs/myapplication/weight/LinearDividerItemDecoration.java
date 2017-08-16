package com.example.wzs.myapplication.weight;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by hxcs-04 on 2017/8/16.
 */

public class LinearDividerItemDecoration extends RecyclerView.ItemDecoration {
    private int[] attrs = {android.R.attr.listDivider};

    private Drawable mDividerDrawable;
    private int mOri;

    public LinearDividerItemDecoration(Context context, int ori) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs);
        mDividerDrawable = typedArray.getDrawable(0);
        this.mOri = ori;
        typedArray.recycle();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (mOri == LinearLayoutManager.VERTICAL) {
            //画水平线
            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = parent.getChildAt(i);

                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                int left = child.getLeft() - params.leftMargin;
                int top = child.getBottom() + params.bottomMargin;
                int right = child.getRight() + params.rightMargin;
                int bottom = top + mDividerDrawable.getIntrinsicHeight();
                mDividerDrawable.setBounds(left, top, right, bottom);
                mDividerDrawable.draw(c);
            }
        } else if (mOri == LinearLayoutManager.HORIZONTAL) {
            //画垂直线
            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = parent.getChildAt(i);
                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                int left = child.getRight() + params.rightMargin;
                int right = left + mDividerDrawable.getIntrinsicWidth();
                int top = child.getTop() - params.topMargin;
                int bottom = child.getBottom() + params.bottomMargin;
                mDividerDrawable.setBounds(left, top, right, bottom);
                mDividerDrawable.draw(c);
            }
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (mOri == LinearLayoutManager.HORIZONTAL) {
            outRect.set(0, 0, mDividerDrawable.getIntrinsicWidth(), 0);
        } else if (mOri == LinearLayoutManager.VERTICAL) {
            outRect.set(0, 0, 0, mDividerDrawable.getIntrinsicHeight());
        }
    }
}
