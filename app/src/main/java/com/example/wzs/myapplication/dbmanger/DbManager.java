package com.example.wzs.myapplication.dbmanger;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.wzs.myapplication.application.HXApplication;
import com.example.wzs.myapplication.dbmanger.db_dao.DaoMaster;
import com.example.wzs.myapplication.dbmanger.db_dao.DaoSession;
import com.example.wzs.myapplication.dbmanger.db_dao.FriendMsg;
import com.example.wzs.myapplication.dbmanger.db_dao.UserInfoEntity;

import java.util.List;

/**
 * Created by hxcs-02 on 2017/8/18.
 */

public class DbManager {

    private final DaoMaster daoMaster;

    public DbManager() {

        DaoMaster.DevOpenHelper helper=new DaoMaster.DevOpenHelper(HXApplication.mContext,"hxcs.db");

        SQLiteDatabase writableDatabase = helper.getWritableDatabase();

        daoMaster = new DaoMaster(writableDatabase);
    }

    public <T> void insertData(T t){
        DaoSession daoSession = daoMaster.newSession();
        daoSession.insert(t);
    }

    public <T> void updateData(T t){
        DaoSession daoSession = daoMaster.newSession();

        daoSession.update(t);
    }
    public <T> void deleteData(T t){
        DaoSession daoSession=daoMaster.newSession();
        daoSession.delete(t);
    }

    public <T> void deleteAll(Class<T> entityClass){
        DaoSession daoSession=daoMaster.newSession();

        daoSession.deleteAll(entityClass);
    }

    public <T> void insertAll(List<T> list){

        for (int i=0;i<list.size();i++){
            DaoSession daoSession = daoMaster.newSession();

            daoSession.insert(list.get(i));
        }

    }

    public List<FriendMsg> selectFriendMsg(){

        DaoSession daoSession = daoMaster.newSession();

        return daoSession.getFriendMsgDao().queryBuilder().build().list();
    }
    public List<UserInfoEntity> selectFriendList(){

        DaoSession daoSession = daoMaster.newSession();

        return daoSession.getUserInfoEntityDao().queryBuilder().build().list();
    }
}
