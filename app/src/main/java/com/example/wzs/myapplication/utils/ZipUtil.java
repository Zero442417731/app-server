package com.example.wzs.myapplication.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;


/**
 * Created by hxcs-02 on 2017/8/21.
 */

public class ZipUtil {


    /***
     *
     * @Title: compress
     * @Description: 加密
     * @param @param paramString
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    public static String compress(String str){
        ByteArrayOutputStream byteArrayOutputStream =null;

        GZIPOutputStream gzipOutputStream =null;
        try {
            if (str == null || str.length() == 0) {
                return str;
            }
            byteArrayOutputStream = new ByteArrayOutputStream();
            gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gzipOutputStream.write(str.getBytes());
            gzipOutputStream.close();

            String base = new BASE64Encoder().encode(byteArrayOutputStream.toByteArray());
            return base.replace("\n", "hxcs01").replace("\r", "hxcs02").replace("hxcs03", "\r\n") ;
        } catch (IOException localIOException5) {
            return null;
        } finally {
            if (gzipOutputStream != null)
                try {
                    gzipOutputStream.close();
                } catch (IOException localIOException6) {
                }
            if (byteArrayOutputStream != null)
                try {
                    byteArrayOutputStream.close();
                } catch (IOException localIOException7) {
                }
        }
    }

    /**
     * @Title: decompress
     * @Description:解密
     * @param @param base
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    @SuppressWarnings("unused")
    public static final String decompress(String base) {

        byte[] paramArrayOfByte=null;
        try {
            paramArrayOfByte = new BASE64Decoder().decodeBuffer(base.replace("hxcs01", "\n").replace("hxcs02", "\r").replace("hxcs03", "\r\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (paramArrayOfByte == null)
            return null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        ByteArrayInputStream byteArrayInputStream = null;
        GZIPInputStream  gzipInputStream = null;
        String str;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayInputStream = new ByteArrayInputStream(paramArrayOfByte);
            gzipInputStream = new GZIPInputStream(byteArrayInputStream);

            byte[] arrayOfByte = new byte[1024];
            int n;
            while ((n = gzipInputStream.read(arrayOfByte))>= 0) {
                byteArrayOutputStream.write(arrayOfByte, 0, n);
            }
            str = byteArrayOutputStream.toString();
        } catch (IOException localIOException7) {
            str = null;
        } finally {
            if (gzipInputStream != null)
                try {
                    gzipInputStream.close();
                } catch (IOException localIOException8) {
                }
            if (byteArrayInputStream != null)
                try {
                    byteArrayInputStream.close();
                } catch (IOException localIOException9) {
                }
            if (byteArrayOutputStream != null)
                try {
                    byteArrayOutputStream.close();
                } catch (IOException localIOException10) {
                }
        }
        return str;
    }


}
