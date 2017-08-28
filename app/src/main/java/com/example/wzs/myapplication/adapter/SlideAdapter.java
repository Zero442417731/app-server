package com.example.wzs.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wzs.myapplication.model.User;
import com.example.wzs.myapplication.utils.GlideImageLoaderUtil;
import com.nonecity.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hxcs-02 on 2017/8/24.
 */

public class SlideAdapter extends BaseAdapter {
    List<User> mList;
    Context mContext;
    ViewHolder holder;

    public SlideAdapter(Context mContext, List<User> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.msg_item, null);
            holder = new ViewHolder();
            holder.mTextView = (TextView) convertView.findViewById(R.id.msg_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        User user = mList.get(position);
        holder.mTextView.setText(user.getNickName());

        return convertView;
    }

    class ViewHolder {
        TextView mTextView;
    }
}