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
import com.example.wzs.myapplication.weight.SwipeLayout;
import com.nonecity.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hxcs-02 on 2017/8/24.
 */

public class SlideAdapter extends BaseAdapter {
    //存放所有已经打开的菜单
    private List<SwipeLayout> openList = new ArrayList<SwipeLayout>();

    private Context context;
    private List<User> list;
    private LayoutInflater inflater;

    public SlideAdapter(Context context, List<User> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }



    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView =inflater.inflate(R.layout.layout_swipe, parent, false);
            holder.textView = (TextView) convertView.findViewById(R.id.textView);
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            holder.tv_del = (TextView) convertView.findViewById(R.id.tv_del);
            holder.tv_zd = (TextView) convertView.findViewById(R.id.tv_zd);
            holder.swipeLayout = (SwipeLayout) convertView.findViewById(R.id.swipeLayout);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        User user = list.get(position);

        holder.textView.setText(user.getNickName());
        GlideImageLoaderUtil.displayImage(user.getHeadImgPath(),holder.imageView);
        holder.tv_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.swipeLayout.close();
                Toast.makeText(context, user.getNickName(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.tv_zd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.swipeLayout.close();
                Toast.makeText(context, user.getNickName(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.swipeLayout.setSwipeChangeListener(new SwipeLayout.OnSwipeChangeListener() {

            @Override
            public void onStartOpen(SwipeLayout mSwipeLayout) {

                for (SwipeLayout layout : openList) {
                    layout.close();
                }
                openList.clear();
            }

            @Override
            public void onStartClose(SwipeLayout mSwipeLayout) {

            }

            @Override
            public void onOpen(SwipeLayout mSwipeLayout) {
                openList.add(mSwipeLayout);
            }

            @Override
            public void onDraging(SwipeLayout mSwipeLayout) {

            }

            @Override
            public void onClose(SwipeLayout mSwipeLayout) {
                openList.remove(mSwipeLayout);
            }
        });
        return convertView;
    }

}

class ViewHolder {
    SwipeLayout swipeLayout;
    TextView textView;
    TextView tv_del,tv_zd;
    ImageView imageView;

}