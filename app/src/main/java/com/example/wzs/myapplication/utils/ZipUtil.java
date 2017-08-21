package com.example.wzs.myapplication.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by hxcs-02 on 2017/8/21.
 */

public class ZipUtil {
    /**
     * 字符串的压缩
     *
     * @param str 待压缩的字符串
     * @return 返回压缩后的字符串
     * @throws IOException
     */
    public static String compress(String str) {
        if (null == str || str.length() <= 0) {
            return str;
        }
        // 创建一个新的 byte 数组输出流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // 使用默认缓冲区大小创建新的输出流
        String replace = null;
        try {
            GZIPOutputStream gzip = new GZIPOutputStream(out);
            // 将 b.length 个字节写入此输出流
            gzip.write(str.getBytes());
            gzip.close();
            replace = out.toString("ISO-8859-1").replace("\n", "hxcs01").replace("\r", "hxcs02");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 使用指定的 charsetName，通过解码字节将缓冲区内容转换为字符串
        return replace;
    }

    /**
     * 字符串的解压
     *
     * @param str 对字符串解压
     * @return 返回解压缩后的字符串
     * @throws IOException
     */
    public static String unCompress(String str) {
        if (null == str || str.length() <= 0) {
            return str;
        }
        // 创建一个新的 byte 数组输出流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        String outStr = null;
        // 创建一个 ByteArrayInputStream，使用 buf 作为其缓冲区数组
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(str.replace("hxcs01", "\n").replace("hxcs02", "\r")
                    .getBytes("ISO-8859-1"));
            // 使用默认缓冲区大小创建新的输入流
            GZIPInputStream gzip = new GZIPInputStream(in);
            byte[] buffer = new byte[256];
            int n = 0;
            while ((n = gzip.read(buffer)) >= 0) {// 将未压缩数据读入字节数组
                // 将指定 byte 数组中从偏移量 off 开始的 len 个字节写入此 byte数组输出流
                out.write(buffer, 0, n);
            }
            outStr = out.toString("GBK");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 使用指定的 charsetName，通过解码字节将缓冲区内容转换为字符串
        return outStr;
    }
}
