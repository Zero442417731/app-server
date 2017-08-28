package com.example.wzs.myapplication.model.friendMsg;

import java.io.Serializable;

/**
 * Created by hxcs-02 on 2017/8/4.
 */
@SuppressWarnings("serial")
public class HYXX implements Serializable {
    /**
     * token : 单用户登录唯一标示，必需
     * friendUserId : 接收用户id
     * userid:发送用户id
     * drawingId : 画板id
     * order : 顺序，long类型.按当前时间毫秒作为顺序标记.
     * command : 命令：画笔,橡皮,拖动,删除.int类型
     * paintSize : 画笔大小int类型
     * paintColor : 画笔颜色int类型
     * drawingData : [{"action":"动作, int类型：1down，2move，3up","X":"起始x坐标值,  float类型","Y":"起始y坐标值,  float类型","Ttime":"毫秒延时.long类型"},{"action":"动作, int类型：1down，2move，3up","X":"起始x坐标值,  float类型","Y":"起始y坐标值,  float类型","Ttime":"毫秒延时.long类型"}]
     */

    private String friendUserId;
    private String userid;
    private String token;
    private String drawingId;
    private long order;
    private String command;
    private int paintSize;
    private int paintColor;

    private String drawingData;

    public String getDrawingData() {
        return drawingData;
    }

    public void setDrawingData(String drawingData) {
        this.drawingData = drawingData;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFriendUserId() {
        return friendUserId;
    }

    public void setFriendUserId(String friendUserId) {
        this.friendUserId = friendUserId;
    }

    public String getDrawingId() {
        return drawingId;
    }

    public void setDrawingId(String drawingId) {
        this.drawingId = drawingId;
    }

    public long getOrder() {
        return order;
    }

    public void setOrder(long order) {
        this.order = order;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public int getPaintSize() {
        return paintSize;
    }

    public void setPaintSize(int paintSize) {
        this.paintSize = paintSize;
    }

    public int getPaintColor() {
        return paintColor;
    }

    public void setPaintColor(int paintColor) {
        this.paintColor = paintColor;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
