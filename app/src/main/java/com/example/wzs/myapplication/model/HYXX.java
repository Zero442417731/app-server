package com.example.wzs.myapplication.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hxcs-02 on 2017/8/4.
 */
@SuppressWarnings("serial")
public class HYXX implements Serializable {

    /**
     * friendUserId : 好友用户id
     * drawingId : 画板id
     * windowWidth : 屏幕宽度int类型
     * windowHeight : 屏幕高度int类型
     * canvas_color : 画布颜色
     * order : 顺序，long类型
     * command : 命令
     * paintSize : 画笔大小
     * paintColor : 画笔颜色
     * drawingData : [{"startX":"起始x坐标值float类型","startY":"起始y坐标值float类型"},{"stopX":"终止x坐标值float类型","stopY":"终止y坐标值float类型"}]
     */

    private String friendUserId;
    private String drawingId;
    private int windowWidth;
    private int windowHeight;
    private String canvas_color;
    private Long order;
    private String command;
    private String paintSize;
    private String paintColor;
    private List<DrawingDataBean> drawingData;

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

    public int getWindowWidth() {
        return windowWidth;
    }

    public void setWindowWidth(int windowWidth) {
        this.windowWidth = windowWidth;
    }

    public int getWindowHeight() {
        return windowHeight;
    }

    public void setWindowHeight(int windowHeight) {
        this.windowHeight = windowHeight;
    }

    public String getCanvas_color() {
        return canvas_color;
    }

    public void setCanvas_color(String canvas_color) {
        this.canvas_color = canvas_color;
    }

    public Long getOrder() {
        return order;
    }

    public void setOrder(Long order) {
        this.order = order;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getPaintSize() {
        return paintSize;
    }

    public void setPaintSize(String paintSize) {
        this.paintSize = paintSize;
    }

    public String getPaintColor() {
        return paintColor;
    }

    public void setPaintColor(String paintColor) {
        this.paintColor = paintColor;
    }

    public List<DrawingDataBean> getDrawingData() {
        return drawingData;
    }

    public void setDrawingData(List<DrawingDataBean> drawingData) {
        this.drawingData = drawingData;
    }

    public static class DrawingDataBean {
        /**
         * startX : 起始x坐标值float类型
         * startY : 起始y坐标值float类型
         * stopX : 终止x坐标值float类型
         * stopY : 终止y坐标值float类型
         */

        private float startX;
        private float startY;
        private float stopX;
        private float stopY;

        public float getStartX() {
            return startX;
        }

        public void setStartX(float startX) {
            this.startX = startX;
        }

        public float getStartY() {
            return startY;
        }

        public void setStartY(float startY) {
            this.startY = startY;
        }

        public float getStopX() {
            return stopX;
        }

        public void setStopX(float stopX) {
            this.stopX = stopX;
        }

        public float getStopY() {
            return stopY;
        }

        public void setStopY(float stopY) {
            this.stopY = stopY;
        }
    }
}
