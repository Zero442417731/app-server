package com.example.wzs.myapplication.weight;

import com.example.wzs.myapplication.config.Constant;
import com.example.wzs.myapplication.model.SendMsg;
import com.example.wzs.myapplication.model.friendMsg.DrawingDataBean;
import com.example.wzs.myapplication.model.friendMsg.HYXX;
import com.example.wzs.myapplication.utils.List2Json;
import com.example.wzs.myapplication.utils.LogUtil;
import com.example.wzs.myapplication.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by wanglin on 2017/8/1.
 */

public class mSendDrawing {
    private static int timeLong = 1000;//时间间隔毫秒
    private static Timer  timer = new Timer();
    private String drawingId;
    private String friendId;
    private int color;
    private float width;
    private long order;
    /**
     * 大概16毫秒一条，申请65个空间够1000毫秒使用
     */
    private List<DrawingDataBean> list_drawingDataBean = new ArrayList<>();


    /**
     * 设置参数
     * @param drawingId 画板id
     * @param friendId  好友id
     * @param Order  时间节点
     * @param color  画笔颜色
     * @param penwidth  画笔宽度
     */
    public void SetmSendDrawing(String drawingId, String friendId, long  Order ,int color, float penwidth) {
        this.drawingId = drawingId;
        this.friendId = friendId;
        this.color=color;
        this.width=penwidth;
        this.order =Order;
    }


    public void  senddrawing( int a,float x,float y,long t ){
        DrawingDataBean drawingDataBean =new DrawingDataBean().setAll(a, x, y, t);
        switch (a){
            case 1:
                mSendMess_start(drawingDataBean);
                break;
            case 2:
                mSendMess_move(drawingDataBean);
                break;
            case 3:
                mSendMess_up(drawingDataBean);
                break;

        }

    }

    //1
    private void mSendMess_start(DrawingDataBean date) {
        timer = new Timer();
        this.list_drawingDataBean.add(date);
        startTime();

    }

    //2
    private void mSendMess_move(DrawingDataBean date) {
        LogUtil.e("坐标------", date.getX() + "------" + date.getY());
        list_drawingDataBean.add(date);

    }


    //3
    private void mSendMess_up(DrawingDataBean date) {
        for (int i = 0; i < list_drawingDataBean.size(); i++) {
            LogUtil.e("_________", "==========" + list_drawingDataBean.get(i).getX() + "----" + list_drawingDataBean.get(i).getY());
        }
        list_drawingDataBean.add(date);
        endTime();
    }




    private boolean fasong() {
        HYXX body =new HYXX();
        body.setToken(SharedPreferencesUtil.getStringPreferences(Constant.CONFIG_SHAREDPREFRENCE_USER, "token"));
        body.setFriendUserId(friendId);
        body.setDrawingId(drawingId);
        body.setOrder(String.valueOf(order));
        body.setCommand("1");
        body.setPaintSize(width);
        body.setPaintColor(color);
        String stringZip = List2Json.toDrawStringZip(list_drawingDataBean);
        body.setDrawingData(stringZip);
        SendMsg send=new SendMsg(Constant.HYXX);
        send.setBody(body);
        send.sendMessage();


//
//
//
//        JsonBinder jsonBinder = JsonBinder.buildNormalBinder();
//        DrawModel drawModel = new DrawModel();
//        DrawModel.HeaderBean headerBean = new DrawModel.HeaderBean();
//        headerBean.setCode("HXCS-JC-HYXX");
//        DrawModel.BodyBean bodyBean = new DrawModel.BodyBean();
//        //String stringZip = List2Json.toDrawStringZip(list_drawingDataBean);
//        bodyBean.setDrawingData(stringZip);
//        drawModel.setHeader(headerBean);
//        drawModel.setBody(bodyBean);
//
//        String s = jsonBinder.toJson(drawModel);
//
//        LogUtil.e("发送消息------", s);
//        ClientUtil.sendMessage(s);
        return true;
    }


    public void startTime() {

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (fasong()) {
                    list_drawingDataBean.clear();
                }
            }

        };
        timer.schedule(task, timeLong, timeLong);// 1秒一次
    }

    public void endTime() {
        timer.cancel();// 停止定时器
        if (fasong()) {
            list_drawingDataBean.clear();
        }

    }

}
