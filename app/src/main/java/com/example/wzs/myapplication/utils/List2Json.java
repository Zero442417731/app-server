package com.example.wzs.myapplication.utils;

import com.example.wzs.myapplication.model.friendMsg.DrawingDataBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by hxcs-02 on 2017/8/22.
 */

public class List2Json {

    public static JSONArray ProLogList2Json(List<DrawingDataBean> list) {
        JSONArray json = new JSONArray();
        for (DrawingDataBean bean : list) {
            JSONObject jo = new JSONObject();
            try {
                jo.put("A", bean.getA());
                jo.put("T", bean.getT());
                jo.put("X", bean.getX());
                jo.put("Y", bean.getY());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            json.put(jo);
        }
        return json;

    }

    /**
     * 对象转JSON字符
     * @param object
     * @return
     */
    public static String toJsonZip(Object object){
        JsonBinder jsonBinder = JsonBinder.buildNormalBinder();
        return ZipUtil.compress(jsonBinder.toJson(object));
    }

    /**
     * JSON字符转对象
     * @param jsonString
     * @param clazz
     * @param <T>
     * @return
     */

    public static <T> T fromJsonZip(String jsonString, Class<T> clazz) {
        JsonBinder jsonBinder = JsonBinder.buildNormalBinder();
        return jsonBinder.fromJson(ZipUtil.decompress(jsonString),clazz);
    }


    /**
     * HYXX.DrawingDataBean类型转字符
     * @param list_drawingDataBean
     * @return
     */
    public static String toDrawString (List<DrawingDataBean> list_drawingDataBean){
        String str="";
        if (list_drawingDataBean != null && list_drawingDataBean.size() > 0) {
            Iterator<DrawingDataBean> iter = list_drawingDataBean.iterator();
            while (iter.hasNext()) {
                DrawingDataBean sss= iter.next();
                //乘万取整 精确4位
                //str += sss.getA()+","+(int)(sss.getX()*10000)+","+(int)(sss.getY()*10000)+","+ sss.getT()+";";
                str += sss.getA()+","+sss.getX()+","+sss.getY()+","+ sss.getT()+";";
            }
        }
        return str;
    }

    /**
     * HYXX.DrawingDataBean类型转字符并压缩
     * @param list_drawingDataBean
     * @return
     */
    public static String toDrawStringZip (List<DrawingDataBean> list_drawingDataBean){
        return ZipUtil.compress(toDrawString(list_drawingDataBean));
    }


    /**
     * 字符转HYXX.DrawingDataBean类型
     * @param str
     * @return HYXX.DrawingDataBea
     */
    public static List<DrawingDataBean> fromDrawString(String str){
        String[] strArray = null;
        strArray = str.split(";");
        List<DrawingDataBean> list_drawingDataBean = new ArrayList<>(strArray.length);
        for(int i =0;i<strArray.length;i++){
            DrawingDataBean ss=new DrawingDataBean();
            String[] strnum =  strArray[i].split(",");
            if(strnum.length==4) {
                ss.setAll(Integer.parseInt(strnum[0]),Float.parseFloat(strnum[1]), Float.parseFloat(strnum[2]),  Long.parseLong(strnum[3]));
                //LogUtil.e("转换测试：", strnum[0] + "------" +strnum[1] + "------" +strnum[2] + "------" +strnum[3]  );//输出测试
                list_drawingDataBean.add(ss);
            }
        }
        return list_drawingDataBean;
    }


    /**
     * 字符解压缩并转 HYXX.DrawingDataBean类型
     * @param str
     * @return HYXX.DrawingDataBea
     */
    public static List<DrawingDataBean> fromDrawStringZip(String str){
        return fromDrawString(ZipUtil.decompress(str));
    }

}
