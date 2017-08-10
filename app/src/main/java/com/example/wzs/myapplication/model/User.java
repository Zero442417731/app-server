package com.example.wzs.myapplication.model;


import com.nonecity.R;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/1/8.
 */
public class User implements Serializable {
    private String name;
    private String letter;
    //拼音首字母
    private String headerWord;

    public String getHeaderWord() {
        return headerWord;
    }

    public void setHeaderWord(String headerWord) {
        this.headerWord = headerWord;
    }

    private int icon = R.mipmap.ic_launcher;

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

}
