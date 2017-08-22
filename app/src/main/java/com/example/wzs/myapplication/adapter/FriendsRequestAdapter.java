package com.example.wzs.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wzs.myapplication.config.Constant;
import com.example.wzs.myapplication.model.FriendsRequestsPush;
import com.example.wzs.myapplication.model.RequestList;
import com.example.wzs.myapplication.weight.RoundImageView;
import com.nonecity.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hxcs-04 on 2017/8/16.
 */

public class FriendsRequestAdapter extends RecyclerView.Adapter<FriendsRequestAdapter.FriendsRequestHolder> implements View.OnClickListener {
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private YesOrNo yesorot;
    private String[] mTitles;
    private OnItemClickListener mOnItemClickListener = null;
    private RequestList.BodyBean bodyBeanData;
    ArrayList<RequestList.BodyBean> mlist = new ArrayList<RequestList.BodyBean>();
    private RequestList beanData;
    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(view, (int) view.getTag());
        }
    }

    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public FriendsRequestAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    public FriendsRequestAdapter(Context context, RequestList bean) {
        this(context);
        this.beanData = bean;
    }

    @Override
    public FriendsRequestHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_friends_requests, parent, false);
        FriendsRequestHolder vh = new FriendsRequestHolder(view);
        view.setOnClickListener(this);
        return vh;
    }

    @Override
    public void onBindViewHolder(FriendsRequestHolder holder, int position) {
        bodyBeanData=mlist.get(position);

        holder.itemView.setTag(position);
        holder.imgYes.setOnClickListener(new ImgYesOrNoClickListener(position));
        holder.imgNo.setOnClickListener(new ImgYesOrNoClickListener(position));
       // bodyBeanData = mlist.get(position);
//        for (int i = 0; i < mlist.size(); i++) {
//            holder.textRemake.setText(bodyBeanData.getRemark());
//            holder.textNike.setText(bodyBeanData.getNickName());
//            Glide.with(mContext).load(bodyBeanData.getHeadImgPath()).into(holder.imgHead);
//        }
//        holder.textFrom.setText("来源：" + "来自手机账号查找");
        for (int i = 0; i < mlist.size(); i++) {
            holder.textFrom.setText(bodyBeanData.getResultData().get(i).getArea());
        }
    }

    @Override
    public int getItemCount() {
//        return mlist.size();
        return mlist.size();
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
        @Bind(R.id.img_yes)
        ImageView imgYes;
        @Bind(R.id.img_no)
        ImageView imgNo;

        public FriendsRequestHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


    public void setYesorot(YesOrNo yesorot) {
        this.yesorot = yesorot;
    }

    public interface YesOrNo {
        void yes(int pos);

        void no(int pos);
    }

    class ImgYesOrNoClickListener implements View.OnClickListener {
        private int pos;

        public ImgYesOrNoClickListener(int pos) {
            this.pos = pos;
        }

        @Override
        public void onClick(View view) {

            if (yesorot == null) {
                return;
            }
            switch (view.getId()) {
                case R.id.img_yes:
                    yesorot.yes(pos);
                    break;
                case R.id.img_no:
                    yesorot.no(pos);
                    break;
            }
        }
    }
}
