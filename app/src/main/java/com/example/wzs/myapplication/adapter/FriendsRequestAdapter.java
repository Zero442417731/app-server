package com.example.wzs.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wzs.myapplication.weight.RoundImageView;
import com.nonecity.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hxcs-04 on 2017/8/16.
 */

public class FriendsRequestAdapter extends RecyclerView.Adapter<FriendsRequestAdapter.FriendsRequestHolder> {
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
private String []mTitles;

    public FriendsRequestAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public FriendsRequestHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FriendsRequestHolder(mLayoutInflater.inflate(R.layout.item_friends_requests, parent, false));
    }

    @Override
    public void onBindViewHolder(FriendsRequestHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public static class FriendsRequestHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.img_head)
        RoundImageView imgHead;
        @Bind(R.id.text_nike)
        TextView textNike;
        @Bind(R.id.text_remake)
        TextView textRemake;
        @Bind(R.id.text_from)
        TextView textFrom;
        @Bind(R.id.line_yes_or_no)
        LinearLayout lineYesOrNo;
        @Bind(R.id.item)
        LinearLayout item;
        public FriendsRequestHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}