package com.example.wzs.myapplication.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hxcs-02 on 2017/8/18.
 */

public class DrawModel implements Serializable {


    /**
     * header : {"code":"HXCS-JC-HYXX"}
     * body : {"token":"单用户登录唯一标示，必需","friendUserId":"好友用户id","drawingId":"画板id","order":"顺序，long类型.按当前时间毫秒作为顺序标记.","command":"命令：画笔,橡皮,拖动,删除.int类型","paintSize":"画笔大小int类型","paintColor":"画笔颜色int类型","drawingData":[{"action":"动作, int类型：1down，2move，3up","X":"起始x坐标值,  float类型","Y":"起始y坐标值,  float类型","Ttime":"毫秒延时.long类型"},{"action":"动作, int类型：1down，2move，3up","X":"起始x坐标值,  float类型","Y":"起始y坐标值,  float类型","Ttime":"毫秒延时.long类型"}]}
     */

    private HeaderBean header;
    private BodyBean body;

    public HeaderBean getHeader() {
        return header;
    }

    public void setHeader(HeaderBean header) {
        this.header = header;
    }

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public static class HeaderBean {
        /**
         * code : HXCS-JC-HYXX
         */

        private String code;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

    public static class BodyBean {
        /**
         * token : 单用户登录唯一标示，必需
         * friendUserId : 好友用户id
         * drawingId : 画板id
         * order : 顺序，long类型.按当前时间毫秒作为顺序标记.
         * command : 命令：画笔,橡皮,拖动,删除.int类型
         * paintSize : 画笔大小int类型
         * paintColor : 画笔颜色int类型
         * drawingData : [{"action":"动作, int类型：1down，2move，3up","X":"起始x坐标值,  float类型","Y":"起始y坐标值,  float类型","Ttime":"毫秒延时.long类型"},{"action":"动作, int类型：1down，2move，3up","X":"起始x坐标值,  float类型","Y":"起始y坐标值,  float类型","Ttime":"毫秒延时.long类型"}]
         */

        private String friendUserId;
        private String token;
        private String drawingId;
        private String order;

        public String getDrawingData() {
            return drawingData;
        }

        public void setDrawingData(String drawingData) {
            this.drawingData = drawingData;
        }

        private String command;
        private String paintSize;
        private String paintColor;
        private String drawingData;

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

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
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


    }
}
