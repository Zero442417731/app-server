package com.example.wzs.myapplication.weight;

import com.example.wzs.myapplication.config.Constant;
import com.example.wzs.myapplication.model.DrawModel;
import com.example.wzs.myapplication.network.ClientUtil;
import com.example.wzs.myapplication.utils.JsonBinder;
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
    private static Timer timer;
    private String drawingId;
    private String friendId;
    /**
     * 大概16毫秒一条，申请65个空间够1000毫秒使用
     */
    private  List<DrawModel.BodyBean.DrawingDataBean> list_drawingDataBean = new ArrayList<>();


    public mSendDrawing(String drawingId, String friendId, DrawModel.BodyBean.DrawingDataBean drawingDataBean) {
        timer = new Timer();
        this.drawingId = drawingId;
        this.friendId = friendId;
        this.list_drawingDataBean.add(drawingDataBean);
        startTime();
    }

    public void mSendMess_move(DrawModel.BodyBean.DrawingDataBean date) {
        LogUtil.e("坐标------",date.getX()+"------"+date.getY());
        list_drawingDataBean.add(date);

    }

    public void mSendMess_up(DrawModel.BodyBean.DrawingDataBean date) {
        for (int i = 0; i < list_drawingDataBean.size(); i++) {
            LogUtil.e("_________","=========="+list_drawingDataBean.get(i).getX()+"----"+list_drawingDataBean.get(i).getY());
        }
        list_drawingDataBean.add(date);

        endTime();
    }


    private boolean fasong() {
        JsonBinder jsonBinder = JsonBinder.buildNormalBinder();
        DrawModel drawModel = new DrawModel();

        DrawModel.HeaderBean headerBean = new DrawModel.HeaderBean();
        headerBean.setCode("HXCS-JC-HYXX");
        DrawModel.BodyBean bodyBean = new DrawModel.BodyBean();
        bodyBean.setToken(SharedPreferencesUtil.getStringPreferences(Constant.CONFIG_SHAREDPREFRENCE_USER, "token"));
        bodyBean.setFriendUserId(friendId);
        bodyBean.setDrawingId(drawingId);
        bodyBean.setOrder("1");
        bodyBean.setCommand("2");
        bodyBean.setPaintSize("3");
        bodyBean.setPaintColor("4");
        bodyBean.setDrawingData(list_drawingDataBean);
        drawModel.setBody(bodyBean);
        drawModel.setHeader(headerBean);
        String s = jsonBinder.toJson(drawModel);
        LogUtil.e("发送消息----", s);
        ClientUtil.sendMessage(s);
        return true;
    }

    /**
     * 清空缓存
     */
    private  void clear() {

        list_drawingDataBean.clear();

    }

    public void startTime() {

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (fasong()) {
                    clear();
                }
            }

        };
        timer.schedule(task, timeLong, timeLong);// 1秒一次
    }

    public void endTime() {
        timer.cancel();// 停止定时器
        if (fasong()) {
            clear();
        }

    }
}
