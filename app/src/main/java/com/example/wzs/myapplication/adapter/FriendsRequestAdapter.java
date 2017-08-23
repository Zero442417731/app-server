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
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.netty.handler.codec.http.HttpObjectDecoder;

/**
 * Created by hxcs-04 on 2017/8/16.
 */

public class FriendsRequestAdapter extends RecyclerView.Adapter<FriendsRequestAdapter.FriendsRequestHolder> implements View.OnClickListener {
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private YesOrNo yesorot;
    private String[] mTitles;
    private OnItemClickListener mOnItemClickListener = null;
    private List<RequestList.BodyBean.ResultDataBean> mlist = new ArrayList<>();


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

    public FriendsRequestAdapter(Context context, List<RequestList.BodyBean.ResultDataBean> mlist) {
        this(context);
        this.mlist = mlist;
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
        RequestList.BodyBean.ResultDataBean bodyBeanData = mlist.get(position);

        holder.itemView.setTag(position);
        holder.imgYes.setOnClickListener(new ImgYesOrNoClickListener(position));
        holder.imgNo.setOnClickListener(new ImgYesOrNoClickListener(position));

        for (int i = 0; i < mlist.size(); i++) {
            holder.textFrom.setText(bodyBeanData.getArea());
            //         holder.textFrom.setText(bodyBeanData.get(i).getArea());
            holder.textNike.setText(bodyBeanData.getNickName());
            holder.textRemake.setText("备注：" + bodyBeanData.getRemark());
            holder.textFrom.setText("来源：" + "通过手机号查找");
            Glide.with(mContext).load(bodyBeanData.getHeadImgPath()).into(holder.imgHead);
            switch (bodyBeanData.getState()) {
                /**
                 * e" : "状态 0:好友关系待验证 1:验证通过 2:屏蔽好友"
                 */
                case 1:
                    holder.lineYesOrNo.setVisibility(View.GONE);
                    holder.lineFriendsStatus.setVisibility(View.VISIBLE);
                    holder.textFriendsStatus.setText("已通过");
                    break;
                case 2:
//屏蔽好友操作
                    break;
                case 0:
//                    holder.lineYesOrNo.setVisibility(View.GONE);
//                    holder.lineFriendsStatus.setVisibility(View.VISIBLE);
//                    holder.textFriendsStatus.setText("待验证");
                    holder.lineYesOrNo.setVisibility(View.VISIBLE);
                    holder.lineFriendsStatus.setVisibility(View.GONE);
                    break;
            }
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
        @Bind(R.id.line_yes_or_not)
        LinearLayout lineYesOrNo;
        @Bind(R.id.item)
        LinearLayout item;
        @Bind(R.id.img_yes)
        ImageView imgYes;
        @Bind(R.id.img_no)
        ImageView imgNo;
        @Bind(R.id.line_friends_status)
        LinearLayout lineFriendsStatus;
        @Bind(R.id.text_friends_status)
        TextView textFriendsStatus;

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
